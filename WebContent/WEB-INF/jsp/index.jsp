<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>流程管理系统</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">
  
  <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css?t=1554901098009"  media="all">
</head>
<style>
#primaryWin{
	width:100%; height:100%;
}
</style>

<script>
function showPage( link ){
	//{ps} id="primaryWin" [iframe]
	var pWin = document.getElementById("primaryWin");
	pWin.src = '${ctxPath}'+ link;
}
</script>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">流程管理后台</div>
    <!-- 头部区域 (可配合 layui 已有的水平导航) -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="${ctxPath}/static/layui/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" class="layui-nav-img">
          ${ user.username }
        </a>
        <dl class="layui-nav-child">
          <dd><a href="${ctxPath}/User/login?username=admin&password=123">
          	admin(管理员)
          </a></dd>
          <dd><a href="${ctxPath}/User/login?username=terry&password=123">
          	Terry(业务部职员)
          </a></dd>
          <dd><a href="${ctxPath}/User/login?username=kailen&password=123">
          	Kailen(业务部主管)
          </a></dd>
          <dd><a href="${ctxPath}/User/login?username=andy&password=123">
          	andy(财务部主管)
          </a></dd>
          <dd><a href="${ctxPath}/User/login?username=boss&password=123">
         	 Boss(公司老总)
          </a></dd>
        </dl>
      </li>
      <li class="layui-nav-item">
      	<a href="${ctxPath}/User/logout">退出登录</a>
      </li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
	
      <!-- 左侧导航区域 (可配合 layui 已有的垂直导航) -->
        
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
      
      	<li class="layui-nav-item layui-nav-itemed" style="cursor:pointer;">
          	<a class="">用户管理</a>
		    	<!-- 生成子菜单   -->
		        <dl class="layui-nav-child">
		            <dd>
		            	<a href="#" onclick="showPage('/User/viewList');">
		            		用户列表
		            	</a>
					</dd>
					<dd>
		            	<a href="#" onclick="showPage('/User/viewAdd');">
		            		新增用户
		            	</a>
					</dd>
		    	</dl>
        </li>
        
        <li class="layui-nav-item layui-nav-itemed" style="cursor:pointer;">
          	<a class="">部门管理</a>
		    	<!-- 生成子菜单   -->
		        <dl class="layui-nav-child">
		            <dd>
		            	<a href="#" onclick="showPage('/Dept/viewList');">
		            		部门列表
		            	</a>
					</dd>
					<dd>
		            	<a href="#" onclick="showPage('/Dept/viewAdd');">
		            		新增部门
		            	</a>
					</dd>
		    	</dl>
        </li>
        
		<li class="layui-nav-item layui-nav-itemed" style="cursor:pointer;">
          	<a class="">文档中心</a>
		    	<!-- 生成子菜单   -->
		        <dl class="layui-nav-child">
		            <dd>
		            	<a href="#" onclick="showPage('/Document/viewList');">
		            		文件列表
		            	</a>
					</dd>
					<dd>
		            	<a href="#" onclick="showPage('/Document/viewUpload');">
		            		上传文件
		            	</a>
					</dd>
		    	</dl>
        </li>
        
        <li class="layui-nav-item layui-nav-itemed" style="cursor:pointer;">
          	<a class="">流程管理</a>
		    	<!-- 生成子菜单   -->
		        <dl class="layui-nav-child">
		            <dd>
		            	<a href="#" onclick="showPage('/WorkFlow/viewUploadProc');">
		            		布署流程
		            	</a>
					</dd>
					<dd>
		            	<a href="#" onclick="showPage('/WorkFlow/viewProcList');">
		            		流程列表
		            	</a>
					</dd>
		    	</dl>
        </li>
        
		<li class="layui-nav-item layui-nav-itemed" style="cursor:pointer;">
          	<a class="">请假审批</a>
		    	<!-- 生成子菜单   -->
		        <dl class="layui-nav-child">
		            <dd>
		            	<a href="#" onclick="showPage('/Leave/showLeaveForm');">
		            		我要请假
		            	</a>
					</dd>
					<dd>
		            	<a href="#" onclick="showPage('/Leave/waitMyApprove');">
		            		待我审批的
		            	</a>
					</dd>
					<dd>
		            	<a href="#" onclick="showPage('/Leave/myInitiate');">
		            		我发起的
		            	</a>
					</dd>
					<dd>
		            	<a href="#" onclick="showPage('/Leave/myApproved');">
		            		我已审批的
		            	</a>
					</dd>   
		    	</dl>
        </li>
        
        <li class="layui-nav-item layui-nav-itemed" style="cursor:pointer;">
          	<a class="">报销审批</a>
		    	<!-- 生成子菜单   -->
		        <dl class="layui-nav-child">
		            <dd>
		            	<a href="#" onclick="showPage('/Expense/showExpenseForm');">
		            		申请报销
		            	</a>
					</dd>
					<dd>
		            	<a href="#" onclick="showPage('/Expense/waitMyApprove');">
		            		待我审批的
		            	</a>
					</dd>
					<dd>
		            	<a href="#" onclick="showPage('/Expense/myInitiate');">
		            		我发起的
		            	</a>
					</dd>
					<dd>
		            	<a href="#" onclick="showPage('/Expense/myApproved');">
		            		我已审批的
		            	</a>
					</dd>   
		    	</dl>
        </li>
        
        

      </ul>
      
    </div>
  </div>
  
  <!--  主体区域  -->
  <div class="layui-body">	
	<iframe id="primaryWin" src="${ctxPath}/main.jsp" 
		frameborder="0" scrolling="no"></iframe>
  </div>
  
  
  
  <!--   底部固定区域    -->
  <div class="layui-footer">
    WWW.ALECTER.IT.COM
  </div>
</div>
<script src="${ctxPath}/static/layui/layui.js?t=1554901098009" charset="utf-8"></script>
<script>
//{ps} JavaScript 代码区域
layui.use( 'element', function(){
  var element = layui.element;  
} );
</script>
</body>
</html>







	

	
	
	