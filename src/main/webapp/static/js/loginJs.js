$(function () {
    $("form").submit(
        function () {
            return validateForm();
        }
    );
});


function validateForm() {
    let zh = $("#zh").val();
    let kl = $("#kl").val();

    if (zh != null && zh !== "" && kl != null && kl !== "") {
        return true;
    }

    if (zh == null || zh === "") {
        $("#zhMsg").html("请填写账号");
        $("#zhMsg").css("color", "red");
    }

    if (kl == null || kl === "") {
        $("#klMsg").html("请填写密码");
        $("#klMsg").css("color", "red");
    }

    return false;
}
