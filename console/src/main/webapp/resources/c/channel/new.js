$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var sessionUserId = $.cookie("session_user_id");

    queryPermissions(null, null);

    $("#saveChannel").click(function () {
        var channelDto = {
            name: $("#name").val(),
            code: $("#code").val()
        };
        var reqData = {
            requestId: new Date().getTime(),
            data: channelDto
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            url: "/management/service/channel",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
            },
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
                    $("#channelId").val(data.data.id);
                    $("#success").html(data.errorMessage);
                    $("#success").show();
                } else {
                    $("#fail").html(data.errorMessage);
                    $("#fail").show();
                }
            }
        });
    });

    $("#saveChannelPermission").click(function () {
        var id = $("#channelId").val();
        var permissionIds = [];
        $('input[name="permissionIds"]:checked').each(function () {
            permissionIds.push($(this).val());
        });

        var reqData = {
            requestId: new Date().getTime(),
            data: permissionIds
        };
        var dataJson = JSON.stringify(reqData);
        alert(dataJson);
        $.ajax({
            type: "PUT",
            url: "/management/service/channel/" + id + "/permissions",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
            },
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
                } else {
                    $("#fail").html(data.errorMessage);
                    $("#fail").show();
                }
            }
        });
    });

    $("#search").click(function () {
        var code = $("#queryCode").val();
        var name = $("#queryName").val();
        var method = $("#method").val();
        var isAudit = $("#isAudit").val();
        var module = $("#module").val();
        queryPermissions(code, name, method, isAudit, module);
    });
});
/*
function queryPermissions(code, name) {
    var url = "/management/service/permissions/services";
    var flag = false;
    if (code != null && code != undefined) {
        url += "?code=" + code;
        flag = true;
    }
    if (name != null && name != undefined) {
        if (flag) {
            url += "&name=" + name;
        } else {
            url += "?name=" + name;
        }
    }
    $.ajax({
        type: "GET",
        url: url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (res) {
            if (res.success == false) {
                $("#fail").html(res.errorMessage);
                $("#fail").show();
                $("#loading").hide();
            } else {
                $("#permissions").empty();
                $.each(res.data, function (i, item) {
                    $("#permissions").append("<tr>" +
                        "<td><input type='checkbox' name='permissionIds' value='" + item.permissionId + "'>" + item.permissionId + "</td>" +
                        "<td>" + item.resourceId + "</td>" +
                        "<td>" + item.content + "</td>" +
                        "<td>" + item.code + "</td>" +
                        "<td>" + item.path + "</td>" +
                        "<td>" + item.operate + "</td>" +
                        "</tr>");
                });
                $('table td button.btn-default').click(function () {
                    deletePermission($(this));
                });
                $("#loading").hide();
            }
        }
    });
}
*/

function queryPermissions(code, name, method, isAudit, module) {
    var url = "/management/service/permissions/services";
    var flag = false;
    if (code != null && code != undefined) {
        if (flag) {
            url += "&code=" + code;
        } else {
            url += "?code=" + code;
            flag = true;
        }
    }
    if (name != null && name != undefined) {
        if (flag) {
            url += "&name=" + name;
        } else {
            url += "?name=" + name;
            flag = true;
        }
    }
    if (method != null && method != undefined) {
        if (flag) {
            url += "&method=" + method;
        } else {
            url += "?method=" + method;
            flag = true;
        }
    }
    if (isAudit != null && isAudit != undefined) {
        if (flag) {
            url += "&isAudit=" + isAudit;
        } else {
            url += "?isAudit=" + isAudit;
            flag = true;
        }
    }
    if (module != null && module != undefined) {
        if (flag) {
            url += "&module=" + module;
        } else {
            url += "?module=" + module;
            flag = true;
        }
    }
    $.ajax({
        type: "GET",
        url: url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (res) {
            if (res.success == false) {
                $("#fail").html(res.errorMessage);
                $("#fail").show();
                $("#loading").hide();
            } else {
                $("#permissions").empty();
                $.each(res.data, function (i, item) {
                    $("#permissions").append("<tr>" +
                        "<td>" + item.permissionId + "</td>" +
                        "<td>" + item.resourceId + "</td>" +
                        "<td>" + item.content + "</td>" +
                        "<td>" + item.code + "</td>" +
                        "<td>" + item.path + "</td>" +
                        "<td>" + item.operate + "</td>" +
                        "<td>" + item.module + "</td>" +
                        "<td><button value='" + item.id + "' type='button' class='btn btn-default btn-sm' > <span class='glyphicon glyphicon-trash'></span> 删除 </button></td>" +
                        "</tr>");
                });
                $('table td button.btn-default').click(function () {
                    deletePermission($(this));
                });
                $("#loading").hide();
            }
        }
    });
}