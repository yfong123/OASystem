<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>添加部门</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css"  media="all">
</head>
<body>
   
<fieldset class="layui-elem-field layui-field-title" 
	style="margin-top: 20px;">
  <legend>新增部门页</legend>
</fieldset>

<script src="${ctxPath}/static/js/jquery-1.11.1.min.js"></script>

<form id="DeptForm" class="layui-form" 
	action="" 
	method="post">
	
	<div class="layui-form-item">
    	<label class="layui-form-label">部门名称</label>
		<div class="layui-input-block" style="width:250px;">
			<input type="text" id="deptName" 
				 name="deptName" autocomplete="off" 
				 placeholder="请输入名称" 
			class="layui-input">
		</div>
	</div>
	
	<div class="layui-form-item">
    	<label class="layui-form-label">部门描述</label>
		<div class="layui-input-block" style="width:250px;">
			<input type="text" id="deptDesc" 
				 name="deptDesc" autocomplete="off" 
				 placeholder="请输入描述" 
			class="layui-input">
		</div>
	</div>
	
	<div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label" style="width:80px;">上层部门</label>
	      <div class="layui-input-inline">
	      	 <select name="parentId" id="parentId">
            </select>
	      </div>
	    </div>
	  </div>

	<div class="layui-form-item">
    	<div class="layui-input-block">
      		<button class="layui-btn" type="button" onclick="goSubmit();" >
      			创建部门
      		</button>
			<button type="reset" class="layui-btn layui-btn-primary">
				重置表单
			</button>
		</div>
	</div>

</form>
<script>
function initDeptData(){
	$.ajax(
		{
			url:'${ctxPath}/Dept/jsonDeptOptions',
			type:'get',
			dataType:'json',
			success:function(json){
				dept_data  = json['deptJson'];
				var ops = json['deptJson'];
				$("#parentId").append(
					"<option value=\"\">请选择部门</option>"		
				);
				for(var i=0; i<ops.length; i++){
					var op = document.createElement("option");
					op.value = ops[i]['val'];
					op.text = ops[i]['text'];
					$("#parentId").append(op);
				}
				layui.form.render('select');
			}
		}
	);
}

initDeptData();
//{ps}这是提交函数
function goSubmit(){
	//{ps}提交  /Dept/save
	$.ajax(
		{
			url:'${ctxPath}/Dept/save',
			type:'post',
			data:{
				deptname:$("#deptName").val(),
				deptdesc:$("#deptDesc").val(),
				parentid:$("#parentId").val(),
			},
			dataType:'json',
			success:function( respTxt ){
				if(respTxt['result']=='success'){
					//{1} 成功就提示成功
					layer.msg('新增部门成功:'+ 
							$("#deptName").val() );
					//    跳转到 /Dept/viewList
					setTimeout(
						function(){
							reloadPage();
						}, 2500
					);
				}else{
					layer.msg(respTxt['cause']);
				}
			}
		}
	);
}
function reloadPage(){
	window.location = "${ctxPath}/Dept/viewList";
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
    "Deptname": "贤心" // "name": "value"
    ,"password": "123456"
    ,"interest": 1
    ,"like[write]": true //复选框选中状态
    ,"close": true //开关状态
    ,"sex": "女"
    ,"desc": "我爱 layui"
  })
  
  
});
</script>
