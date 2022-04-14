let yhkl;
let cfkl;
let pxh;
let yhid;
let csrq;
let sfjy;
let iSfjy;
let iYhbm;
let sYhbm;
let iYhxb;
let sYhxb;
let yhxm;
let func;

$(
    function () {
        func = $('#iFunc');
        yhkl = $('#iYhkl');
        cfkl = $('#iCfkl');
        yhxm = $('#iYhxm');
        pxh = $('#iPxh');
        yhid = $('#iYhid');
        csrq = $('#iCsrq');
        sfjy = $('#sfjy');
        iSfjy = $('#iSfjy');
        iYhbm = $('#iYhbm');
        sYhbm = $('#sYhbm');
        iYhxb = $('#iYhxb');
        sYhxb = $('#sYhxb');

        loadSel();

        if (func.val() === "add") {
            doAdd();
        } else if (func.val() === "view") {
            doView();
        } else if (func.val() === "modify") {
            doModify();
        } else {
            layer.msg("出现错误，请联系管理员", {
                icon: 2,
                shade: 0.000001,
                time: 2000
            });
        }
    }
)

//添加用户信息
function doAdd() {
    $('#iSubmit').click(function () {
        let valid = validateForm();
        if (valid === false) {
            layer.msg("请检查必填项！", {
                icon: 7,
                shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                time: 2000
            });
            return valid;
        }

        let params = serializeTwo('#userForm');
        $.ajax({
            url: CONTEXT_PATH + "addUser",
            type: "post",
            dataType: "json",
            data: params,
            success: function (data) {
                if (data.code === 0) {
                    layer.msg("添加成功！", {
                        icon: 1,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    }, function () {
                        layerReturn("success");
                    });
                } else {
                    layer.msg("添加失败！", {
                        icon: 0,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    }, function () {
                        layerClose(true);
                    });
                }
            },
            error: function () {
                layer.msg("请求出现错误，请联系管理员！", {
                    icon: 0,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                }, function () {
                    layerClose(true);
                });
            }
        })
    })
}

//查看用户信息
function doView() {
    inputDisable(yhid, true);
    inputDisable(yhkl, true);
    inputDisable(cfkl, true);
    inputDisable(yhxm, true);
    inputDisable(pxh, true);
    inputDisable(csrq, true);
    selDisable(sYhbm, true);
    selDisable(sYhxb, true);
    if (sfjy.val() === "是") {
        setSwichVal(iSfjy, true);
    }
    inputDisable(sfjy, true);
    $('#iSubmit').hide();
}

//修改用户信息
function doModify() {
    inputDisable(yhid, true);
    if (sfjy.val() === "是") {
        setSwichVal(iSfjy, true);
    }

    $('#iSubmit').click(function () {
        let valid = validateForm();

        if (valid === false) {
            layer.msg("请检查必填项！", {
                icon: 7,
                shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                time: 2000
            });
            return valid;
        }

        let params = serializeTwo('#userForm');
        console.log(params);
        $.ajax({
            url: CONTEXT_PATH + "update",
            type: "post",
            dataType: "json",
            data: params,
            success: function (data) {
                console.log(data.code);
                if (data.code === 0) {
                    layer.msg("修改成功！", {
                        icon: 1,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    }, function () {
                        layerReturn("success");
                    });
                } else {
                    layer.msg("修改失败！", {
                        icon: 0,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    }, function () {
                        layerClose(true);
                    });
                }
            },
            error: function () {
                layer.msg("请求出现错误，请联系管理员！", {
                    icon: 0,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                }, function () {
                    layerClose(true);
                });
            }
        })
    })
}


//加载下拉框
function loadSel() {
    let departs = $('#iDeparts');
    let gender = $('#iGender');

    let jDeparts = JSON.parse(departs.val());
    let jGender = JSON.parse(gender.val());

    $.each(jDeparts, function (index, item) {
        if (item.bmmc === iYhbm.val()) {
            sYhbm.append(new Option(item.bmmc, item.bmdm, true, true));
            setSelVal(sYhbm, item.bmdm);
        } else {
            sYhbm.append(new Option(item.bmmc, item.bmdm));
        }
    });

    $.each(jGender, function (index, item) {
        if (item.mc === iYhxb.val()) {
            sYhxb.append(new Option(item.mc, item.code, true, true));
            setSelVal(sYhxb, item.code);
        } else {
            sYhxb.append(new Option(item.mc, item.code));
        }
    });
}

//提交表单验证
function validateForm() {
    return yhid.val() != null && yhid.val() !== "" && yhxm.val() != null && yhxm.val() !== "" &&
        yhkl.val() != null && yhkl.val() !== "" && cfkl.val() != null && cfkl.val() !== "" &&
        cfkl.val() === yhkl.val();
}

