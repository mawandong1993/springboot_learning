package com.note.cloud_note.service;

import java.util.List;
import java.util.Map;

import com.note.cloud_note.exception.NoteIdNotFoundException;
import com.note.cloud_note.exception.NoteNotFoundException;
import com.note.cloud_note.exception.UserNotFoundException;
import com.note.cloud_note.vo.Note;
import com.note.cloud_note.exception.NotebookIdNotFoundException;

public interface NoteService {

	List<Map<String,Object>> listNote(String notebookId)throws NotebookIdNotFoundException;
	Note findNoteById(String noteId) throws NoteIdNotFoundException;
	Note addNote(String userId, String notebookId, String note_title)throws NoteIdNotFoundException;
	boolean updateNote(String noteId, String title, String body)throws NoteNotFoundException,NotebookIdNotFoundException;
	boolean moveNote(String noteId, String bookId)throws NoteNotFoundException,NotebookIdNotFoundException;
	public boolean deleteNote(String noteId)throws NoteNotFoundException;
	List<Map<String,Object>> findTrashByUserId(String userId)throws UserNotFoundException;
	boolean deleteTrash(String noteId)throws NoteNotFoundException;
	
	int deleteNotes(String... noteIds)throws NoteNotFoundException;
	boolean addStars(String userId, int stars)throws UserNotFoundException;
}
