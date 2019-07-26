package com.note.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.note.cloud_note.service.NotebookService;
import com.note.cloud_note.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("/notebook")
public class NotebookController extends AbstractController {
	
	@Resource
	private NotebookService notebookService;
	
	@ResponseBody
	@RequestMapping("/list.do")
	public JsonResult listNotebooks(String userId){
		List<Map<String,Object>> list=notebookService.listNotebooks(userId);
		return new JsonResult(list);
	}
	@ResponseBody
	@RequestMapping("/page.do")
	public JsonResult listNotebooks(String userId,Integer page){
		List<Map<String,Object>> list=notebookService.listNotebooks(userId,page);
		return new JsonResult(list);
	}

}
