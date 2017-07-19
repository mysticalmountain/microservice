$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    $("#new").click(function () {
        location.href = 'new.html';
    });

    $.ajax({
        type: "GET",
        url: "/management/service/user/orgs",
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
            if (res.success == true) {
                var orgs;
                $.each(res.data, function (i, item) {
                    if (item.pid == undefined || item.pid == null) {
                        orgs += "<tr data-tt-id='" + item.id + "' >"
                    } else {
                        orgs += "<tr data-tt-id='" + item.id + "' data-tt-parent-id='" + item.pid + "'>"
                    }
                    orgs +=
                        "<td><a href='#' value='" + item.id +  "'>" + item.name + "</a></td>" +
                        "<td>" + item.id + "</td>" +
                        "<td><button value='" + item.id + "' type='button' class='btn btn-default btn-sm' > <span class='glyphicon glyphicon-trash'></span> 删除 </button></td>" +
                        "</tr>"
                });
                $("#orgs").append(orgs);
                var setting = {
                    expandable: false
                }
                $("#myOrgs").agikiTreeTable(setting);

                $('table td button.btn-default').click(function () {
                    deleteOrgs($(this));
                });

                $("table td a").click(function () {
                    var id = $(this).attr('value');
                    $.cookie("org-edit-id", id);
                    location.href = 'edit.html';
                });
            } else {
                $("#fail").html(res.errorMessage);
                $("#fail").show();
            }
        }
    });
});

function deleteOrgs(but) {
    $.ajax({
        type: "DELETE",
        url: "/management/service/org/" + but.val(),
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

}
