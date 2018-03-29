package com.note.cloud_note.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.note.cloud_note.service.NoteService;
import com.note.cloud_note.util.JsonResult;
import com.note.cloud_note.vo.Note;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/note")
public class NoteController extends AbstractController {
	
	@Resource
	private NoteService noteService;

	@RequestMapping("list.do")
	@ResponseBody
	public JsonResult listNote(String notebookId){
		List<Map<String,Object>> list=noteService.listNote(notebookId);
		return new JsonResult(list);
	}
	
	@RequestMapping("body.do")
	@ResponseBody
	public JsonResult NoteBody(String noteId){
		Note note=noteService.findNoteById(noteId);
		return new JsonResult(note);
	}
	
	@RequestMapping("addnote.do")
	@ResponseBody
	public JsonResult addnote(String userId,String notebookId,String note_title){
		Note note=noteService.addNote(userId, notebookId, note_title);
		return new JsonResult(note);
	}
	
	@RequestMapping("updatenote.do")
	@ResponseBody
	public JsonResult updatenote(String noteId,String title,String body){
		boolean b=noteService.updateNote(noteId, title, body);
		return new JsonResult(b);
	}
	
	@RequestMapping("move.do")
	@ResponseBody
	public JsonResult moveNote(String noteId,String bookId){
		boolean b=noteService.moveNote(noteId, bookId);
		return new JsonResult(b);
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public JsonResult deleteNote(String noteId){
		boolean b=noteService.deleteNote(noteId);
		return new JsonResult(b);
	}
	
	@RequestMapping("trash.do")
	@ResponseBody
	public JsonResult findTrash(String userId){
		List<Map<String, Object>>list=noteService.findTrashByUserId(userId);
		return new JsonResult(list);
	}
	
	@RequestMapping("deleteTrash.do")
	@ResponseBody
	public JsonResult deleteTrash(String noteId){
		boolean b=noteService.deleteTrash(noteId);
		return new JsonResult(b);
	}

}
