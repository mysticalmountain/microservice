$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var data = {
        requestId: new Date().getTime()
    };
    var dataJson = JSON.stringify(data);

    $("#loading").show();
    $.ajax({
        type: "GET",
        url: "http://www.micro.com/permission/service/permissions/service",
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
                $("#services").append("<tr><td><input type='checkbox' name='permissionIds' value='" + item.permissionId + "'/>" + item.permissionId + "</td><td>" + item.code + "</td><td>" + item.content + "</td><td>" + item.system + "</td><td>" + item.module + "</td></tr>");
            })
            $("#loading").hide();
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
        var permissionIds =[];
        $('input[name="permissionIds"]:checked').each(function() {
            permissionIds.push($(this).val());
        });
        var roleDto = {
            name: $("#name").val(),
            permissionIds: permissionIds
        };
        var reqData = {
            requestId: new Date().getTime(),
            data: roleDto
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "POST",
            url: "http://www.micro.com/permission/service/roles",
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
