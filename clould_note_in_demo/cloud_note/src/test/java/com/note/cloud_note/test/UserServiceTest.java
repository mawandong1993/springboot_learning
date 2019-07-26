package com.note.cloud_note.test;

import org.junit.Before;
import org.junit.Test;

import com.note.cloud_note.dao.UserDao;
import com.note.cloud_note.vo.User;
import com.note.cloud_note.service.UserService;

public class UserServiceTest extends BaseTest {
	
	UserService service;
	
	@Before
	public void initDao(){
		service=ctx.getBean("userService",UserService.class);
	}

	@Test
	public void testLogin(){
		String name="demo";
		String password="123456";
		User user=service.login(name, password);
		System.out.println(user);
	}
	
	@Test
	public void testRegist(){
		User user=service.regist("faker123", "faker123456", "12345678", "12345678");
		System.out.println(user);
	}

}
