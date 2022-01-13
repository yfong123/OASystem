package com.gec.mapper;

import com.gec.domain.ProcessConfig;
import com.gec.domain.ProcessConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessConfigMapper {
	
	ProcessConfig getProcConfigByCategory(
			@Param("category") String category );
	

	int getProcCount(@Param("deploymentId")String deploymentId,
					 @Param("procCategory")String procCategory);

	List<ProcessConfig> getProcList(@Param("deploymentId")String deploymentId, 
									@Param("procCategory")String procCategory,  
								  	@Param("offset")Integer offset, 
								  	@Param("limit")Integer limit);
	
	int addProcessConfig(ProcessConfig cfg);
	
    long countByExample(ProcessConfigExample example);

    int deleteByExample(ProcessConfigExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProcessConfig record);

    int insertSelective(ProcessConfig record);

    List<ProcessConfig> selectByExample(ProcessConfigExample example);

    ProcessConfig selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProcessConfig record, @Param("example") ProcessConfigExample example);

    int updateByExample(@Param("record") ProcessConfig record, @Param("example") ProcessConfigExample example);

    int updateByPrimaryKeySelective(ProcessConfig record);

    int updateByPrimaryKey(ProcessConfig record);

}