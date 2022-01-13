package com.gec.service;

import com.gec.domain.Document;
import com.gec.domain.PageBean;

public interface DocumentService {

	void saveDocument(Document Document) throws Exception ;
	
	void growDownloadCnt(String id);

	Document getDocumentById(String id);

	public PageBean<Document> getDocumentList(String documentName,String nickName,String fileName,
									int page,int limit);
	
	void delDocument(String id);
}
