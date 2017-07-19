$(document).ready(function () {
    $.getScript("/resources/c/common/include.js");

    var path = window.document.location.href;
    var pos = path.lastIndexOf(":");
    var context = path.substring(0, pos);

    $.ajax({
        type: "GET",
        url: context + "/permission/service/menus/list",
        dataType: 'json',
        success: function (res) {
            if (res.success == true) {
                $.each(res.data, function (i, item) {
                    // $("#ttmenus").append(
                    //     "<tr id='" + item.id + "' pId='" + item.pid + "'>" +
                    //     "<td>" + item.id + "</td>" +
                    //     "<td>" + item.name + "</td>" +
                    //     "</tr>"
                    // )

                })
            }
        }
    });


    var option = {
        theme: 'vsStyle',
        expandLevel: 2,
        beforeExpand: function ($treeTable, id) {
            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
            // if ($('.' + id, $treeTable).length) {
            //     return;
            // }

            //这里的html可以是ajax请求
            // var html = '<tr id="8" pId="5"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
            //     + '<tr id="9" pId="8"><td>5.2</td><td>动态的内容</td></tr>';
            //
            // $("#ttmenus").append(html);
            // alert('=====');
            if (id != 'root') {
                return;
            }
            alert(id);
            $.ajax({
                type: "GET",
                url: context + "/permission/service/menus/list",
                dataType: 'json',
                success: function (res) {
                    if (res.success == true) {
                        var html = '';
                        $.each(res.data, function (i, item) {
                            // $("#ttmenus").append(
                            if (item.pid == undefined || item.pid == null) {
                                html += "<tr id='" + item.id + "' pId = 'root'>"
                            } else {
                                html += "<tr id='" + item.id + "' pId = '" + item.pid + "'>"
                            }
                            html += "<td>" + item.id + "</td>" +
                                "<td>" + item.name + "</td>" +
                                "</tr>"
                            // )

                        });
                        // alert(html);
                        $treeTable.addChilds(html);
                    }
                }
            });
        },
        onSelect: function ($treeTable, id) {
            window.console && console.log('onSelect:' + id);

        }

    };
    // $('#treeTable1').treeTable(option);

    option.theme = 'default';
    $('#treeTable2').treeTable(option);

});



