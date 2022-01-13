package com.gec.test;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestMain {

	public static void main(String[] args) {
//		Md5Hash md5Hash = new Md5Hash("123", "terry");
//		System.out.println(md5Hash.toString());
		
		String id = "d01".split("d")[1];
		System.out.println(Integer.valueOf(id)+1);
	}
}
