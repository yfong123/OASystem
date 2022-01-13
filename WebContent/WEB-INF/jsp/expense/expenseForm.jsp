<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>申请报销</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css"  media="all">
</head>
<body>
   
<fieldset class="layui-elem-field layui-field-title" 
	style="margin-top: 20px;">
  <legend>申请报销页</legend>
</fieldset>

<script src="${ctxPath}/static/js/jquery-1.11.1.min.js"></script>

<form id="exForm" class="layui-form" 
	action="" 
	method="post">
	
	<input id="initiator" type="hidden" value="${user.id}"/>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">开单日期:</label>
		<div class="layui-input-block" style="width:450px;">
			<input type="text" id="orderDate" 
				 name="orderDate" autocomplete="off" 
				 placeholder="日期格式:2000/01/01"
			class="layui-input">
		</div>
	</div>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">报销金额:</label>
		<div class="layui-input-block" style="width:450px;">
			<input type="text" id="money" 
				 name="money" autocomplete="off" 
				 placeholder="请输入金额" 
			class="layui-input">
		</div>
	</div>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">报销内容:</label>
		<div class="layui-input-block" style="width:450px;">
			<textarea name="orderDesc" id="orderDesc" required lay-verify="required" 
				placeholder="请输入" class="layui-textarea"></textarea>
		</div>
	</div>

	<div class="layui-form-item">
    	<div class="layui-input-block">
      		<button class="layui-btn" type="button" onclick="submitExpense();" >
      			提交报销
      		</button>
			<button type="reset" class="layui-btn layui-btn-primary">
				重置表单
			</button>
		</div>
	</div>

</form>
<script>
function submitExpense(){
	$.ajax(
		{
			url:'${ctxPath}/Expense/submitExpense',
			type:'post',
			data:{
				initiator:$('#initiator').val(),
				orderdate:$('#orderDate').val(),
				money:$('#money').val(),
				orderdesc:$('#orderDesc').val()
			},
			dataType:"json",
			success:function( json ){
				console.log( json );
				var ret = json['result'];
				if(ret='success'){
					alert('申请报销成功');
					window.location = "${ctxPath}/Expense/myInitiate";
				}else{
					var cause = json['cause'];
					alert('申请报销失败，原因：'+cause);
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
