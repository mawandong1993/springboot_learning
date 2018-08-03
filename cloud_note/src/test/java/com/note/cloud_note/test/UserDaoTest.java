package com.note.cloud_note.test;

import java.util.UUID;

import com.note.cloud_note.dao.UserDao;
import com.note.cloud_note.service.UserService;
import com.note.cloud_note.vo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;


public class UserDaoTest extends BaseTest{
	UserDao dao;
	UserService service;
	
	@Before
	public void initDao(){
		dao=ctx.getBean("userDao",UserDao.class);
		service=ctx.getBean("userService",UserService.class);
	}
	
	@Test
	public void testFindUserByName(){
		String name="demo";
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user=dao.findUserByName(name);
		System.out.println(user);
	}
	
	@Test
	public void testAddUser(){
		String id=UUID.randomUUID().toString();
		String name ="FFF";
		String salt="你今天吃了吗?";
		String password=DigestUtils.md5Hex("123456"+salt);
		String token="";
		String nick="";
		User user=new User(id, name, password, token, nick);
		int n=dao.addUser(user);
		System.out.println(n);
	}
	
	
	
}
