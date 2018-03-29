package com.note.cloud_note.dao;

import com.note.cloud_note.vo.Stars;

public interface StarsDao {
	
	Stars findStarsByUserId(String userId);
	int InsertStars(Stars stars);
	int updateStarts(Stars stars);

}
