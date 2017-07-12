/**
 * Created by zaynr on 2017/7/11.
 */
$(document).ready(function () {
    $("#submit").click(function () {
        var encrypt = $.md5($("#password").val());
        var map = {};
        map["password"] = encrypt;
        map["name"] = $("#name").val();
        map["mobile_number"] = $("#mobile_number").val();
        map["serial_number"] = $("#serial_number").val();
        var param = {userInfo:map};

        $.ajax({
            type: "POST",
            url: "/customer/post-customer-data.do",
            data: param,
            success: function (data) {
                $("#test").text(data);
            }
        });
    });
});
