$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var path = window.document.location.href;
    var pos = path.lastIndexOf(":");
    var context = path.substring(0, pos);

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
        url: "/management/service/role/" + roleId,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (res) {
            $("#success").hide();
            $("#fail").hide();
            if (res.success == false) {
                $("#fail").html(res.errorMessage);
                $("#fail").show();
            } else {
                $("#name").val(res.data.name);
                $.ajax({
                    type: "GET",
                    url: "/management/service/org/" + res.data.orgId,
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                    },
                    xhrFields: {
                        withCredentials: true
                    },
                    crossDomain: true,
                    dataType: 'json',
                    success: function (res) {
                        $("#orgName").val(res.data.name);
                        $("#orgId").val(res.data.id);
                    }
                });
            }
        }
    });


    $.ajax({
        type: "GET",
        url: "/management/service/role/" + roleId + "/permissions",
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (res) {
            if (res.success == false) {
                $("#fail").html(data.errorMessage);
                $("#fail").show();
            } else {
                //服务列表
                $.ajax({
                    type: "GET",
                    url: "/management/service/permissions/services",
                    xhrFields: {
                        withCredentials: true
                    },
                    crossDomain: true,
                    dataType: 'json',
                    success: function (data) {
                        $("#services").empty();
                        $.each(data.data, function (i, item) {
                            var checked = false;
                            $.each(res.data, function (j, ps) {
                                if (ps.id == item.permissionId) {
                                    checked = true;
                                }
                            })
                            if (checked) {
                                $("#services").append("<tr>" +
                                    "<td><input type='checkbox' name='permissionIds' checked value='" + item.permissionId + "'/>" + item.permissionId + "</td>" +
                                    "<td>" + item.resourceId + "</td>" +
                                    "<td>" + item.content + "</td>" +
                                    "<td>" + item.code + "</td>" +
                                    "<td>" + item.path + "</td>" +
                                    "<td>" + item.operate + "</td>" +
                                    "</tr>");
                            } else {
                                $("#services").append("<tr>" +
                                    "<td><input type='checkbox' name='permissionIds' value='" + item.permissionId + "'/>" + item.permissionId + "</td>" +
                                    "<td>" + item.resourceId + "</td>" +
                                    "<td>" + item.content + "</td>" +
                                    "<td>" + item.code + "</td>" +
                                    "<td>" + item.path + "</td>" +
                                    "<td>" + item.operate + "</td>" +
                                    "</tr>");
                            }
                        });
                    }
                });
                //菜单列表
                $.ajax({
                    type: "GET",
                    url: "/management/service/permissions/menus",
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                    },
                    xhrFields: {
                        withCredentials: true
                    },
                    crossDomain: true,
                    dataType: 'json',
                    success: function (data) {
                        if (data.success == true) {
                            $("#menus").empty();
                            var menus = '';
                            $.each(data.data, function (i, item) {
                                var checked = false;
                                $.each(res.data, function (j, ps) {
                                    if (ps.id == item.permissionId) {
                                        checked = true;
                                    }
                                });
                                if (item.pid == undefined || item.pid == null) {
                                    menus += "<tr data-tt-id='" + item.id + "' >"
                                } else {
                                    menus += "<tr data-tt-id='" + item.id + "' data-tt-parent-id='" + item.pid + "'>"
                                }
                                if (checked) {
                                    menus +=
                                        "<td><input type='checkbox' name='permissionIds' checked value='" + item.permissionId + "'/>" + item.name + "</td>" +
                                        "<td>" + item.permissionId + "</td>" +
                                        "<td>" + item.operate + "</td>" +
                                        "</tr>"
                                } else {
                                    menus +=
                                        "<td><input type='checkbox' name='permissionIds' value='" + item.permissionId + "'/>" + item.name + "</td>" +
                                        "<td>" + item.permissionId + "</td>" +
                                        "<td>" + item.operate + "</td>" +
                                        "</tr>"
                                }
                            });
                            $("#menus").append(menus);
                            var setting = {
                                expandable: false
                            }
                            $("#mymenus").agikiTreeTable(setting);
                        }
                    }
                });
            }
        }
    });

    var setting = {
        check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true,
                pIdKey: "pid"
            }
        },
        callback: {
            onClick: onClick,
            onCheck: onCheck
        }
    };
    $.ajax({
        type: "GET",
        url: "/management/service/user/orgs",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (res) {
            if (res.success == true) {
                $.fn.zTree.init($("#treeDemo"), setting, res.data);
            }
        }
    });
    $("#orgName").click(function () {
        var cityObj = $("#orgName");
        var cityOffset = $("#orgName").offset();
        $("#menuContent").css({left: cityOffset.left + "px", top: cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    });

    // $.ajax({
    //     type: "GET",
    //     url: "/zuul/permission/service/permissions/menus",
    //     dataType: 'json',
    //     success: function (res) {
    //         if (res.success == true) {
    //             var menus = '';
    //             $.each(res.data, function (i, item) {
    //                 if (item.pid == undefined || item.pid == null) {
    //                     menus += "<tr data-tt-id='" + item.id + "' >"
    //                 } else {
    //                     menus += "<tr data-tt-id='" + item.id + "' data-tt-parent-id='" + item.pid + "'>"
    //                 }
    //                 menus +=
    //                     "<td><input type='checkbox' name='permissionIds' value='" + item.permissionId + "'/>" + item.name + "</td>" +
    //                     "<td>" + item.permissionId + "</td>" +
    //                     "<td>" + item.operate + "</td>" +
    //                     "</tr>"
    //             });
    //             $("#menus").append(menus);
    //             var setting = {
    //                 expandable: false
    //             }
    //             $("#mymenus").agikiTreeTable(setting);
    //         }
    //     }
    // });

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
            permissionIds: permissionIds,
            orgId: $("#orgId").val()
        };
        var reqData = {
            requestId: new Date().getTime(),
            data: roleDto
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "PUT",
            url: "/management/service/role/" + roleId,
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
                $("#loading").hide();
            }
        });
    });
});
function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
    return false;
}
function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getCheckedNodes(true),
        v = "";
    var pid = "";
    for (var i = 0, l = nodes.length; i < l; i++) {
        v += nodes[i].name + ",";
        pid += nodes[i].id;
    }
    if (v.length > 0) v = v.substring(0, v.length - 1);
    $("#orgName").val(v)
    $("#orgId").val(pid);
}

function showMenu() {
    var cityObj = $("#orgName");
    var cityOffset = $("#orgName").offset();
    $("#menuContent").css({left: cityOffset.left + "px", top: cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "orgName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
        hideMenu();
    }
}
