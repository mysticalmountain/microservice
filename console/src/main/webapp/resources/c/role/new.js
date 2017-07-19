$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var sessionUserId = $.cookie("session_user_id");

    var path = window.document.location.href;
    var pos = path.lastIndexOf(":");
    var context = path.substring(0, pos);

    var data = {
        requestId: new Date().getTime()
    };
    var dataJson = JSON.stringify(data);

    $("#loading").show();

    queryPermissions(null, null);

    $("#search").click(function () {
        var code = $("#code").val();
        var name = $("#name").val();
        queryPermissions(code, name);
    });

    $.ajax({
        type: "GET",
        url: "/management/service/permissions/menus",
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
                        "<td><input type='checkbox' name='permissionIds' value='" + item.permissionId + "'/>" + item.name + "</td>" +
                        "<td>" + item.permissionId + "</td>" +
                        "<td>" + item.operate + "</td>" +
                        "</tr>"
                });
                $("#menus").append(menus);
                var setting = {
                    expandable: false
                }
                $("#mymenus").agikiTreeTable(setting);
            }
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
            name: $("#name").val(),
            orgId: $("#orgId").val(),
            permissionIds: permissionIds
        };
        var reqData = {
            requestId: new Date().getTime(),
            data: roleDto
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            url: "/management/service/role",
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
});

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
                        "<td><input type='checkbox' name='permissionIds' value='" + item.id + "'/>" + item.id + "</td>" +
                        "<td>" + item.resourceDto.id + "</td>" +
                        "<td>" + item.resourceDto.serviceDto.content + "</td>" +
                        "<td>" + item.resourceDto.serviceDto.code + "</td>" +
                        "<td>" + item.resourceDto.serviceDto.path + "</td>" +
                        "<td>" + item.operate + "</td>" +
                        "</tr>");
                });

                //删除按钮逻辑
                $('table td button.btn-default').click(function () {
                    $("#loading").show();
                    var but = $(this);
                    $.ajax({
                        type: "DELETE",
                        url: "/management/service/permissions/" + $(this).val(),
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
                });
                $("#loading").hide();
            }
        }
    });

}
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
