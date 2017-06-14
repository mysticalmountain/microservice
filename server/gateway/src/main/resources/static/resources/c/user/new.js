$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var data = {
        requestId: '' + new Date().getTime()
    };
    var dataJson = JSON.stringify(data);

    $("#loading").show();
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://www.micro.com/permission/service/roles",
        data: dataJson,
        dataType: 'json',
        success: function (data) {
            $("#success").hide();
            $("#fail").hide();
            if (data.success == true) {
                $("#roles").empty();
                $.each(data.data, function (i, item) {
                    $("#roles").append("<label class='checkbox-inline'><input type='checkbox' name='roleIds' value='" + item.id +  "'>" + item.name + "</label>");
                });
            } else {
                $("#fail").html(data.errorMessage);
                $("#fail").show();
            }
            $("#loading").hide();
        }
    });


    $("#save").click(function () {
        $("#loading").show();
        var profileDto = {
            age: $("#age").val(),
            sex: $("#sex").val(),
            address: $("#address").val()
        };
        var authorityDto = {
            accountNo: $("#accountNo").val(),
            password: $("#password").val()
        };
        var roleIds = $("*[name='roleIds']");
        var roleDtos = new Array();
        var chk_value =[];
        $('input[name="roleIds"]:checked').each(function() {
            chk_value.push($(this).val());
            var roleDto = {
                roleId: $(this).val(),
                name: ''
            };
            roleDtos.push(roleDto);
        });
        var userDto = {
            name: $("#name").val(),
            username: $("#username").val(),
            userType: 'PERSONAL',
        }
        var data = {
            userDto: userDto,
            profileDto: profileDto,
            authorityDto: authorityDto,
            roleDtos: roleDtos
        };
        var reqData = {
            requestId: '' + new Date().getTime(),
            data: data
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://www.micro.com/user/service/users",
            data: dataJson,
            dataType: 'json',
            success: function (data) {
                $("#success").hide();
                $("#fail").hide();
                if (data.success == true) {
                    $("#success").html(data.errorMessage);
                    $("#success").show();
                } else {
                    $("#fail").html(data.errorMessage);
                    $("#fail").show();
                }
                $("#loading").hide();
            }
        });
    });
});
