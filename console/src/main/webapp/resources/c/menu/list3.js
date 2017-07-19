$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var path = window.document.location.href;
    var pos = path.lastIndexOf(":");
    var context = path.substring(0, pos);

    $.ajax({
        type: "GET",
        url: context + "/permission/service/menus/list",
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
                    menus += "<td>" + item.id + "</td>" +
                        "<td>" + item.name + "</td>" +
                        "<td>" + item.action + "</td>" +
                        "</tr>"
                });
                $("#menus").append(menus);
                var setting = {
                    // persist: true,
                    // persistStoreName: "files",
                    expandable: false
                }
                $("#mymenus").agikiTreeTable(setting);
            }
        }
    });


});



