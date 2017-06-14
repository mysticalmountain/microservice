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
        url: "http://www.micro.com/permission/service/roles",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (data) {
            if (data.success == false) {
                $("#fail").html(data.errorMessage);
                $("#fail").show();
            } else {
                $.each(data.data, function (i, item) {
                    $("#roles").append("<tr><td>" + item.id + "</td><td><a href='#' value='" + item.id + "'>" + item.name + "</a></td><td><button value='" + item.id + "' type='button' class='btn btn-default btn-sm' > <span class='glyphicon glyphicon-trash'></span> 删除 </button></td></tr>");
                });

                //删除按钮逻辑
                $('table td button.btn-default').click(function () {
                    $("#loading").show();
                    var but = $(this);
                    var idDto = {
                        id: $(this).val()
                    }
                    var reqData = {
                        requestId: new Date().getTime(),
                        data: idDto
                    };
                    var dataJson = JSON.stringify(reqData);
                    $.ajax({
                        type: "DELETE",
                        url: "http://www.micro.com/permission/service/roles/" + $(this).val(),
                        // beforeSend: function (xhr) {
                        //     xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                        // },
                        // xhrFields: {
                        //     withCredentials: true
                        // },
                        // crossDomain: true,
                        // data: dataJson,
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
                //编辑链接逻辑
                $('table td a').click(function () {
                    var id = $(this).attr('value');
                    $.cookie("role-edit-id", id);
                    location.href = 'edit.html';
                });
                $("#loading").hide();
            }
        }
    });
});

