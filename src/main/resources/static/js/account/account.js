layui.laydate.render({
    elem: "#createTimeRange"
    , range: true
    // 2020-11-12 - 2020-11-20
})

var table = layui.table

// 第一个实例
var tableIns = table.render({
    elem: '#accountList'
    , url: '/account/list'
    , page: true
    , parseData: function (res) { //res 即为原始返回的数据
        return {
            "code": res.code, //解析接口状态
            "msg": res.msg, //解析提示文本
            "count": res.data.count, //解析数据长度
            "data": res.data.records //解析数据列表
        };
    }
    , cols: [[
        {field: 'username', title: '用户名'}
        , {field: 'realName', title: '真实姓名'}
        , {field: 'roleName', title: '角色名称'}
        , {field: 'sex', title: '性别'}
        , {field: 'createTime', title: '创建时间'}
        , {title: '操作', toolbar: '#barDemo'}
    ]]
});


// 按条件查询
function query() {
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            realName: $("#realName").val()
            , email: $("#email").val()
            , createTimeRange: $("#createTimeRange").val()
            //…
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });

}

function toAdd() {
    openLayer('/account/toAdd', '新增账号');

    layui.form.render();

    mySubmit('addSubmit', 'POST');

}

//监听工具条
table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

    let accountId = data.accountId;

    if (layEvent === 'detail') { //查看
        //do somehing
        openLayer('/account/toDetail/' + accountId, '账号详情')
    } else if (layEvent === 'del') { //删除
        layer.confirm('真的删除行么', function (index) {
            layer.close(index);
            myDelete("/account/" + accountId);
            //向服务端发送删除指令
        });
    } else if (layEvent === 'edit') { //编辑
        //do something
        openLayer('/account/toUpdate/' + accountId, '修改客户')
        layui.form.render();
        mySubmit("updateSubmit", "PUT")

        //同步更新缓存对应的值
        obj.update({
            username: '123'
            , title: 'xxx'
        });
    }
});


// 验证用户名是否重复
layui.form.verify({
    checkUsername: function(value, item){ //value：表单的值、item：表单的DOM对象
        let error = null;

        let url = '/account/' + value;

        let accountId = $("input[name='accountId']").val();

        if (typeof(accountId) != 'undefined') {
            url += '/' + accountId;
        }

        $.ajax({
            url: url
            , async: false
            , type: 'GET'
            , success: function (res) {
                if (res.code == 0) {
                    if (res.data > 0) {
                        error = "用户名已经存在";
                    }
                } else {
                    error = "用户名检测出错";
                }
            }, error: function () {
                error = "用户名检测出错";
            }
        });

        if (error != null) {
            return error;
        }
    }

    //我们既支持上述函数式的方式，也支持下述数组的形式
    //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
    // ,pass: [
    //     /^[\S]{6,12}$/
    //     ,'密码必须6到12位，且不能出现空格'
    // ]
});

