package com.note.cloud_note.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class md5Test {

	@Test
	public void testMd5(){
		String str="123456";
		String md5=DigestUtils.md5Hex(str);
		System.out.println(md5);
		
		//加盐摘要
		String salt="你今天吃了吗?";
		md5=DigestUtils.md5Hex(str+salt);
		System.out.println(md5);
		
	}
	
}
