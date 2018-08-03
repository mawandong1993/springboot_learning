package com.note.cloud_note.test;

import javax.annotation.Resource;

import com.note.cloud_note.dao.PersonDao;
import com.note.cloud_note.vo.Person;
import org.junit.Before;
import org.junit.Test;



public class PersonDaoTest extends BaseTest {
	private PersonDao dao;
	
	@Before
	public void initDao(){
		dao=ctx.getBean("personDao",PersonDao.class);
	}
	
	@Test
	public void test1(){
		Person p=new Person(null, "洋葱哥");
		System.out.println(p);
		int n=dao.addPerson(p);
		System.out.println(n);
		System.out.println(p);
	}
	
	

}
