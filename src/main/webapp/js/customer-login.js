/**
 * Created by zengzy19585 on 2017/7/12.
 */
$(document).ready(function () {
    $("#submit").click(function () {
        var encrypt = $.md5($("#password").val());
        var map = {};
        map["password"] = encrypt;
        map["serial_number"] = $("#serial_number").val();
        var param = {userInfo:map};

        $.ajax({
            type: "POST",
            url: "/customer/post-customer-data-login.do",
            data: param,
            success: function (data) {
                $("#test").text(data);
            }
        });
    });
});