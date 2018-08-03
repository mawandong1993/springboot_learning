package com.note.cloud_note.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.note.cloud_note.exception.NoteIdNotFoundException;
import com.note.cloud_note.exception.NoteNotFoundException;
import com.note.cloud_note.exception.NotebookIdNotFoundException;
import com.note.cloud_note.service.NoteService;
import org.junit.Before;
import org.junit.Test;


public class NoteServiceTest extends BaseTest {

	
	private NoteService service;
	@Before
	public void init(){
		service=ctx.getBean("noteService",NoteService.class);
	}
	
	@Test
	public void test1() throws NotebookIdNotFoundException {
		String id="6d763ac9-dca3-42d7-a2a7-a08053095c08";
		List<Map<String,Object>> list=service.listNote(id);
		System.out.println(list);
		System.out.println(list.size());
	}
	
	
	@Test
	public void test2() throws NoteIdNotFoundException {
		String id="046b0110-67f9-48c3-bef3-b0b23bda9d4e";
		
	}
	
	@Test
	public void test3(){
		String userId="48595f52-b22c-4485-9244-f4004255b972";
		String notebookId="12119052-874c-4341-b85d-6529e171ed83";
		String note_title="123456";
		service.addNote(userId, notebookId, note_title);
		
	}
	
	@Test
	public void test4() throws NoteNotFoundException {
		String id="827f6e8f-cab4-44e1-90da-56b187686aea";
		String title="demo0100";
		String body="<p>测试123456</p>";
		
		boolean a=service.updateNote(id, title, body);
		System.out.println(a);
	}
	
	@Test
	public void test5(){
		String id1="3febebb3-a1b7-45ac-83ba-50cdb41e5fc1";
		String id2="9187ffd3-4c1e-4768-9f2f-c600e835b823";
		String id3="ebd65da6-3f90-45f9-b045-782928a5e2c0";
		String id4="fed920a0-573c-46c8-ae4e-368397846efd";
		int n=service.deleteNotes(id1,id2,id3,id4);
//相当于		int n=service.deleteNotes(new String[]{id1,id2,id3,id4});
		System.out.println(n);
	}
	
	@Test
	public void test6(){
		String userId="01a503e3-b620-4773-96c8-0350e860da25";
		int stars=5;
		boolean b=service.addStars(userId, stars);
		System.out.println(b);
	}

}
