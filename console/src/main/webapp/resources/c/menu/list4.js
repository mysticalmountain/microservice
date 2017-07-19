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
                var html = '';
                $.each(res.data, function (i, item) {
                    if (item.pid == undefined || item.pid == null) {
                        html += "<tr data-tt-id='" + item.id + "' >"
                    } else {
                        html += "<tr data-tt-id='" + item.id + "' data-tt-parent-id='" + item.pid + "'>"
                    }
                    html += "<td>" + item.id + "</td>" +
                        "<td>" + item.name + "</td>" +
                        "</tr>"

                });
                $("#mmm").append(html);
                // $.each(res.data, function (i, item) {
                //     $("#mmm").append(
                //         "<tr data-tt-id='" + item.id + "' data-tt-parent-id='" + item.pid + "'>" +
                //         "<td>" + item.id + "</td>" +
                //         "<td>" + item.name + "</td>" +
                //         "</tr>"
                //     )

                // });
                // $("#mymenus").treeTable();
                var setting = {

                    // persist: true,
                    // persistStoreName: "files",
                    expandable: false
                }
                // $("#mymenus").treeTable(setting);
                $("#mymenus").agikiTreeTable(setting);

            }
        }
    });

    var s = {
        "data": {
            "id": 10000293,
            "name": "root",
            "open": true,
            "sons": [{
                "checkType": "NO",
                "createdTime": 1497841520000,
                "id": 10000294,
                "lastUpdateTime": 1497841520000,
                "name": "系统管理",
                "od": 10,
                "parent": {
                    "checkType": "NO",
                    "createdTime": 1497841520000,
                    "id": 10000293,
                    "lastUpdateTime": 1497841520000,
                    "name": "root",
                    "sons": [{"$ref": "$.data.sons[0]"}, {"checkType": "NO", "createdTime": 1498474106000, "id": 10000369, "lastUpdateTime": 1498474106000, "name": "订单管理", "parent": {"$ref": "$.data.sons[0].parent"}, "sons": []}, {
                        "checkType": "NO",
                        "createdTime": 1498474177000,
                        "id": 10000371,
                        "lastUpdateTime": 1498474177000,
                        "name": "订单管理",
                        "od": 2,
                        "parent": {"$ref": "$.data.sons[0].parent"},
                        "sons": []
                    }, {"checkType": "NO", "createdTime": 1498474432000, "id": 10000373, "lastUpdateTime": 1498474432000, "name": "新建333", "od": 33, "parent": {"$ref": "$.data.sons[0].parent"}, "sons": []}, {
                        "action": "333",
                        "checkType": "NO",
                        "createdTime": 1498475396000,
                        "id": 10000376,
                        "lastUpdateTime": 1498475396000,
                        "name": "测试二级",
                        "od": 33,
                        "parent": {"$ref": "$.data.sons[0].parent"},
                        "sons": []
                    }]
                },
                "sons": [{
                    "action": "/views/user/list.html",
                    "checkType": "NO",
                    "createdTime": 1497841520000,
                    "id": 10000295,
                    "lastUpdateTime": 1497841520000,
                    "name": "用户管理",
                    "od": 10,
                    "parent": {"$ref": "$.data.sons[0]"},
                    "sons": []
                }, {
                    "action": "/views/role/list.html",
                    "checkType": "NO",
                    "createdTime": 1497841520000,
                    "id": 10000296,
                    "lastUpdateTime": 1497841520000,
                    "name": "角色管理",
                    "od": 20,
                    "parent": {"$ref": "$.data.sons[0]"},
                    "sons": []
                }, {
                    "action": "/views/permission/list.html",
                    "checkType": "NO",
                    "createdTime": 1497841521000,
                    "id": 10000297,
                    "lastUpdateTime": 1497841521000,
                    "name": "权限管理",
                    "od": 30,
                    "parent": {"$ref": "$.data.sons[0]"},
                    "sons": []
                }, {
                    "action": "/views/service/list.html",
                    "checkType": "NO",
                    "createdTime": 1497841521000,
                    "id": 10000298,
                    "lastUpdateTime": 1497841521000,
                    "name": "服务管理",
                    "od": 40,
                    "parent": {"$ref": "$.data.sons[0]"},
                    "sons": []
                }, {"action": "/views/menu/list.html", "checkType": "NO", "createdTime": 1497918763000, "id": 10000324, "lastUpdateTime": 1497918763000, "name": "菜单管理", "od": 50, "parent": {"$ref": "$.data.sons[0]"}, "sons": []}, {
                    "checkType": "NO",
                    "createdTime": 1498474153000,
                    "id": 10000370,
                    "lastUpdateTime": 1498474153000,
                    "name": "新建",
                    "od": 10,
                    "parent": {"$ref": "$.data.sons[0]"},
                    "sons": []
                }, {
                    "checkType": "NO",
                    "createdTime": 1498474202000,
                    "id": 10000372,
                    "lastUpdateTime": 1498474202000,
                    "name": "测试",
                    "od": 10,
                    "parent": {"$ref": "$.data.sons[0]"},
                    "sons": [{"checkType": "NO", "createdTime": 1498474484000, "id": 10000374, "lastUpdateTime": 1498474484000, "name": "新建11", "od": 10, "parent": {"$ref": "$.data.sons[0].sons[6]"}, "sons": []}, {
                        "checkType": "NO",
                        "createdTime": 1498474489000,
                        "id": 10000375,
                        "lastUpdateTime": 1498474489000,
                        "name": "新建12",
                        "od": 10,
                        "parent": {"$ref": "$.data.sons[0].sons[6]"},
                        "sons": []
                    }]
                }]
            }, {"$ref": "$.data.sons[0].parent.sons[1]"}, {"$ref": "$.data.sons[0].parent.sons[2]"}, {"$ref": "$.data.sons[0].parent.sons[3]"}, {"$ref": "$.data.sons[0].parent.sons[4]"}]
        }, "errorCode": "000000", "errorMessage": "success", "success": true
    }
});



