$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var channelId = $.cookie("channel-edit-id");

    $.ajax({
        type: "GET",
        url: "/management/service/channel/" + channelId,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
            xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (res) {
            if (res.success == true) {
                $("#code").val(res.data.code);
                $("#name").val(res.data.name);
                $("#channelId").val(res.data.id);
            }
        }
    });


    queryPermissions(channelId, null, null);

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

    $("#checkAll").change(function () {
        if ($(this).attr('checked') == null || $(this).attr('checked') == undefined) {
            $(this).attr('checked', true);
            $("input[name='permissionIds']").attr("checked", true);
        } else {
            $(this).attr('checked', false);
            $("input[name='permissionIds']").attr("checked", false);
        }
    });
});

function queryPermissions(channelId, code, name) {
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
        url: "/management/service/channel/" + channelId + "/permissions",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
            xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            var myPermissions;
            if (data.success == true) {
                myPermissions = data.data;
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
                                var checked = false;
                                $.each(myPermissions, function (j, item1) {
                                    if (item1.id == item.permissionId) {
                                        checked = true;
                                    }
                                });
                                if (checked) {
                                    $("#permissions").append("<tr>" +
                                        "<td><input type='checkbox' checked name='permissionIds' value='" + item.permissionId + "'>" + item.permissionId + "</td>" +
                                        "<td>" + item.resourceId + "</td>" +
                                        "<td>" + item.content + "</td>" +
                                        "<td>" + item.code + "</td>" +
                                        "<td>" + item.path + "</td>" +
                                        "<td>" + item.operate + "</td>" +
                                        "</tr>");

                                } else {
                                    $("#permissions").append("<tr>" +
                                        "<td><input type='checkbox' name='permissionIds' value='" + item.permissionId + "'>" + item.permissionId + "</td>" +
                                        "<td>" + item.resourceId + "</td>" +
                                        "<td>" + item.content + "</td>" +
                                        "<td>" + item.code + "</td>" +
                                        "<td>" + item.path + "</td>" +
                                        "<td>" + item.operate + "</td>" +
                                        "</tr>");

                                }
                            });
                        }
                    }
                });
            }
        }
    });
}
