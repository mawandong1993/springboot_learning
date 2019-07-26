package com.note.cloud_note.dao;

import com.note.cloud_note.vo.Post;

public interface PostDao {
	Post findPostById(Integer id);
}
