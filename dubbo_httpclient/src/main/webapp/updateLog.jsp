<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="ff" method="post">
    <input type="text" value = "${loger._id}" name = "_id">
    <div>
        <label for="text">LogId:</label>
        <input class="easyui-validatebox" type="text" value = "${loger.logId}" name="logId" data-options="required:true" />
    </div>
    <div>
        <label for="text">LogMsg:</label>
        <input class="easyui-validatebox" type="text" value = "${loger.logMsg}" name="logMsg" data-options="required:true" />
    </div>
    <div>
        <label for="text">创建时间:</label>
        <input class="easyui-validatebox" type="text" value="<fmt:formatDate value = "${loger.createTime}" pattern="yyyy-MM-dd" />"  name="createTime" data-options="required:true" />
    </div>

</form>
</body>
</html>
