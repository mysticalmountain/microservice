$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    $("#new").click(function () {
        location.href = 'new.html';
    });

    var data = {};
    var reqData = {
        requestId: new Date().getTime(),
    };

    queryPermissions(null, null);

    $("#loading").show();
    $("#search").click(function () {
        var code = $("#code").val();
        var name = $("#name").val();
        var method = $("#method").val();
        var isAudit = $("#isAudit").val();
        var module = $("#module").val();
        queryPermissions(code, name, method, isAudit, module);
    });


    $.ajax({
        type: "GET",
        url: "/management/service/permissions/menus",
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (res) {
            if (res.success == true) {
                var menus = '';
                $.each(res.data, function (i, item) {
                    if (item.pid == undefined || item.pid == null) {
                        menus += "<tr data-tt-id='" + item.id + "' >"
                    } else {
                        menus += "<tr data-tt-id='" + item.id + "' data-tt-parent-id='" + item.pid + "'>"
                    }
                    menus +=
                        "<td>" + item.permissionId + "</td>" +
                        "<td>" + item.name + "</td>" +
                        "<td>" + item.operate + "</td>" +
                        "<td><button value='" + item.permissionId + "' type='button' class='btn btn-default btn-sm' > <span class='glyphicon glyphicon-trash'></span> 删除 </button></td>" +
                        "</tr>"
                });
                $("#menus").append(menus);
                var setting = {
                    // persist: true,
                    // persistStoreName: "files",
                    expandable: false
                }
                $("#mymenus").agikiTreeTable(setting);

                $('table td button.btn-default').click(function () {
                    deletePermission($(this));
                });
            }
        }
    });

});

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

function deletePermission(but) {
    $.ajax({
        type: "DELETE",
        url: "/management/service/permission/" + but.val(),
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            $("#success").hide();
            $("#fail").hide();
            if (data.success == true) {
                but.parent().parent().remove();
                $("#success").html(data.errorMessage);
                $("#success").show();
            } else {
                $("#fail").html(data.errorMessage);
                $("#fail").show();
            }
            $("#loading").hide();
        }
    });

}
