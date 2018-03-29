package com.note.cloud_note.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.note.cloud_note.dao.NoteDao;
import com.note.cloud_note.dao.NotebookDao;
import com.note.cloud_note.dao.StarsDao;
import com.note.cloud_note.dao.UserDao;
import com.note.cloud_note.exception.NoteIdNotFoundException;
import com.note.cloud_note.exception.NoteNotFoundException;
import com.note.cloud_note.exception.NotebookIdNotFoundException;
import com.note.cloud_note.exception.UserNotFoundException;
import com.note.cloud_note.vo.Note;
import com.note.cloud_note.vo.Stars;
import com.note.cloud_note.vo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service("noteService")
public class NoteServiceImpl implements NoteService {
	
	@Resource
	private StarsDao starsDao;
	@Resource
	private UserDao userDao;
	@Resource
	private NoteDao noteDao;
	@Resource
	private NotebookDao notebookDao;


	@Override
	public List<Map<String, Object>> listNote(String notebookId)throws NotebookIdNotFoundException {
		
		if(notebookId==null || notebookId.isEmpty() ){
			throw new  NotebookIdNotFoundException("笔记本ID为空");
		}
		List<Map<String,Object>> list=noteDao.findNotes(null,notebookId,"1");
		int n=notebookDao.countNotebookById(notebookId);
		if(n==0){
			throw new NotebookIdNotFoundException("笔记本不存在");
		}
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public Note findNoteById(String noteId) throws NoteIdNotFoundException {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteIdNotFoundException("笔记本ID为空");
		}
		Note note=noteDao.finNoteById(noteId);
		if(note==null){
			throw new NoteIdNotFoundException("笔记本不存在");
		}
		return note;
	}

	@Override
	@Transactional
	public Note addNote(String userId, String notebookId, String note_title)throws NoteIdNotFoundException,NotebookIdNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("用户ID空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}
		if(notebookId==null||notebookId.trim().isEmpty()){
			throw new NotebookIdNotFoundException("ID空");
		}
		int n=notebookDao.countNotebookById(notebookId);
		if(n!=1){
			throw new NotebookIdNotFoundException("没有笔记本");
		}
		if(note_title==null || note_title.trim().isEmpty()){
			note_title="葵花宝典";
		}
		
		Note note=new Note();
		String id=UUID.randomUUID().toString();
		note.setCn_note_id(id);
		note.setCn_user_id(userId);
		note.setCn_notebook_id(notebookId);
		note.setCn_note_title(note_title);
		long time=System.currentTimeMillis();
		note.setCreatDate(new Date());
		note.setUpdateDate(new Date());
		note.setCn_note_status_id("1");
		note.setCn_note_type_id("0");
		note.setCn_note_body("");
		n=noteDao.addNote(note);
		if(n==0){
			throw new NoteIdNotFoundException("添加失败");
		}
		//当前的事务，会传播到addStars方法中，整合为一个事务!
		addStars(userId, 5);
		
		note=noteDao.finNoteById(id);
		return note;
	}

	@Override
	@Transactional
	public boolean updateNote(String noteId, String title, String body) throws NoteNotFoundException {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID不能空");
		}
		Note note=noteDao.finNoteById(noteId);
		if(note==null){
	        throw new NoteNotFoundException("没有对应的笔记");
	    } 
		Note data=new Note();
		if(title!=null && !title.equals(note.getCn_note_title())){
	        data.setCn_note_title(title);
	    }
		if(body!=null&&!body.equals(note.getCn_note_body())){
			data.setCn_note_body(body);
		}
		data.setCn_note_id(noteId);
		data.setUpdateDate(new Date());
		int n=noteDao.updateNote(data);
		return n==1;
	}

	@Override
	public boolean moveNote(String noteId, String bookId) throws NoteNotFoundException, NotebookIdNotFoundException {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID不能空");
		}
		if(bookId==null || bookId.isEmpty() ){
			throw new  NotebookIdNotFoundException("笔记本ID为空");
		}
		Note data=new Note();
		data.setCn_note_id(noteId);
		data.setCn_notebook_id(bookId);
		data.setUpdateDate(new Date());
		int n=noteDao.updateNote(data);
		return n==1;
	}

	@Override
	public boolean deleteNote(String noteId)throws NoteNotFoundException{
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID不能空");
		}
		Note data=new Note();
		data.setCn_note_id(noteId);
		data.setCn_note_status_id("0");
		data.setUpdateDate(new Date());
		int n=noteDao.updateNote(data);
		return n==1;
		
	}

	@Override
	public List<Map<String, Object>> findTrashByUserId(String userId) throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("用户ID空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}
		List<Map<String, Object>> list=noteDao.findNotes(userId, null, "0");
		return list;
	}

	@Override
	public boolean deleteTrash(String noteId) throws NoteNotFoundException {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID不能空");
		}
		Note note=noteDao.finNoteById(noteId);
		if(note==null){
	        throw new NoteNotFoundException("没有对应的笔记");
	    } 
		int n=noteDao.deleteTrash(noteId);
		return n==1;
	}

	@Override
	@Transactional
	public int deleteNotes(String... noteIds) throws NoteNotFoundException {
		for(String id:noteIds){
			int n=noteDao.deleteNoteById(id);
			if(n!=1){
				throw new NoteNotFoundException("笔记本ID错误");
			}
		}
		return noteIds.length;
	}

	@Override
	@Transactional
	public boolean addStars(String userId, int stars) throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("用户ID空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}
		//检查是否有星
		Stars st=starsDao.findStarsByUserId(userId);
		int n;
		if(st==null){//没星星
			String id=UUID.randomUUID().toString();
			st=new Stars(id,userId,stars);
			n=starsDao.InsertStars(st);
			if(n!=1){
				throw new RuntimeException("失败");
			}
		}else{//如果有星星，就在现有的星星数量上增加
			n=st.getStars()+stars;
			if(n<0){
				throw new RuntimeException("扣分太多");
			}
			st.setStars(n);
			n=starsDao.updateStarts(st);
			if(n!=1){
				throw new RuntimeException("失败");
			}
		}
		

		return n==1;
	}

	

}
