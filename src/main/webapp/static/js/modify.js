$(function () {
    $.getJSON(CONTEXT_PATH + "loadDepartSel", function (departs) {
        $.each(departs, function (index, item) {
            if (item.bmmc === $('#iYhbm').val()) {
                $('#sYhbm').append(new Option(item.bmmc, item.bmdm,true,true));
                setSelVal('#sYhbm',item.bmdm);
            } else {
                $('#sYhbm').append(new Option(item.bmmc, item.bmdm));
            }
        });
    });

    $.getJSON(CONTEXT_PATH + "loadGenderSel", function (bzdms) {
        $.each(bzdms, function (index, item) {
            if(item.mc === $('#iYhxb').val()) {
                $('#sYhxb').append(new Option(item.mc, item.code,true,true));
                setSelVal('#sYhxb',item.code);
            }else {
                $('#sYhxb').append(new Option(item.mc, item.code));
            }
        })
    });

    if($('#iSfjy').val() === "是"){
        setSwichVal("input[name='sfjy']", true);
    }

    $('#iSubmit').click(function () {
        doSave();
    })
})

function doSave() {
    let reqInfo = validateForm();

    if (reqInfo === false) {
        layer.msg("请检查必填项！", {
            icon: 7,
            shade: 0.000001, //不展示遮罩，但是要有遮罩效果
            time: 2000
        });
        return;
    }

    let params = serializeTwo('#modifyForm');
    $.ajax({
        url: CONTEXT_PATH + "update",
        type: "post",
        dataType: "text",
        data: params,
        success: function (data) {
            data = $.trim(data);
            console.log(data);
            if (data === "success") {
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
        error: function (e) {
            layer.msg("修改失败！", {
                icon: 0,
                shade: 0.000001, //不展示遮罩，但是要有遮罩效果
                time: 2000
            },function(){
                layerClose(true);
            });
        }
    })
}


function validateForm() {
    let yhzh = $("#iYhzh").val();
    let yhxm = $("#iYhxm").val();
    let yhkl = $("#iYhkl").val();

    return yhzh != null && yhzh !== "" && yhxm != null && yhxm !== "" && yhkl != null && yhkl !== "";
}
