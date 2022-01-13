package com.gec.mapper;

import com.gec.domain.Leave;
import com.gec.domain.LeaveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeaveMapper {
	int addLeave(Leave leave);

	int updateAssigneeAndTask( 
			@Param("insId")String insId, 
			@Param("taskId")String taskId,
			@Param("assignee") String assignee );

	List<Leave> queryWaitMyApprove(@Param("assigneeId") String assigneeId,
			@Param("offset")int offset,
			@Param("limit")int limit);

	int queryWaitMyApproveCount(@Param("assigneeId") String assigneeId,
							@Param("offset")int offset,
							@Param("limit")int limit);
	
	int updateStatus(
			@Param("insId")String insId, 
			@Param("status")String status );

	int updateAssigneeByTask(
		@Param("taskId") String taskId, 
		@Param("assignee") String assignee );

	int updateStatusByTask(
		@Param("taskId") String taskId, 
		@Param("status") String status );

	List<Leave> queryMyInitiate(
		@Param("initiator") String initiator,
		@Param("offset")int offset,
		@Param("limit")int limit);

	List<Leave> queryMyApproved(
		@Param("assignee") String assignee );

	Leave queryMyTaskByTaskId(@Param("taskId") String taskId );

	int queryMyInitiateCount(
		@Param("initiator") String initiator,
		@Param("offset")int offset,
		@Param("limit")int limit);

	List<Leave> queryMyApproved(@Param("assigneeId") String assigneeId,
			@Param("offset")int offset,
			@Param("limit")int limit);

	int queryMyApprovedCount(@Param("assigneeId") String assigneeId,
			@Param("offset")int offset,
			@Param("limit")int limit);
	
    long countByExample(LeaveExample example);

    int deleteByExample(LeaveExample example);

    int insert(Leave record);

    int insertSelective(Leave record);

    List<Leave> selectByExample(LeaveExample example);

    int updateByExampleSelective(@Param("record") Leave record, @Param("example") LeaveExample example);

    int updateByExample(@Param("record") Leave record, @Param("example") LeaveExample example);
}