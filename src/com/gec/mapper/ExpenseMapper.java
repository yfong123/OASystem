package com.gec.mapper;

import com.gec.domain.Expense;
import com.gec.domain.ExpenseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpenseMapper {
	
	String queryUidByRoleDept(@Param("roleName")String roleName,
							  @Param("deptName")String deptName);

	List<Expense> queryMyInitiate(
			@Param("initiator") String initiator,
			@Param("offset")int offset,
			@Param("limit")int limit);

	int queryMyInitiateCount(
			@Param("initiator") String initiator,
			@Param("offset")int offset,
			@Param("limit")int limit);

	List<Expense> queryWaitMyApprove(@Param("assigneeId") String assigneeId,
									@Param("offset")int offset,
									@Param("limit")int limit);
	
	int queryWaitMyApproveCount(@Param("assigneeId") String assigneeId,
			@Param("offset")int offset,
			@Param("limit")int limit);

	Expense queryMyTaskByTaskId(@Param("taskId") String taskId );

	List<Expense> queryMyApproved(@Param("assigneeId") String assigneeId,
			@Param("offset")int offset,
			@Param("limit")int limit);

	int queryMyApprovedCount(@Param("assigneeId") String assigneeId,
			@Param("offset")int offset,
			@Param("limit")int limit);
	
	int updateStatus(@Param("insId")String insID, @Param("status")String status);
	
	int updateAssigneeAndTask( 
			@Param("insId")String insId, 
			@Param("taskId")String taskId,
			@Param("assignee") String assignee );
	
	
	
	
	
	
    long countByExample(ExpenseExample example);

    int deleteByExample(ExpenseExample example);

    int insert(Expense record);

    int insertSelective(Expense record);

    List<Expense> selectByExample(ExpenseExample example);

    int updateByExampleSelective(@Param("record") Expense record, @Param("example") ExpenseExample example);

    int updateByExample(@Param("record") Expense record, @Param("example") ExpenseExample example);

	

}