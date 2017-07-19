$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    path = window.document.location.href;
    pos = path.lastIndexOf(":");
    context = path.substring(0, pos);

    queryServices(0, 20, null, null, null);

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
        queryServices(0, 20);
    });

});

function queryServices(page, size) {
    var code = $("#code").val();
    var method = $("#method").val();
    var isAudit = $("#isAudit").val();
    var module = $("#module").val();
    var url =  '/management/service/services';
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
    if (module != null && module != undefined && module != "") {
        url += "&module=" + module;
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
        success: function (data) {
            $("#success").hide();
            $("#fail").hide();
            if (data.success == false) {
                $("#fail").html(data.errorMessage);
                $("#fail").show();
            }
            $("#services").empty();
            $.each(data.data, function (i, item) {
                var isAudit;
                if (item.isAudit == 0) {
                    isAudit = '否';
                } else {
                    isAudit = '是';
                }
                $("#services").append("<tr>" +
                    "<td>" + item.id + "</td>" +
                    "<td>" + item.code + "</td>" +
                    "<td><a href='#' value='" + item.id + "'>" + item.content + "</a></td>" +
                    "<td>" + item.path + "</td>" +
                    "<td>" + item.method + "</td>" +
                    "<td>" + isAudit + "</td>" +
                    "<td>" + item.module + "</td>" +
                    "</tr>");
            })
            //编辑链接逻辑
            $('table td a').click(function () {
                var id = $(this).attr('value');
                $.cookie("service-edit-id", id);
                location.href = 'edit.html';
            });
            //分页
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