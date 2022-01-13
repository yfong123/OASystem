package com.gec.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.gec.domain.Permission;
import com.gec.mapper.MenuMapper;

public class FilterDataFactory {

	@Autowired
	private MenuMapper menuMapper;

	private Map<String, String> definitions = new LinkedHashMap<String, String>();
	private String regex = "\\s*([^\\s]+)\\s*=\\s*([^\\s]+)\\s*";
	
	private String[] trimSpace(String line,String regex) {
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(line);
		if (!matcher.matches()) {
			throw new RuntimeException("配置格式有误");
		}
		return new String[] {matcher.group(1),matcher.group(2)};
	}
	
	public void setAnonymousFilter(String definition) {
		String[] lines = definition.split("\n");
		for (String L : lines) {
			L = L.trim();
			if (L.length()==0) {
				continue;
			}
			String[] data = trimSpace(L, regex);
			System.out.println(data[0]+":"+data[1]);
			definitions.put(data[0], data[1]);
		}
	}
	
	public Map<String, String> getFilterDefinitions(){
		Set<Permission> sysPerms = menuMapper.getSysPermissions();
		for (Permission permission : sysPerms) {
			StringBuffer sb = new StringBuffer("perms[");
			sb.append(permission.getPermission());
			sb.append("]");
			definitions.put(permission.getMapping(),sb.toString());
		}
		definitions.put("/**", "authc");
		return definitions;
 	}
	
	public static void main(String[] args) {
		String text = " /User/index = anon \n"+" /User/edit = anon  \n"+"  \n"+" ";
		FilterDataFactory filterDataSouce = new FilterDataFactory();
		//filterDataSouce.setAnonymousFilter(text);
		Map<String, String> map = filterDataSouce.getFilterDefinitions();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			System.out.println(key+":"+map.get(key));
		}
	}
}









