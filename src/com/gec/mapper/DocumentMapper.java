package com.gec.mapper;

import com.gec.domain.Document;
import com.gec.domain.DocumentExample;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentMapper {

	public void addDocument(Document document) throws SQLException;
	
	public void growDownloadCnt(@Param("id")String id);
	
	public Document getDocumentById(@Param("id")String id);
	
	public List<Document> getDocumentList(@Param("documentName")String documentName,
											@Param("nickName")String nickName,
											@Param("fileName")String fileName,
										  @Param("offset")Integer offset, 
										  @Param("limit")Integer limit) ;
	
	public int getDocumentCount(@Param("documentName")String documentName,
			@Param("nickName")String nickName,
			@Param("fileName")String fileName);
	
	void delDocumentById(@Param("id")String id);
	
    long countByExample(DocumentExample example);

    int deleteByExample(DocumentExample example);

    int deleteByPrimaryKey(String id);

    int insert(Document record);

    int insertSelective(Document record);

    List<Document> selectByExample(DocumentExample example);

    Document selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Document record, @Param("example") DocumentExample example);

    int updateByExample(@Param("record") Document record, @Param("example") DocumentExample example);

    int updateByPrimaryKeySelective(Document record);

    int updateByPrimaryKey(Document record);
}