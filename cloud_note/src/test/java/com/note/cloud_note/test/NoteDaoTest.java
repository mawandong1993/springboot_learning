package com.note.cloud_note.test;

import java.util.List;
import java.util.Map;

import com.note.cloud_note.dao.NoteDao;
import org.junit.Before;
import org.junit.Test;


public class NoteDaoTest extends BaseTest {
	NoteDao dao;
	
	@Before
	public void initDao(){
		dao=ctx.getBean("noteDao",NoteDao.class);
	}
	
	@Test
	public void test1(){
		List<Map<String,Object>>list= dao.findNotesByNotebookId("6d763ac9-dca3-42d7-a2a7-a08053095c08");
		System.out.println(list);
	}
	
	@Test
	public void testDeletNotes(){
		
		String id1="f4594f33-06d4-47dc-87cf-c3bd20e5a23f";
		String id2="f4cce0e5-ba14-4deb-bc04-e396f53c40f3";
		String id3="f74d03aa-d01d-4989-95e8-4757d6ca8a2a";
		int n=dao.deleteNotesById(id1,id2,id3);
		System.out.println(n);
		
	}
	
}
