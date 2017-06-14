$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var userId = $.cookie("user-edit-id");
    $("#id").val(userId);
    var queryUserDto = {
        id: userId
    }
    var data = {
        requestId: '' + new Date().getTime(),
        data: queryUserDto
    };
    var dataJson = JSON.stringify(data);

    // $("#loading").show();
    $.ajax({
        type: "GET",
        url: "http://www.micro.com/user/service/users/" + userId,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        dataType: 'json',
        success: function (data) {
            $("#name").val(data.data.name);
            $("#username").val(data.data.username);
        }
    });
    // 查询用户简介
    $.ajax({
        type: "GET",
        url: "http://www.micro.com/user/service/users/" + userId + "/profiles",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            $("#profileId").val(data.data.id);
            $("#age").val(data.data.age);
            $("#address").val(data.data.address);
            var options = $('select option');
            $.each(options, function () {
                if ($(this).val() == data.data.sex) {
                    $(this).attr('selected', true);
                }
            });
        }
    });
    //查询用户权限
    $.ajax({
        type: "GET",
        url: "http://www.micro.com/user/service/users/" + userId + "/authorities",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            $("#authorityId").val(data.data[0].id);
            $("#accountNo").val(data.data[0].accountNo);
            $("#password").val(data.data[0].password);
        }
    });

    //查询用户角色
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://www.micro.com/permission/service/roles",
        dataType: 'json',
        success: function (res) {
            if (res.success == true) {
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "http://www.micro.com/user/service/users/" + userId + "/roles",
                    dataType: 'json',
                    success: function (res1) {
                        $.each(res.data, function (i, item) {
                            var isCheck = false;
                            $.each(res1.data, function (i, itemUser) {
                                if (item.id == itemUser.roleId) {
                                    isCheck = true;
                                }
                            });
                            if (isCheck) {
                                $("#roles").append("<label class='checkbox-inline'><input type='checkbox' name='roleIds' checked value='" + item.id + "'>" + item.name + "</label>");
                            } else {
                                $("#roles").append("<label class='checkbox-inline'><input type='checkbox' name='roleIds' value='" + item.id + "'>" + item.name + "</label>");
                            }
                        });

                    }
                });
            }
        }
    });

    $("#save").click(function () {
        $("#loading").show();
        var profileDto = {
            id: $("#profileId").val(),
            age: $("#age").val(),
            sex: $("#sex").val(),
            address: $("#address").val()
        };
        var authorityDto = {
            id: $("#authorityId").val(),
            accountNo: $("#accountNo").val(),
            password: $("#password").val()
        };
        var roleIds = $("*[name='roleIds']");
        var roleDtos = new Array();
        var chk_value = [];
        $('input[name="roleIds"]:checked').each(function () {
            chk_value.push($(this).val());
            var roleDto = {
                roleId: $(this).val(),
                name: ''
            };
            roleDtos.push(roleDto);
        });
        var userDto = {

            id: userId,
            name: $("#name").val(),
            username: $("#username").val(),
            userType: 'PERSONAL',
        };
        var editUserDto = {
            userDto: userDto,
            profileDto: profileDto,
            authorityDto: authorityDto,
            roleDtos: roleDtos
        };
        var reqData = {
            requestId: '' + new Date().getTime(),
            data: editUserDto
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "PATCH",
            contentType: "application/json",
            url: "http://www.micro.com/user/service/users/" + userId,
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
