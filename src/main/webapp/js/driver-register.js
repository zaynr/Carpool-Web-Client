/**
 * Created by zengzy19585 on 2017/7/12.
 */
$(document).ready(function () {
    $("#submit").click(function () {
        var encrypt = $.md5($("#password").val());
        var map = {};
        map["password"] = encrypt;
        map["name"] = $("#name").val();
        map["mobile_number"] = $("#mobile_number").val();
        map["car_plate"] = $("#car_plate").val();
        var param = {userInfo:map};

        $.ajax({
            type: "POST",
            url: "/driver/post-register-data.do",
            data: param,
            success: function (data) {
                $("#test").text(data);
            }
        });
    });
});
