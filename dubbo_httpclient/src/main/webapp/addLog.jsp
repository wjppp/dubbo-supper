<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form id="ff" method="post">
        <div>
            <label for="text">LogId:</label>
            <input class="easyui-validatebox" type="text" name="logId" data-options="required:true" />
        </div>
        <div>
            <label for="text">LogMsg:</label>
            <input class="easyui-validatebox" type="text" name="logMsg" data-options="required:true" />
        </div>
        <div>
            <label for="text">创建时间:</label>
            <input class="easyui-validatebox" type="text" name="createTime" data-options="required:true" />
        </div>

    </form>
</body>
</html>
