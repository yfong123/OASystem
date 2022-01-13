<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.net.*"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>流程布署</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css"  media="all">
</head>
<style>
.wd-250{ width:250px; }
.wd-350{ width:350px; }
.wd-450{ width:450px; }
.wd-550{ width:550px; }

[type='file']{
	height:40px; margin-top:0px;
}
</style>
<body>

<%
String cause = request.getParameter("cause");
cause = (cause==null) ? "" : cause;
%>
<script>

window.onload = function(){
	var msg = "<%=cause%>";
	if( msg ){
		layui.layer.msg( msg );
	}
}
</script>
        
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>流程布署</legend>
</fieldset>

<form class="layui-form" action="${ctxPath}/WorkFlow/deployProc" 
	method="POST" enctype="multipart/form-data" >
	
  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label" style="width:80px;">所属部门</label>
	      <div class="layui-input-inline">
	      	 <select name="procCategory" id="procCategory">
	      	 	<option value="0">请选择流程类型</option>
				<option value="请假流程">请假流程</option>
				<option value="报销流程">报销流程</option>
				<option value="加班流程">加班流程</option>
				<option value="出差流程">出差流程</option>
            </select>
	      </div>
	    </div>
	  </div>
  
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">流程描述</label>
    <div class="layui-input-block">
      <textarea name="note" placeholder="请输入描述:" 
      	class="layui-textarea wd-550"></textarea>
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">流程文件</label>
    <div class="layui-input-block">
      <input type="file" name="zipFile" 
      		style="height:40px;">
    </div>
  </div>  

  <div class="layui-form-item">
    <div class="layui-input-block">
      <button type="submit" class="layui-btn">开始部署</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form> 

<script src="${ctxPath}/static/layui/layui.js" charset="utf-8">
</script>
<script>
layui.use( 
	['form', 'layedit', 'laydate'], 
	function(){
		console.log('{DEBUG} layui.user...');
	}
);
</script>

</body>
</html>
        