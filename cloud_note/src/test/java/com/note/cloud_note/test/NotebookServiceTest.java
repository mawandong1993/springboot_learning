package com.note.cloud_note.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.service.NotebookService;

public class NotebookServiceTest extends BaseTest {
	
	private NotebookService service;
	@Before
	public void init(){
		service=ctx.getBean("notebookservice",NotebookService.class);
	}
	
	@Test
	public void test1(){
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		List<Map<String,Object>>list=service.listNotebooks(userId);
		for(Map<String,Object> map:list){
			System.out.println(map);
		}
	}
	@Test
	public void test2(){
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		List<Map<String,Object>>list=service.listNotebooks(userId, 2);
		for(Map<String,Object> map:list){
			System.out.println(map);
		}
	}

}
