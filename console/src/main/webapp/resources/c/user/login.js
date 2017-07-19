$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");


    $("#login").click(function () {
        var loginDto = {
            username: $("#username").val(),
            password: $("#password").val()
        }
        var data = {
            requestId: '' + new Date().getTime(),
            data: loginDto
        };
        var dataJson = JSON.stringify(data);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/management/service/login",
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            data: dataJson,
            dataType: 'json',
            success: function (data) {
                $("#success").hide();
                $("#fail").hide();
                if (data.success == true) {
                    $("#success").html(data.errorMessage);
                    $("#success").show();
                    location.href = 'list.html';
                } else {
                    $("#fail").html(data.errorMessage);
                    $("#fail").show();
                }
            }
        });
    });
});
