package com.gec.converters;

import org.springframework.core.convert.converter.Converter;

import com.gec.domain.User;

public class UserConverter implements Converter<String, User>{

	@Override
	public User convert(String line) {
		System.err.println(line);
		String[] data = line.split(",");
		User user = new User();
		user.setUsername(data[0]);
		user.setPassword(data[1]);
		user.setNickname(data[2]);
		return user;
	}

}
