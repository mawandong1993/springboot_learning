package com.note.cloud_note.service;


import com.note.cloud_note.exception.PasswordException;
import com.note.cloud_note.exception.UserNameException;
import com.note.cloud_note.exception.UserNotFoundException;
import com.note.cloud_note.vo.User;

/*
 * 业务层接口
 */
public interface UserService{
	/**
	 * 登录功能,登陆成功返回用户信息,登录失败则抛出异常.
	 * @param name
	 * @param password
	 * @return	如果登陆成功就返回登录信息
	 * @throws UserNotFoundException	用户不存在
	 * @throws PasswordException	密码错误
	 */
	User login(String name, String password)throws UserNotFoundException,PasswordException;
	/**
	 * UserService中添加注册信息
	 * @param name
	 * @param nick
	 * @param password
	 * @param confirm
	 * @return	注册成功返回用户信息
	 * @throws UserNameException	用户名异常
	 * @throws PasswordException	密码异常
	 */
	User regist(String name, String nick, String password, String confirm) throws UserNameException,PasswordException;
}
