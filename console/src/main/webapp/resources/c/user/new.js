$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var userId = $.cookie("session_user_id");
    var orgId = $.cookie("session_org_id");
    var roleIds = $.cookie("session_role_ids");

    var data = {
        requestId: '' + new Date().getTime()
    };
    var dataJson = JSON.stringify(data);

    $("#loading").show();
    $.ajax({
        type: "GET",
        url: "/management/service/org/" + orgId + "/roles",
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
                $("#roles").empty();
                $.each(data.data, function (i, item) {
                    $("#roles").append("<label class='checkbox-inline'><input type='checkbox' name='roleIds' value='" + item.id + "'>" + item.name + "</label>");
                });
            } else {
                $("#fail").html(data.errorMessage);
                $("#fail").show();
            }
            $("#loading").hide();
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
            name: $("#name").val(),
            username: $("#username").val(),
            orgId: $("#orgId").val(),
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
            url: "/management/service/user",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
            },
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            dataType: 'json',
            data: dataJson,
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
