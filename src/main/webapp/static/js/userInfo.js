$(function () {
    let yhid = getQueryVariable("yhid");
    console.log("userInfojs" + yhid);
    $.getJSON("viewUserInfoServlet", {"yhid": yhid}, function (user) {
            $("#iYhzh").val(user.yhid);
            $("#iYhxm").val(user.yhxm);
            $("#iYhxb").val(user.yhxb);
            $("#iYhbm").val(user.yhbm);
            $("#iCsrq").val(user.csrq);
            let pxh = user.pxh;
            if (0 === pxh) {
                $("#iPxh").val('-');
            } else {
                $("#iPxh").val(pxh);
            }
            $("#iSfjy").val(user.sfjy);
            $("#iYhkl").val(user.yhkl);
        }
    )

    $("#back").click(function () {
        layerClose(true);
    });
})


function getQueryVariable(variable) {
    let query = window.location.search.substring(1);
    let vars = query.split("&");
    for (let i = 0; i < vars.length; i++) {
        let pair = vars[i].split("=");
        if (pair[0] === variable) {
            return decodeURIComponent(pair[1]);
        }
    }
    return false;
}