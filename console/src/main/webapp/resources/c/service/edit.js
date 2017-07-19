$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    path = window.document.location.href;
    pos = path.lastIndexOf(":");
    contextPth = path.substring(0, pos);

    var serviceId = $.cookie("service-edit-id");
    $("#id").val(serviceId);

    $("#loading").show();
    $.ajax({
        type: "GET",
        url: "/management/service/service/" + serviceId,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (res) {
            $("#success").hide();
            $("#fail").hide();
            if (res.success == false) {
                $("#fail").html(res.errorMessage);
                $("#fail").show();
            } else {
                $("#name").val(res.data.content);
                $("#code").val(res.data.code);
                $("#path").val(res.data.path);
                $("#method").val(res.data.method);
                $("#isAudit").val(res.data.isAudit);
                $("#module").val(res.data.module);
                $("input[name=isAudit]").val();
                if (res.data.isAudit == 1) {
                    // $("#isAudits").append(
                    //     "<input type='radio' name='isAudit' value='1' checked> 是" +
                    //     "<input type='radio' name='isAudit' value='0'> 否"
                    // )
                    $("#isAudits :radio[value=1]").attr("checked", true);
                } else {
                    // $("#isAudits").append(
                    //     "<input type='radio' name='isAudit' value='1'> 是" +
                    //     "<input type='radio' name='isAudit' value='0' checked> 否"
                    // )
                    $("#isAudits :radio[value=0]").attr("checked", true);
                }
            }
        }
    });


    $("#save").click(function () {
        var serviceDto = {
            code: $("#code").val(),
            content: $("#name").val(),
            method: $("#method").val(),
            path: $("#path").val(),
            // isAudit: $('#isAudit').val(),
            isAudit: $('input:radio[name="isAudit"]:checked').val(),
            module: $("#module").val()
        };
        var reqData = {
            requestId: new Date().getTime(),
            data: serviceDto
        };
        var dataJson = JSON.stringify(reqData);
        $.ajax({
            type: "PUT",
            url: "/management/service/service/" + serviceId,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
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
