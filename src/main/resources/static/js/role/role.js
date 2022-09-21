var table = layui.table

// 第一个实例
var tableIns = table.render({
    elem: '#roleList',
    url: '/role/list',
    page: true,
    parseData: function (res) { //res 即为原始返回的数据
        return {
            "code": res.code, //解析接口状态
            "msg": res.msg, //解析提示文本
            "count": res.data.count, //解析数据长度
            "data": res.data.records //解析数据列表
        };
    },
    cols: [[
        {field: 'roleName', title: '角色名称'},
        {field: 'createTime', title: '创建时间'},
        {title: '操作', toolbar: '#barDemo'}
    ]],
    even: true
});

/**
 * 按条件查询
 */
function query() {
    // 重载 - 这里以搜索为例
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            roleName: $("#roleName").val(),
        }, page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}

/**
 *新增客户
 */
function toAdd() {
    openLayer('/role/toAdd', '新增角色');
    $.ajax({
        url: '/role/listResource',
        async: false,
        type: 'GET',
        success: function (res) {
            if (res.code == 0) {
                layui.tree.render({
                    elem: '#resource',
                    data: res.data,
                    id: 'resource',
                    showCheckbox: true
                });
            }
        }
    });
    mySubmit('addSubmit', 'POST', addIds);
}

const addIds = function (field) {
    let checkedData = layui.tree.getChecked('resource');
    field.resourceIds = getIds(checkedData, []);
};

function getIds(checkedData, arr) {
    for (let i in checkedData) {
        arr.push(checkedData[i].id);
        arr = getIds(checkedData[i].children, arr);
    }
    return arr;
}