package com.gec.domain;

import java.util.Map;

public class PageData {

	private Integer page;
	private Integer limit;
	private Map<String, Object> keywords;

	public PageData( Map data ){
		this.page = getInteger(data,"page",1);
		this.limit = getInteger(data,"limit",10);
		data.remove("page");   //移除页码
		data.remove("limit");  //移除页大小。
		keywords = data;
	}

	private Integer getInteger(Map<String,String> data, String key, 
						Integer defVal ){
		String regex = "\\d+";
		String val = data.get(key);
		if( val==null ){ return defVal; };
		if( !val.matches(regex) ){ return defVal; };
		return Integer.valueOf( val );
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

	public Map<String, Object> getKeywords() {
		return keywords;
	}

	public void setKeywords(Map<String, Object> keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "PageData [page=" + page + ", limit=" + limit + "]";
	}
	
}
