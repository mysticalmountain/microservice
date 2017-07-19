$("#header").load("../../views/common/header.html");
$("#sidebar").load("../../views/common/sidebar.html");
$("#footer").load("../../views/common/footer.html");

var path = window.document.location.href;
var pos = path.lastIndexOf(":");
var context = path.substring(0, pos);

//初始化菜单列表
$.ajax({
    type: "GET",
    url: "/management/service/user-menus",
    beforeSend: function (xhr) {
        xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
    },
    xhrFields: {
        withCredentials: true
    },
    crossDomain: true,
    dataType: 'json',
    success: function (res) {
        var pathName = window.document.location.pathname;
        if (res.success == true) {
            var selectId;
            var selectMenuId;
            $.each(res.data.sons, function (i, item) {
                var menuStr = "<li id='" + item.id + "' class='treeview'>" +
                    "<a href='#'>" +
                    "<i class='fa fa-laptop'></i>" +
                    "<span>" + item.name + "</span>" +
                    "<span class='pull-right-container'>";
                if (item.sons.length > 0) {
                    menuStr += "<span class='label label-primary pull-right'>" + item.sons.length + "</span>";
                }
                menuStr += "</span></a>";
                if (item.sons.length > 0) {
                    menuStr += "<ul class='treeview-menu'>"
                    $.each(item.sons, function (j, sonItem) {
                        if (pathName == '/web' + sonItem.action) {
                            menuStr += "<li class='active' id='" + sonItem.id + "'>" + "<a class='active' href='/web" + sonItem.action + "'><i class='fa fa-circle-o'></i>" + sonItem.name + "</a></li>"
                            selectId = item.id;
                            selectMenuId = sonItem.id;
                        } else {
                            menuStr += "<li id='" + sonItem.id + "'><a href='/web" + sonItem.action + "'><i class='fa fa-circle-o'></i>" + sonItem.name + "</a></li>"
                        }
                    });
                    menuStr += "</ul>"
                }
                menuStr += "</li>";
                $("#sidebar-menu").append(menuStr);
            });
            if (selectId != null) {
                $.cookie("sidebar-treeview-id", selectId);
            } else {
                selectId = $.cookie("sidebar-treeview-id");
            }
            if (selectId != null && selectId != undefined) {
                document.getElementById(selectId).setAttribute("class", "treeview active");
            }

            if (selectMenuId != null) {
                $.cookie("sidebar-treeview-menu-id", selectMenuId);
            } else {
                selectMenuId = $.cookie("sidebar-treeview-menu-id");
            }
            if (selectMenuId != null && selectMenuId != undefined) {
                document.getElementById(selectMenuId).setAttribute("class", "active");
            }
        }
    }
});

var session_user_id = $.cookie("session_user_id");
var session_org_id = $.cookie("session_org_id");
var session_role_ids = $.cookie("session_role_ids");
if (session_user_id == null || session_org_id == null || session_role_ids == null) {
    $.ajax({
        type: "GET",
        url: "/management/service/user/session",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (res) {
            if (res.success = true) {
                $.cookie("session_user_id", res.data.userId);
                $.cookie("session_org_id", res.data.orgId);
                $.cookie("session_role_ids", res.data.roleIds);
            }
        }
    });
}
