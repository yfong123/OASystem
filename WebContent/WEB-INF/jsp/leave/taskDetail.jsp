<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<script src="${ctxPath}/static/js/jquery-1.11.1.min.js"></script>


<form id="frmLeave">	
	<!--  {ps}发起人: initiator -->
	<label>发起人:</label>
	<input id="initiatorName" type="text" readonly value="${leave.initiatorName}"/><br/>
	
	<label>开始时间:</label>
	<input id="startDate" type="text" readonly value="${leave.startdate}"/><br/>
	
	<label>结束时间:</label>
	<input id="endDate" type="text" readonly value="${leave.enddate}"/><br/>
	
	<label>请假类型:</label>
	<input id="leaveType" type="text" readonly value="${leave.leavetype}"/>
	<br/>
	
	<label>请假原因:</label> 
	<textarea id="reason" readonly>${leave.reason}</textarea><br/>
	
	<input type="button" value="同意申请" 
		onclick="apprLeave('true');"/>
		
	<input type="button" value="拒绝申请" 
		onclick="apprLeave('false');"/><br/><br/>
		
	<label>拒绝原因:</label> 
	<textarea id="refuseReason"></textarea><br/><br/>
	
	<label>任务 ID:</label> 
	<input id="taskId" type="text" readonly value="${leave.taskid}" />	
	<br/><br/>

</form>

<script>
function apprLeave( _flag ){
	var _taskId = $("#taskId").val();
	//{1} 当前用户的部门Id
	var _deptId = '${user.deptid}';
	//{2} 拒绝原因
	var _reason   = $("#refuseReason").val();
	//{3} 提交地址
	$.ajax(
		{
			url:'${ctxPath}/Leave/doApprove',
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
					window.location = "${ctxPath}/Leave/myApproved";
				}else{
					var cause = json['cause'];
					alert('提交审批失败，原因：'+cause);
				}
			}
		}		
	);
}
</script>




