<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>报销单</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css"  media="all">
</head>
<body>
   
<fieldset class="layui-elem-field layui-field-title" 
	style="margin-top: 20px;">
  <legend>报销详情页</legend>
</fieldset>

<script src="${ctxPath}/static/js/jquery-1.11.1.min.js"></script>

<form id="exForm" class="layui-form" action="" method="post">
	
	<div class="layui-form-item">
    	<label class="layui-form-label">任务 ID:</label>
		<div class="layui-input-block" style="width:450px;">
			<input type="text" id="taskId" 
				 name="taskId" autocomplete="off" 
				 readonly value="${expense.taskid}" 
			class="layui-input">
		</div>
	</div>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">报销金额:</label>
		<div class="layui-input-block" style="width:450px;">
			<input type="text" id="money" 
				 name="money" autocomplete="off" 
				 readonly value="${expense.money}"
			class="layui-input">
		</div>
	</div>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">开单时间:</label>
		<div class="layui-input-block" style="width:450px;">
			<input type="text" id="orderdate" 
				 name="orderdate" autocomplete="off" 
				 readonly value="${expense.orderdate}"
			class="layui-input">
		</div>
	</div>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">申请时间:</label>
		<div class="layui-input-block" style="width:450px;">
			<input type="text" id="expensedate" 
				 name="expensedate" autocomplete="off" 
				 readonly value="${expense.expensedate}"
			class="layui-input">
		</div>
	</div>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">报销内容:</label>
		<div class="layui-input-block" style="width:450px;">
			<textarea name="orderDesc" id="orderDesc" required lay-verify="required" 
				class="layui-textarea" readonly>${expense.orderdesc}</textarea>
		</div>
	</div>

	<div class="layui-form-item">
    	<label class="layui-form-label">拒绝原因:</label>
		<div class="layui-input-block" style="width:450px;">
			<textarea name="refuseReason" id="refuseReason" required lay-verify="required" 
				class="layui-textarea"></textarea>
		</div>
	</div>
	
	<div class="layui-form-item">
    	<div class="layui-input-block">
      		<button class="layui-btn" type="button" onclick="apprExpense('true');" >
      			同意申请
      		</button>
			<button class="layui-btn" type="button" onclick="apprExpense('false');" >
      			拒绝申请
      		</button>
		</div>
	</div>

</form>
<script>
function apprExpense( _flag ){
	var _taskId = $("#taskId").val();
	//{1} 当前用户的部门Id
	var _deptId = '${user.deptid}';
	//{2} 拒绝原因
	var _reason   = $("#refuseReason").val();
	//{3} 提交地址
	$.ajax(
		{
			url:'${ctxPath}/Expense/doApprove',
			type:'post',
			data:{
				deptId:_deptId,
				appFlag:_flag,
				refuseReason:_reason,
				taskId:_taskId
			},
			dataType:"json",
			success:function( json ){
				console.log( json );
				var ret = json['result'];
				if(ret='success'){
					alert('提交审批成功');
					window.location = "${ctxPath}/Expense/myApproved";
				}else{
					var cause = json['cause'];
					alert('提交审批失败，原因：'+cause);
				}
			}
		}		
	);
}
</script>


<script src="${ctxPath}/static/layui/layui.js" charset="utf-8"></script>
<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //日期
  laydate.render({
    elem: '#date'
  });
  laydate.render({
    elem: '#date1'
  });
  
  //创建一个编辑器
  var editIndex = layedit.build('LAY_demo_editor');
 
  //自定义验证规则
  form.verify({
	account: function(value){
      if(value.length < 3){
        return '帐号至少 3 个字符';
      }
    }
    ,pass: [
      /^[\S]{3,3}$/
      ,'密码必须 3 到 3 位，且不能出现空格'
    ]
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });
  
  //监听指定开关
  form.on('switch(switchTest)', function(data){
    layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
      offset: '6px'
    });
    layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
  });
  
  //监听提交
  //form.on('submit(demo1)', function(data){
    //layer.alert(JSON.stringify(data.field), {
    //  title: '最终的提交信息'
    //})    
  //  return false;
  //});
 
  //表单初始赋值
  form.val('example', {
    "username": "贤心" // "name": "value"
    ,"password": "123456"
    ,"interest": 1
    ,"like[write]": true //复选框选中状态
    ,"close": true //开关状态
    ,"sex": "女"
    ,"desc": "我爱 layui"
  })
  
  
});
</script>
