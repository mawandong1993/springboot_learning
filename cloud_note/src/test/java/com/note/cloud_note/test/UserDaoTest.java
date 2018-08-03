package com.note.cloud_note.test;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.UserService;

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
