$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var sessionUserId = $.cookie("session_user_id");
    var orgId = $.cookie("session_org_id");
    var roleIds = $.cookie("session_role_ids");

    var path = window.document.location.href;
    var pos = path.lastIndexOf(":");
    var context = path.substring(0, pos);

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

    $.ajax({
        type: "GET",
        url: "/management/service/user/" + userId,
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
        url: "/management/service/user/" + userId + "/profile",
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
        url: "/management/service/user/" + userId + "/authorities",
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
        url: "/management/service/org/" + orgId + "/roles",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        dataType: 'json',
        success: function (res) {
            if (res.success == true) {
                $.ajax({
                    type: "GET",
                    url: "/management/service/user/" + userId + "/roles",
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                    },
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
        url: "/management/service/user/org",
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
            orgId: $("#orgId").val(),
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
            type: "PUT",
            url: "/management/service/user/" + userId,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
            },
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
    $.ajax({
        type: "GET",
        url: "/management/service/user/orgs",
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
