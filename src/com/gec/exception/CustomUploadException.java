package com.gec.exception;

import java.net.URLEncoder;

public class CustomUploadException 
	extends Exception {
	public static final String NAME_EXISTS = "文件名称已存在";
	public static final String FILE_EXISTS = "文件附件已存在";
	public static final String NO_WRITABLE = "文件不可写";
	public static final String PATH_ERROR  = "路径错误";
	public static final String FORMAT_ERROR = "上传数据格式不对";
	public static final String OTHER_ERROR = "其它上传异常";

	public CustomUploadException(String msg){
		super(msg);
	}

	public String getURLMsg() {
		String msg = getMessage();
		try {
			msg = URLEncoder.encode(msg,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "&result=failed&cause="+ msg;
	}
	
	

}
