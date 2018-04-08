<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui-1.4.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui-1.4.1/themes/icon.css">
</head>
<body >

    <div id="cc" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north'" style="height:100px;">
            <center><h1>金科1709B</h1></center>
        </div>
        <div data-options="region:'west',title:'菜单'" style="width:150px;">
            <div id="tt" ></div>
        </div>
        <div data-options="region:'center',title:'center title'" style="padding:5px;">
            <div id="tab" class="easyui-tabs" data-options="fit:true" ></div>

        </div>
    </div>

<script type="text/javascript">

    $("#tt").tree({
        data:[{
            "id":1,"parendId":0,"text":"日志","url":"/http/client/start"
            }],
        url:'<%=request.getContextPath()%>/queryMenu.lm',
        onClick:function(node){
            if(!$("#tab").tabs('exists',node.text)){
                $("#tab").tabs('add',{
                    title:node.text,
                    closable:true,
                    href:"<%=request.getContextPath()%>"+node.url,
                    onDestroy:function(){
                        $("#dd").dialog('destroy');
                    }
                })
            }else{
                $("#tab").tabs('select',node.text)
            }
        }
    })


</script>
</body>
</html>