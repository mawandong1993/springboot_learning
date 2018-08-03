package com.note.cloud_note.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.NotebookDao;

public class NotebookDaoTest extends BaseTest {

	NotebookDao dao;
	
	@Before
	public void initDao(){
		dao=ctx.getBean("notebookDao",NotebookDao.class);
	}
	
	@Test
	public void testFindNotbooksByUserId(){
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		List<Map<String,Object>> list=dao.findNotebooksByUserId(userId);
		
		for(Map<String,Object> map:list){
			System.out.println(map);
		}
		
	}

}
