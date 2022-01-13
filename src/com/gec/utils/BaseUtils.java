package com.gec.utils;

import java.util.UUID;

public class BaseUtils {

	public static String makeUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		return uuid;
	}

}
