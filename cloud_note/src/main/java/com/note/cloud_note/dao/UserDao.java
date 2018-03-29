package com.note.cloud_note.dao;


import com.note.cloud_note.vo.User;

public interface UserDao {
	
	User findUserByName(String name);
	int addUser(User user);
	User findUserById(String userId);
	
}
