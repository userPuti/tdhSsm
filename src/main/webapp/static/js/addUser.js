$(function () {
    $.getJSON(CONTEXT_PATH + "loadDepartSel", function (departs) {
        $.each(departs, function (index, item) {
            $('#iYhbm').append(new Option(item.bmmc, item.bmdm));
        })
    });

    $.getJSON(CONTEXT_PATH + "loadGenderSel", function (bzdms) {
        $.each(bzdms, function (index, item) {
            $('#iYhxb').append(new Option(item.mc, item.code));
        })
    });

    $("#iSubmit").click(function () {
            let reqInfo = validateForm();

            if (reqInfo === false) {
                layer.msg("请检查必填项！", {
                    icon: 7,
                    shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                    time: 2000
                });
                return reqInfo;
            }
            let params = serializeTwo("#fAddUser");
            $.post(CONTEXT_PATH + "addUser", params, function (isSucc) {
                isSucc = $.trim(isSucc);
                console.log(isSucc);
                if (isSucc === "success") {
                    layer.msg("添加成功！", {
                        icon: 1,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    }, function () {
                        layerReturn("1");
                    });
                } else {
                    layer.msg("添加失败！", {
                        icon: 2,
                        shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                        time: 2000
                    })
                }
            }, "text");

        }
    )

    $("#back").click(function () {
        layerClose(true);
    });
})

function validateForm() {
    let yhzh = $("#iYhzh").val();
    let yhxm = $("#iYhxm").val();
    let yhkl = $("#iYhkl").val();
    let cfkl = $("#iCfkl").val();

    return yhzh != null && yhzh !== "" && yhxm != null && yhxm !== "" && yhkl != null && yhkl !== "" && cfkl != null && cfkl !== "" && cfkl === yhkl;
}