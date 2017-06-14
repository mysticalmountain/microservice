$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var roleId = $.cookie("role-edit-id");
    $("#id").val(roleId);
    var queryRoleDto = {
        id: roleId
    }
    var data = {
        requestId: new Date().getTime(),
        data: queryRoleDto
    };
    var dataJson = JSON.stringify(data);

    $("#loading").show();
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://www.micro.com/permission/service/roles/" + roleId,
        dataType: 'json',
        success: function (res) {
            $("#success").hide();
            $("#fail").hide();
            if (res.success == false) {
                $("#fail").html(res.errorMessage);
                $("#fail").show();
            } else {
                $("#name").val(res.data.name);
            }
        }
    });

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://www.micro.com/permission/service/permissions/service",
        dataType: 'json',
        success: function (data) {
            $("#success").hide();
            $("#fail").hide();
            if (data.success == false) {
                $("#fail").html(data.errorMessage);
                $("#fail").show();
            } else {
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "http://www.micro.com/permission/service/roles/" + roleId + "/permissions",
                    dataType: 'json',
                    success: function (res) {
                        $("#services").empty();
                        $.each(data.data, function (i, item) {
                            var checked = false;
                            $.each(res.data, function (j, ps) {
                                if (ps.id == item.permissionId) {
                                    checked = true;
                                }
                            })
                            if (checked) {
                                $("#services").append("<tr><td><input type='checkbox' name='permissionIds' checked value='" + item.permissionId + "'/>" + item.permissionId + "</td><td>" + item.code + "</td><td>" + item.content + "</td><td>" + item.system + "</td><td>" + item.module + "</td></tr>");
                            } else {
                                $("#services").append("<tr><td><input type='checkbox' name='permissionIds' value='" + item.permissionId + "'/>" + item.permissionId + "</td><td>" + item.code + "</td><td>" + item.content + "</td><td>" + item.system + "</td><td>" + item.module + "</td></tr>");
                            }
                        })
                    }
                });
            }
            $("#loading").hide();
        }
    });

    $("#checkAll").change(function () {
        if ($(this).attr('checked') == null) {
            $(this).attr('checked', true);
            $("input[name='permissionIds']").attr("checked", true);
        } else {
            $(this).attr('checked', false);
            $("input[name='permissionIds']").attr("checked", false);
        }
    });


    $("#save").click(function () {
        $("#loading").show();
        var permissionIds = [];
        $('input[name="permissionIds"]:checked').each(function () {
            permissionIds.push($(this).val());
        });
        var roleDto = {
            id: roleId,
            name: $("#name").val(),
            permissionIds: permissionIds
        };
        var reqData = {
            requestId: new Date().getTime(),
            data: roleDto
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "PATCH",
            contentType: "application/json",
            url: "http://www.micro.com/permission/service/roles/" + roleId,
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
