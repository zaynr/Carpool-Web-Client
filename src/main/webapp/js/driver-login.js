/**
 * Created by zengzy19585 on 2017/7/12.
 */
$(document).ready(function () {
    $("#submit").click(function () {
        var encrypt = $.md5($("#password").val());
        var map = {};
        map["password"] = encrypt;
        map["mobile_number"] = $("#mobile_number").val();
        var param = {userInfo:map};

        $.ajax({
            type: "POST",
            url: "/driver/post-driver-data-login.do",
            data: param,
            success: function (data) {
                $("#test").text(data);
            }
        });
    });
});