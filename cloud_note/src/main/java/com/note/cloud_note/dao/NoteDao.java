package com.note.cloud_note.dao;

import java.util.List;
import java.util.Map;

import com.note.cloud_note.vo.Note;
import org.apache.ibatis.annotations.Param;


public interface NoteDao {
	
	List<Map<String,Object>>findNotesByNotebookId(String notebookId);
	int addNote(Note note);
	Note finNoteById(String cn_note_id);
	int updateNote(Note note);
	List<Map<String,Object>>findTrashByUserId(String userId);
	int deleteTrash(String noteId);
	
	int deleteNoteById(String noteId);
	int deleteNotesById(@Param("ids") String... ids);
	List<Map<String,Object>>findNotes(@Param("userId") String userId,
                                      @Param("notebookId") String notebookId,
                                      @Param("statusId") String statusId);
}
