/**
 * Created by zaynr on 2017/7/11.
 */
$(document).ready(function () {
    $("#submit").click(function () {
        var foo = $("#password").val();
        var encrypt = $.md5(foo);
        $.ajax({
            type: "POST",
            url: "/test/post-data.do",
            data: encrypt,
            success: function (data) {
                $("#test").text(data);
            }
        });
    });
});
