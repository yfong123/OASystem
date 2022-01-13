package com.gec.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gec.domain.Document;
import com.gec.domain.PageBean;
import com.gec.exception.CustomUploadException;
import com.gec.mapper.DocumentMapper;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentMapper documentMapper;
	// 设置文件上传的位置 (创建好该目录)
	private String basePath = "F://upload/";

	// {a}实现文件的上传。
	// {b}实现数据库的信息保存。
	@Override
	public void saveDocument(Document document) throws Exception {
		MultipartFile file = document.getFile();
		try {
			// 目录是否存在, 没有 --> 创建目录
			File rootDir = new File(basePath);
			boolean isOk;
			if (!rootDir.exists()) {
				isOk = rootDir.mkdir();
				if (!isOk) {// 创建目录失败 --> 异常
					throw new CustomUploadException(CustomUploadException.PATH_ERROR);
				}
			}
			// 文件是否已存在, 存在 --> 异常
			String fileName = document.getFilename();
			File targetFile = new File(basePath+fileName);
			if (targetFile.exists()) {
				throw new CustomUploadException(CustomUploadException.FILE_EXISTS);
			}
			// 正式写入文件
			file.transferTo(targetFile);
			// 设置 uuid 到 Document
			String uuid = UUID.randomUUID().toString();
			uuid = uuid.replace("-", "");
			document.setId(uuid);
			//将文件信息写入数据库
			documentMapper.addDocument(document);
		} catch (SQLException e) {
			e.printStackTrace();
			String msg = e.getMessage();
			boolean isRepeat = msg.contains("Duplicate entry");
			if (isRepeat) {
				throw new CustomUploadException(CustomUploadException.NAME_EXISTS);
			} else {
				throw new CustomUploadException(CustomUploadException.OTHER_ERROR);
			}
		}
	}

	@Override
	public void growDownloadCnt(String id) {
		documentMapper.growDownloadCnt(id);
	}

	@Override
	public Document getDocumentById(String id) {
		Document Document = null;
		Document = documentMapper.getDocumentById(id);
		return Document;
	}

	@Override
	public PageBean<Document> getDocumentList(String documentName,String nickName,String fileName,
									int page,int limit) {
		// 查询结果记录偏移量
		int offset = (page - 1) * limit;
		// 创建 PageBean
		PageBean<Document> pBean = new PageBean<Document>();
		// 获取当前查询到的记录数。
		int count = documentMapper.getDocumentCount(documentName,nickName,fileName);
		// 获取当前查询到的记录数据。
		List<Document> list = documentMapper.getDocumentList(documentName,nickName,fileName, offset, limit);
		// 设置记录数
		pBean.setCount(count);
		// 设置 列表数据
		pBean.setList(list);
		return pBean;
	}

	@Override
	public void delDocument(String id) {
		Document document = documentMapper.getDocumentById(id);
		File file = new File(basePath + document.getFilename());
		if (file.exists()) {
			file.delete();
		}
		documentMapper.delDocumentById(id);
	}

}
