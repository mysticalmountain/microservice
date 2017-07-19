$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var orgId = $.cookie("org-edit-id");

    $.ajax({
        type: "GET",
        url: "/management/service/org/" + orgId,
        dataType: 'json',
        success: function (res) {
            if (res.success == true) {
                $("#id").val(res.data.id);
                $("#name").val(res.data.name);
                $("#od").val(res.data.od);
            }
        }
    });

    $("#save").click(function () {
        var orgDto = {
            id: $("id").val(),
            name: $("#name").val(),
            od: $("#od").val()
        }

        var data = {
            requestId: '' + new Date().getTime(),
            data: orgDto
        }

        var dataJson = JSON.stringify(data);
        $.ajax({
            type: "PUT",
            url: "/management/service/org/" + orgId,
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
            success: function (res) {
                $("#success").hide();
                $("#fail").hide();
                if (res.success == true) {
                    $("#success").html(data.errorMessage);
                    $("#success").show();
                } else {
                    $("#fail").html(data.errorMessage);
                    $("#fail").show();
                }
            }
        });
    });
});
