$(function () {
    tableInit()
    //只要触发模态框的关闭就清空input里面的数据

    $('#myModal').on('hidden.bs.modal', function (e) {
        $("#formName").val("")
       $("#formAccount").val("")
    })

})
 let globalDate=null;
globalId=null;
function tableInit() {
    $('#table').bootstrapTable({
        url: '/user/query', // 请求后台的URL（*）
        method: 'get', // 请求方式（*）
        contentType: "application/x-www-form-urlencoded",//post 必须制定类型，否则不能正常传值
        toolbar: '#toolbar', // 工具按钮用哪个容器
        striped: true, // 是否显示行间隔色
        cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, // 是否显示分页（*）
        sortName: "id",//默认排序列
        sortable: true, // 是否启用排序
        sortOrder: "asc", // 排序方式
        queryParams: tableQueryParams,// 传递参数（*）
        sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, // 初始化加载第一页，默认第一页
        pageSize: 3, // 每页的记录行数（*）
        pageList: [10, 15, 20], // 可供选择的每页的行数（*）
        search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true, // 是否显示所有的列
        showRefresh: true, // 是否显示刷新按钮
        minimumCountColumns: 2, // 最少允许的列数
        clickToSelect: true, // 是否启用点击选中行
        singleSelect: true,//开启单选，默认为多选
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        showToggle: false, // 是否显示详细视图和列表视图的切换按钮
        cardView: false, // 是否显示详细视图
        detailView: false, // 是否显示父子表
        columns: [ {//表格每列数据
            field: 'id',//返回数据名
            title: 'ID'//表格上显示的名
        },
           {
            field: 'name',
            title: '名字',
        },{
            field: 'account',
            title: '账户',
        }, {
            field: 'id',
            title: '操作',
    //             参数1；当前字段的值 id
    //             参数二；这条数据的值
    // 参数三；这条数值在结果数组里面的下标
            formatter: function (data, row, index) {
                var temp = ""
                temp ="<a href='javascript:void(0)' onclick=edit('" + data + "') >编辑</a>  &nbsp;&nbsp;";
                temp +="<a href='javascript:void(0)' onclick=dele('" + data + "')>删除</a>   &nbsp;&nbsp;";
//必须返回一个字符串 展示在表格内
                return temp;
            }
        }],
        responseHandler:function (res) {//前端接收后端参数
            let tempRows=null;
            let  tempTal=0;
            if (res.code==200 ) {
                tempRows =res.data.info
                    tempTal=res.data.total
            }
            return{
              rows: tempRows,
              total:  tempTal

            }

},
onLoadSuccess:function (res){
    globalDate=  res.rows
    }
    });
}
// 传递给后端的数据
function tableQueryParams(params) {
    var page = (params.offset / params.limit) + 1;
    // 传递给后端的数据
    var temp = {
        size: params.limit, // 页面大小
        page: page, // 第几页
        order: params.order,
        sort: params.sort,
        //定义任何数据
        name:$("#ImputName").val(),//前端传给后端的值
        account:$("#ImputAccount").val()

    };


    return temp;
}
function tableQuery() {

           $('#table').bootstrapTable("destroy")//销毁原来表格
    tableInit();
}

function openDialog() {
    $('#myModal').modal('show')
}
function close() {
    $('#myModal').modal('hide')
}



// 增加数据
function formSub() {
let name=$("#formName").val()
    let account=$("#formAccount").val()
    if (name==null || name=="" ){
        $("#formName").focus();
        return;
    }
    if (account == null || account =="" ){
        $("#formAccount").focus();
        return;
    }

    $.ajax({
        url:"/user/addoredit",//地址映射 后端地址
        type:"post" ,//提交方式
        data:{
            id:globalId,
            name:name,
            account:account
        } , //要传递到后端的参数
        dataType:"json",//传递数据转换为对象形式  text为传递字符串形式
        success:function (data) {
          // 无论成功与否都关闭模态框
            $('#myModal').modal('hide')
            if (data.code!=200 ){
                alert(data.msg)
            }else{
                $('#table').bootstrapTable("refresh")

            }


        }
    })


}
// <!--根据id修改数据-->
function edit(id) {
    globalId=id

    if (globalDate&&globalDate.length>0){
        for (var i=0;i<globalDate.length;i++)   {
            if (globalDate[i].id==id){
               let  name=globalDate[i].name
                let  account=  globalDate[i].account
                $('#myModal').modal('show')
                $('#formName').val(name)
                $('#formAccount').val(account)
                $('.modal-title').text("编辑用户")

            }
        }
    }


}
// 表格数据的删除
function dele(id) {
    if(confirm('确定要删除该行信息?')){
   let  name=   $("#ImputName").val();
      let  account=    $("#ImputName").val();
        $.ajax({
            url:"/user/dele",//地址映射 后端地址
            type:"post" ,//提交方式
            data: {id:id,
            name:name,
            account:account}, //要传递到后端的参数 将当前这条数据的id返回后端dele
            dataType:"json",//传递数据转换为对象形式  text为传递字符串形式
            success:function (data) {
                //如果code不为200说明错误返回错误信息
                if (data.code!=200 ){
                    alert(data.msg)
                }else{
                    //反之刷新表格
                    $('#table').bootstrapTable("refresh")

                }
            }
        })
}
}

