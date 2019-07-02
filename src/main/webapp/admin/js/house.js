$(function(){


    //使用datagrid绑定数据展示
    $('#dg').datagrid({
        title:"审核出租房管理",
        url:'/getHouseNopass',
        fitColumns: true,
        pagination: true,
        pageList: [5, 10, 15, 20],
        toolbar:"#ToolBar",
        pageSize:5,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'title', title: '标题', width: 50, align: "center" },
            { field: 'price', title: '价格', width: 50, align: "center" },
            { field: 'floorage', title: '面积', width: 50, align: "center" },
            { field: 'dname', title: '区域', width: 50, align: "center" },
            { field: 'sname', title: '街道', width: 50, align: "center" },
            { field: 'tname', title: '类型', width: 50, align: "center" },
            { field: 'ispass', title: '状态', width: 50, align: "center" ,formatter:function(value,row,index){
                return "未审核";

                }
            },
            { field: 'opt', title: '操作', width: 50, align: "center",
                formatter: function(value,row,index){

                    return "<a href='javascript:delSingle("+row.id+")'>审核</a>";
                }
            }
        ]]
    })

});


//实现搜索
function searchUser(){
    //datagrid控制重新加载的方法
    //$("#dg").datagrid("load",跟查询条件的参数);
    //取电话,开始年龄，结束年龄


    var $telephone=$("#tel").val();
    var $startAge=$("#startAge").val();
    var $endAge=$("#endAge").val();
    $("#dg").datagrid("load",{"telephone":$telephone,"startAge":$startAge,"endAge":$endAge});

}
