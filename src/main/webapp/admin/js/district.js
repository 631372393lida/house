$(function(){


    //使用datagrid绑定数据展示
    $('#dg').datagrid({
        title:"区域信息",
        url:'/getDistrict',
        fitColumns: true,
        pagination: true,
        pageList: [5, 10, 15, 20],
        toolbar:"#ToolBar",
        pageSize:5,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'name', title: '名称', width: 50, align: "center" },
            { field: 'opt', title: '操作', width: 50, align: "center",
                formatter: function(value,row,index){
                    //同步跳转 "<a href='delDistrict?id="+row.id+"'>删除</a>"
                    return "<a href='javascript:delSingle("+row.id+")'>删除</a> | <a href='javascript:selectstreet("+row.id+")'>查看街道</a>";
                }
            }
        ]]
    })

});


//打开添加窗口
function Add() {
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length!=0){
        SelectRows.length==0
        $.messager.alert('提示框','请直接点击添加');
        $("#dg").datagrid('reload');    /*刷新*/
        return;
    }


    $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
}

//关闭添加窗口
function CloseDialog(obj){
    $("#"+obj).dialog("close");
}


function SaveDialog(){
    //跳转到后台实现保存
    //传统:取值-->通过$.ajax方法发送异步请求实现添加
    $('#saveDialogForm').form('submit', {
        url:"/addDistrict",  //提交的服务器地址
        success:function(data){ //成功的回调函数
            //data接收服务器返回值json字符串（不是json对象）
            var obj=$.parseJSON(data);
            if(obj.result>0){
                $("#AddDialog").dialog("close");  //关闭
                $("#dg").datagrid('reload');    /*刷新*/
                //alert("添加成功");
                $.messager.alert('提示框','恭喜添加成功!');
            }
            else
            {
                $.messager.alert('提示框','系统正在维护!');
            }
        }
    });
}



//弹出修改对话框
function ModifyBySelect(){
    //判断有没有选择修改的记录
    //获取所有选中行，返回的数据，如果没有选中返回空
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length!=1){
        $.messager.alert('提示框','你还没有选中行，或者选择了多行');

        return;
    }


    //选择了一行
    //还原数据
    var SelectRow = SelectRows[0];  //获取当前行的json:{"id":1000,"name":"丰台"}
    //打开编辑对话框
    $("#upDialog").dialog("open").dialog('setTitle',"修改区域");
    //获得行对象的数据加载到表单中显示
    //注意:SelectRow表示的就是当前行的Json:{"id":1000,"name":"丰台"}
    // 表单对象名称与json对象的键相同即可还原
    $("#upDialogForm").form('load',SelectRow);

}


/*实现修改*/
function upDialog() {
    $('#upDialogForm').form('submit', {
        url:"/upDistrict",  //提交的服务器地址
        success:function(data){ //成功的回调函数
            //data接收服务器返回值json字符串（不是json对象）
            var obj=$.parseJSON(data);
            if(obj.result>0){
                $("#upDialog").dialog("close");  //关闭
                $("#dg").datagrid('reload');    /*刷新*/
                //alert("添加成功");
                $.messager.alert('提示框','恭喜修改成功!');
            }
            else
            {
                $.messager.alert('提示框','系统正在维护!');
            }
        }
    });
}


//删除单条
function delSingle(id){
    $.messager.confirm('确认提示框', '确认删除吗?', function(result){
        if (result){
            // 实现删除  发送异步请求实现删除
            //alert(id);
            //$.post("服务器地址",给服务器传参,回调函数,"json");
            //传参的格式: {"参数名称":值,"参数名称":值}

            $.post("/deDistrict",{"id":id},function(data){
                if(data.result>0){
                    $("#dg").datagrid('reload'); //刷新
                }
                else
                {
                    $.messager.alert('提示框','系统正在维护!');
                }
            },"json");

        }
    });
}




//删多条
function DeleteByFruitName() {
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length==0){
        $.messager.alert('提示框','你还没有选中行');
        return;
    }

    var delValue ="";
    for (var i=0;i<SelectRows.length;i++){
        delValue=delValue+SelectRows[i].id+","
    }
    delValue=delValue.substring(0,delValue.length-1);


    $.messager.confirm('确认提示框', '确认删除吗?', function(result){
        if (result) {

            $.post("/demoreDistrict", {"ids": delValue}, function (data) {
                if (data.result > 0) {
                    $("#dg").datagrid('reload'); //刷新
                    $.messager.alert('提示框', '删除成功!');
                }
                else {
                    $.messager.alert('提示框', '系统正在维护!');
                }
            }, "json");
        }
    });

}



function selectstreet(obj) {
    $("#streetDialog").dialog("open").dialog('setTitle',"查看街道");
    $('#streetDg').datagrid({
        title:"街道信息",
        url:'/getStreetByid?did='+obj,
        fitColumns: true,
        pagination: true,
        pageList: [5, 10, 15, 20],
       // toolbar:"#ToolBar",
        pageSize:5,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'name', title: '名称', width: 50, align: "center" },
            { field: 'opt', title: '操作', width: 50, align: "center",
                formatter: function(value,row,index){
                    //同步跳转 "<a href='delDistrict?id="+row.id+"'>删除</a>"
                    return "<a href='javascript:delSingle(\"+row.id+\")'>删除</a>";
                }
            }
        ]]
    });

    $("#districtId").val(obj)

}

