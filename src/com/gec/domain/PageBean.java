package com.gec.domain;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class PageBean <T> {

	private Integer count;  //总记录数
	private Integer page;   //页码..
	private Integer limit;  //页大小
	private List<T> list;   //记录列表
	private int code = 0;
	private String msg = "";

	public PageBean(){}
	public PageBean(int page, int limit){
		this.page = page;
		this.limit = limit;
	}
	public String toJSON(){
		JSONObject jsObj = new JSONObject();
		jsObj.put("code", code);
		jsObj.put("msg", msg);
		jsObj.put("count", count);
		jsObj.put("data", list);
		return jsObj.toString();
	}

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}


