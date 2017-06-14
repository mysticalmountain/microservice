$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var data = {
        requestId: new Date().getTime()
    };
    var dataJson = JSON.stringify(data);

    queryServices(0, 5, null, null, null);
    /*
     $.ajax({
     type: "GET",
     url: "http://www.micro.com/permission/service/services?size=2",
     beforeSend: function (xhr) {
     xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
     },
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
     $("#services").append("<tr>" +
     "<td>" + item.id + "</td>" +
     "<td>" + item.code + "</td>" +
     "<td>" + item.content + "</td>" +
     "<td>" + item.path + "</td>" +
     "<td>" + item.method + "</td>" +
     "<td>" + item.isAudit + "</td>" +
     "<td></td>" +
     "</tr>");
     })
     var begin = data.currentPage - 2;
     var end = data.currentPage + 2;
     if (begin > 0) {
     $("#pages").append("<li><a name='pageNum' value = '" + (begin - 1) + "' href='#'>&laquo;</a></li>");
     }
     for (; begin <= end; begin++) {
     if (begin >= 0) {
     $("#pages").append("<li><a name='pageNum' value = '" + begin + "' href='#'>" + (begin + 1) + "</a></li>");
     }
     }
     if (data.totalPages - end > 0) {
     $("#pages").append("<li><a name='pageNum' value = '" + begin + "' href='#'>&raquo;</a></li>");
     }
     $("#loading").hide();
     }
     });
     */

    $("#checkAll").change(function () {
        if ($(this).attr('checked') == null) {
            $(this).attr('checked', true);
            $("input[name='resourceIds']").attr("checked", true);
        } else {
            $(this).attr('checked', false);
            $("input[name='resourceIds']").attr("checked", false);
        }
    });


    $("#search").click(function () {
        queryServices(0, 5);
    });

    $("#save").click(function () {
        $("#loading").show();
        var resourceIds = [];
        $('input[name="resourceIds"]:checked').each(function () {
            var newPermissionDto = {
                resourceId: $(this).val()
            }
            resourceIds.push(newPermissionDto);
        });
        var reqData = {
            requestId: new Date().getTime(),
            data: resourceIds
        }
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            url: "http://www.micro.com/permission/service/permissions",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
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
});

function queryServices(page, size) {
    var code = $("#code").val();
    var method = $("#method").val();
    var isAudit = $("#isAudit").val();
    var url = 'http://www.micro.com/permission/service/services';
    if (page != null && page != undefined) {
        url += "?page=" + page;
    }
    if (size != null && size != undefined && size != "") {
        url += "&size=" + size;
    }
    if (code != null && code != undefined && code != "") {
        url += "&code=" + code;
    }
    if (method != null && method != undefined && method != "") {
        url += "&method=" + method;
    }
    if (isAudit != null && isAudit != undefined && isAudit != "") {
        url += "&isAudit=" + isAudit;
    }
    $.ajax({
        type: "GET",
        // url: "http://www.micro.com/permission/service/services?page=" + page + "&size=" + size + "&code=" + code + "&method=" + method + "&isAudit=" + isAudit,
        url: url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
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
                $("#services").append("<tr>" +
                    "<td>" + item.id + "</td>" +
                    "<td>" + item.code + "</td>" +
                    "<td>" + item.content + "</td>" +
                    "<td>" + item.path + "</td>" +
                    "<td>" + item.method + "</td>" +
                    "<td>" + item.isAudit + "</td>" +
                    "<td></td>" +
                    "</tr>");
            })
            $("#pages").empty();
            var begin = data.currentPage - 2;
            var end = data.currentPage + 2;
            if (data.totalPages != 1) {
                if (begin > 0) {
                    $("#pages").append("<li><a name='pageNum' value = '" + (begin - 1) + "' href='javascript:queryServices(" + (begin - 1) + ")'>&laquo;</a></li>");
                }
                for (; begin <= end; begin++) {
                    if (begin >= 0 && begin < data.totalPages) {
                        if (begin == data.currentPage) {
                            $("#pages").append("<li><a name='pageNum' value = '" + begin + "' href='javascript:queryServices(" + begin + ")'><b>" + (begin + 1) + "</b></a></li>");
                        } else {
                            $("#pages").append("<li><a name='pageNum' value = '" + begin + "' href='javascript:queryServices(" + begin + ")'>" + (begin + 1) + "</a></li>");
                        }
                    }
                }
                if (data.totalPages - end > 0) {
                    $("#pages").append("<li><a name='pageNum' value = '" + begin + "' href='javascript:queryServices(" + (data.currentPage + 1) + ")'>&raquo;</a></li>");
                }
            }
        }
    });
}