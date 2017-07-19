$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    $("#new").click(function () {
        location.href = 'new.html';
    });

    $.ajax({
        type: "GET",
        url: "/management/service/channels",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
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
                $.each(res.data, function (i, item) {
                    $("#channels").append("<tr>" +
                        "<td>" + item.id + "</td>" +
                        "<td><a href='#' value='" + item.id + "'>" + item.name + "</a></td>" +
                        "<td>" + item.code + "</td>" +
                        "<td><button value='" + item.id + "' type='button' class='btn btn-default btn-sm' > <span class='glyphicon glyphicon-trash'></span> 删除 </button></td>" +
                        "</tr>");

                })
                $('table td button.btn-default').click(function () {
                    var but = $(this);
                    $.ajax({
                        type: "DELETE",
                        url: "/management/service/channel/" + $(this).val(),
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
                //编辑链接
                $("table td a").click(function () {
                    var id = $(this).attr('value');
                    $.cookie("channel-edit-id", id);
                    location.href = 'edit.html';
                });
            }

        }
    });
});

