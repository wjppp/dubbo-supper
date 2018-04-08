<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<jsp:include page="includ.jsp"  flush="true" ></jsp:include>
<body>
<div id="tb" style="padding:3px">
    <span>条件查:</span><div id="dd"></div>
    <input id="itemid" style="line-height:26px;border:1px solid #ccc">

    <a href="#" class="easyui-linkbutton" plain="false" onclick="doSearch()">搜索</a>
</div>

<table id="dg" class="easyui-datagrid"   title="颜色管理"  >

    <thead>
    <tr>
        <th data-options="field:' ',formatter:bb"  width="16%"><input type="button" value="批量删除" onclick="batchdel()"></th>
        <th data-options="field:'id'"  width="16%">mongoID</th>
        <th data-options="field:'logId'" width="16%">日志ID</th>
        <th data-options="field:'logMsg'" width="16%">日志内容</th>
        <th data-options="field:'createTime'" width="16%">创建时间</th>
        <th data-options="field:'    ',formatter: aa"  width="16%">操作</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>

<div id="ee"></div>
<script>
    function doSearch() {
        $('#dg').datagrid('load', {
            conditionSearch: $('#itemid').val(),

        });

    }
    function updateLog(id){

        $("#ee").dialog({
            title: '修改Log',
            width: 400,
            height: 200,
            closed: false,

            cache: false,
            href: '<%=request.getContextPath()%>/http/client/queryLogById?id='+id,
            modal: true  ,
            buttons:[{
                text:'修改',
                handler:function(){

                    $.ajax({
                        url:"<%=request.getContextPath()%>/http/client/updateLog",
                        type:"post",
                        data:{"_id": $("[name=_id]").val(), "logId": $("[name=logId]").val(), "logMsg":$("[name=logMsg]").val(), "createTime":$("[name=createTime]").val()},
                        success:function(result){

                            alert(result);
                            $("#dg").datagrid("reload");
                        }

                    })

                    $("#ee").dialog("close");
                }
            },{
                text:'取消',
                handler:function(){

                    $("#ee").dialog("close");
                }
            }]


        })
    }
    function insertLog(){

        $('#dd').dialog({
            title: '添加Log',
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            href: '<%=request.getContextPath()%>/http/client/skipAdd',
            modal: true ,

            buttons:[{
                text:'保存',
                handler:function(){

                    $.ajax({
                        url:"<%=request.getContextPath()%>/http/client/addLog",
                        type:"post",
                        //data:$("#ff").serialize(),
                        data:{"logId": $("[name=logId]").val(), "logMsg":$("[name=logMsg]").val(), "createTime":$("[name=createTime]").val()},
                        success:function(result){

                            alert(result);
                            $("#dg").datagrid("reload");
                        }

                    })
                    $("#dd").dialog("close");
                }
            },{
                text:'关闭',
                handler:function(){

                    $("#dd").dialog("close");
                }
            }]
        })
    }
    function  aa(value,row,index){


        return     "<a  href='javascript:insertLog()' class='insert' >添加</a>"
           +"<a  href='javascript:updateLog(\""+row.id+"\")' class='upda' >修改</a>";
    }
    function batchdel(){

        var id="";
        $.each($("[name='box']"),function(i){
            if($("[name='box']")[i].checked){

                id+="-"+$(this).val();
            }

        })

        $.ajax({
            url:"<%=request.getContextPath()%>/http/client/batchDeleteLog",
            type:"get",
            data:{"id":id},
            success:function(result){

                alert(result);

            }
        })
    }
    function bb(val,row,index){

        return "<input type='checkbox' value="+row.id+" name='box'>";
    }
    $("#dg").datagrid({

        url:"<%=request.getContextPath()%>/http/client/start2",
       /* pagination:true,
        pageNumber:1,
        pageSize:5,
        pageList:[5,10,15,20],*/
        onLoadSuccess:function(){

            $('.insert').linkbutton({
                iconCls: 'icon-add',
                plain:false
            });
            $('.upda').linkbutton({
                iconCls: 'icon-edit',
                plain:false
            });

        }

    })


    </script>
</body>
</html>
