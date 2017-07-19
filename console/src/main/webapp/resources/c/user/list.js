$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var sessionOrgId = $.cookie("session_org_id");

    $("#new").click(function () {
        location.href = 'new.html';
    });
    queryUsers(sessionOrgId);

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

    $("#search").click(function () {
        var orgId = $("#orgId").val();
        queryUsers(orgId);

    });
});


function queryUsers(orgId) {
    $.ajax({
        type: "GET",
        url: "/management/service/org/" + orgId + "/users",
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
            if (data.success == false) {
                $("#fail").html(data.errorMessage);
                $("#fail").show();
            } else {
                $("#users").empty();
                $.each(data.data, function (i, item) {
                    $("#users").append("<tr><td>" + item.id + "</td><td><a href='#' value='" + item.id + "'>" + item.name + "</a></td><td>" + item.username + "</td><td><button type='button' class='btn btn-default btn-sm' value='" + item.id + "'> <span class='glyphicon glyphicon-trash'></span> 删除 </button></td></tr>");
                });
                //删除按钮逻辑
                $('table td button.btn-default').click(function () {
                    $('#loading').show();
                    var but = $(this);
                    var idDto = {
                        id: $(this).val()
                    }
                    var reqData = {
                        requestId: new Date().getTime(),
                        data: idDto
                    };
                    var dataJson = JSON.stringify(reqData);
                    $.ajax({
                        type: "DELETE",
                        contentType: "application/json",
                        url: "/management/service/user/" + $(this).val(),
                        // data: dataJson,
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
                //编辑链接
                $("table td a").click(function () {
                    var id = $(this).attr('value');
                    $.cookie("user-edit-id", id);
                    location.href = 'edit.html';
                });
                $('#loading').hide();
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
