$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var path = window.document.location.href;
    var pos = path.lastIndexOf(":");
    var context = path.substring(0, pos);

    var treeData;
    $.ajax({
        type: "GET",
        url: "/management/service/tree-menus",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
        },
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        dataType: 'json',
        success: function (res) {
            if (res.success == true) {
                treeData = res.data;
                var setting = {
                    view: {
                        selectedMulti: false
                    },
                    edit: {
                        enable: true,
                        showRemoveBtn: false,
                        showRenameBtn: false
                    },
                    data: {
                        key: {
                            children: 'sons',
                            url: 'name',
                            title: 'action'
                        }
                    },
                    callback: {
                        onClick: showDetail
                    }
                };
                $.fn.zTree.init($("#menuTree"), setting, res.data);

            }
        }
    });

    $("#delete").click(function () {
        var zTree = $.fn.zTree.getZTreeObj("menuTree"),
            nodes = zTree.getSelectedNodes(),
            treeNode = nodes[0];
        if (nodes.length == 0) {
            alert("请先选择一个节点");
            return;
        }
        var callbackFlag = $("#callbackTrigger").attr("checked");

        var id = treeNode.id;
        $.ajax({
            type: "DELETE",
            url:  "/management/service/menu/" + id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
            },
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            dataType: 'json',
            // data: dataJson,
            success: function (res) {
                if (res.success == true) {
                    $("#success").hide();
                    $("#fail").hide();
                    if (res.success == true) {
                        $("#success").html(res.errorMessage);
                        $("#success").show();
                        zTree.removeNode(treeNode, callbackFlag);
                    } else {
                        $("#fail").html(res.errorMessage);
                        $("#fail").show();
                    }
                }
            }
        });
    });

    var newCount = 1;
    $("#new").click(function () {
        var zTree = $.fn.zTree.getZTreeObj("menuTree");
        var nodes = zTree.getSelectedNodes();
        var treeNode = nodes[0];
        zTree.addNodes(treeNode, {id: (100 + newCount), pId: treeNode.id, isParent: false, name: "新建" + (newCount++)});
        var id = $("#id").val();
        $("#pid").val(id);
        $("#id").val('');
        $('#id').attr('disabled', true);
        $('#name').val("新建");
    });

    var action;
    var pid;
    var od;

    function showDetail(event, treeId, treeNode) {
        $("#id").val(treeNode.id);
        $("#name").val(treeNode.name);
        $("#action").val(treeNode.url);
        action = null;
        pid = null;
        od = null;
        getMenu(treeData, treeNode.id);
        $("#action").val(action);
        $("#pid").val(pid);
        $("#od").val(od);
    }

    function getMenu(data, id) {
        if (data.id == id) {
            action = data.action;
        }
        $.each(data.sons, function (i, item) {
            if (item.id == id) {
                pid = data.id;
                action = item.action;
                od = item.od;
                // item.par
                // return item.action;
            } else {
                return getMenu(item, id);
            }
        });
    }

    $("#save").click(function () {
        var id = $("#id").val();
        var menuDto = {
            name: $("#name").val(),
            pid: $("#pid").val(),
            action: $("#action").val(),
            od: $("#od").val()
        };
        var data = {
            requestId: new Date().getTime(),
            data: menuDto
        };
        var dataJson = JSON.stringify(data);
        if (id != null && id != '') {

            $.ajax({
                type: "PATCH",
                url:  "/management/service/menu/" + id,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
                },
                dataType: 'json',
                data: dataJson,
                success: function (res) {
                    if (res.success == true) {
                        $("#success").hide();
                        $("#fail").hide();
                        if (res.success == true) {
                            $("#success").html(res.errorMessage);
                            $("#success").show();
                        } else {
                            $("#fail").html(res.errorMessage);
                            $("#fail").show();
                        }
                    }
                }
            });
        } else {
            $.ajax({
                type: "POST",
                url:  "/management/service/menu",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json; charset=utf-8");
                    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8")
                },
                dataType: 'json',
                data: dataJson,
                success: function (res) {
                    if (res.success == true) {
                        $("#success").hide();
                        $("#fail").hide();
                        if (res.success == true) {
                            $("#success").html(res.errorMessage);
                            $("#success").show();
                        } else {
                            $("#fail").html(res.errorMessage);
                            $("#fail").show();
                        }
                    }
                }
            });
        }

    });
});



