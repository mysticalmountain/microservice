$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var path = window.document.location.href;
    var pos = path.lastIndexOf(":");
    var context = path.substring(0, pos);

    var data = {
        requestId: new Date().getTime()
    };
    var dataJson = JSON.stringify(data);

    $("#loading").show();
    $.ajax({
        type: "GET",
        url: "/management/service/services/noPermission",
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
            }
            $("#services").empty();
            $.each(data.data, function (i, item) {
                var permissions = "<input name='exeResourceIds' type='checkbox' value='" + item.resourceDto.id + "'/> 执行";
                if (item.isAudit == 1) {
                    permissions += "&nbsp;<input name='auditResourceIds' type='checkbox' value='" + item.resourceDto.id + "'/> 审核";
                } else {
                    permissions += "&nbsp;<input name='auditResourceIds' type='checkbox' disabled value='" + item.resourceDto.id + "'/> 审核";
                }
                $("#services").append("<tr>" +
                    "<td>" + item.content + "</td>" +
                    "<td>" + item.code + "</td>" +
                    "<td>" + item.method + "</td>" +
                    "<td>" + item.path + "</td>" +
                    "<td>" + permissions + "</td>" +
                    "</tr>");

            })
            $("#loading").hide();
        }
    });

    $.ajax({
        type: "GET",
        url: "/management/service/list-menus/noPermission",
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
                var menus = '';
                $.each(res.data, function (i, item) {
                    if (item.pid == undefined || item.pid == null) {
                        menus += "<tr data-tt-id='" + item.id + "' >"
                    } else {
                        menus += "<tr data-tt-id='" + item.id + "' data-tt-parent-id='" + item.pid + "'>"
                    }
                    menus +=
                        "<td><input name='menuResourceIds' type='checkbox' value='" + item.resourceDto.id + "'/>" + item.resourceDto.id + "</td>" +
                        "<td>" + item.name + "</td>" +
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

    $("#checkAll").change(function () {
        if ($(this).attr('checked') == null || $(this).attr('checked') == undefined) {
            $(this).attr('checked', true);
            // $(this).applyAttr('checked')
            $("input[name='exeResourceIds']").attr("checked", true);
        } else {
            $(this).attr('checked', false);
            $("input[name='exeResourceIds']").attr("checked", false);
        }
    });


    $("#serviceSave").click(function () {
        $("#loading").show();
        var resources = [];
        $('input[name="exeResourceIds"]:checked').each(function () {
            var newPermissionDto = {
                resourceId: $(this).val(),
                operate: 'EXE'
            }
            resources.push(newPermissionDto);
        });
        $('input[name="auditResourceIds"]:checked').each(function () {
            var newPermissionDto = {
                resourceId: $(this).val(),
                operate: 'AUDIT'
            }
            resources.push(newPermissionDto);
        });
        var reqData = {
            requestId: new Date().getTime(),
            data: resources
        }
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            url: "/management/service/permission",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
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


    $("#menuSave").click(function () {
        $("#loading").show();
        var resources = [];
        $('input[name="menuResourceIds"]:checked').each(function () {
            var newPermissionDto = {
                resourceId: $(this).val(),
                operate: 'READ'
            }
            resources.push(newPermissionDto);
        });
        // $('input[name="auditResourceIds"]:checked').each(function () {
        //     var newPermissionDto = {
        //         resourceId: $(this).val(),
        //         operate: 'AUDIT'
        //     }
        //     resources.push(newPermissionDto);
        // });
        var reqData = {
            requestId: new Date().getTime(),
            data: resources
        }
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            url: "/management/service/permission",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
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
