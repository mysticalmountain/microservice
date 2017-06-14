$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var data = {
        requestId: new Date().getTime()
    };
    var dataJson = JSON.stringify(data);

    $("#loading").show();
    $.ajax({
        type: "GET",
        url: "http://www.micro.com/permission/service/services/noPermission",
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
                $("#services").append("<tr><td>" + item.resourceDto.id + "</td></td><td>" + item.id + "</td><td>" + item.code + "</td><td>" + item.content + "</td><td><input name='resourceIds' type='checkbox' value='" + item.resourceDto.id + "' </td></tr>");
            })
            $("#loading").hide();
        }
    });

    $("#checkAll").change(function () {
        if ($(this).attr('checked') == null) {
            $(this).attr('checked', true);
            $("input[name='resourceIds']").attr("checked", true);
        } else {
            $(this).attr('checked', false);
            $("input[name='resourceIds']").attr("checked", false);
        }
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
