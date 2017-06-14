$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    $("#new").click(function () {
        location.href = 'new.html';
    });

    var data = {};
    var reqData = {
        requestId: new Date().getTime(),
    };
    var dataJson = JSON.stringify(reqData);
    $("#loading").show();
    $.ajax({
        type: "GET",
        url: "http://www.micro.com/permission/service/permissions",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        dataType: 'json',
        success: function (res) {
            if (res.success == false) {
                $("#fail").html(res.errorMessage);
                $("#fail").show();
                $("#loading").hide();
            } else {
                $.each(res.data, function (i, item) {
                    $("#permissions").append("<tr><td>" + item.id + "</td><td>" + item.resourceDto.id + "</td><td>" + item.resourceDto.serviceDto.content + "</td><td>" + item.resourceDto.serviceDto.path + "</td><td><button value='" + item.id + "' type='button' class='btn btn-default btn-sm' > <span class='glyphicon glyphicon-trash'></span> 删除 </button></td></tr>");
                });

                //删除按钮逻辑
                $('table td button.btn-default').click(function () {
                    $("#loading").show();
                    var but = $(this);
                    $.ajax({
                        type: "DELETE",
                        url: "http://www.micro.com/permission/service/permissions/" + $(this).val(),
                        dataType: 'json',
                        success: function (data) {
                            $("#success").hide();
                            $("#fail").hide();
                            if (data.success == true) {
                                but.parent().parent().remove();
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
                $("#loading").hide();
            }
        }
    });
});

