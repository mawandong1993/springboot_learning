package com.note.cloud_note.service;

import java.util.UUID;

import javax.annotation.Resource;

import com.note.cloud_note.dao.UserDao;
import com.note.cloud_note.exception.PasswordException;
import com.note.cloud_note.exception.UserNameException;
import com.note.cloud_note.exception.UserNotFoundException;
import com.note.cloud_note.vo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Value("#{jdbc.salt}")
	private String salt;
	
	@Resource
	private UserDao dao;

    @Override
	public User login(String name, String password) throws UserNotFoundException, PasswordException {
		
//		String s=null;
//		s.charAt(0);//空指针异常
		System.out.println("login");
		
		if(password==null || password.trim().isEmpty()){
			throw new PasswordException("密码空");
		}
		if(name==null || name.trim().isEmpty()){
			throw new UserNotFoundException("用户名空");
		}
		User user=dao.findUserByName(name);
		if(user==null){
			throw new UserNotFoundException("用户名不存在");
		}
		String md5= DigestUtils.md5Hex(password.trim()+salt);
		if(!user.getPassword().equals(md5)){
			throw new PasswordException("密码错误");
		}
		return user;
	}

    @Override
	public User regist(String name, String nick, String password, String confirm)
			throws UserNameException, PasswordException {
		//检查用户名不能重复
		if(name==null || name.trim().isEmpty()){
			throw new UserNameException("不能空");
		}
		User one=dao.findUserByName(name);
		if(one!=null){
			throw new UserNameException("用户名已注册");
		}
		
		//检查密码
		if(password==null || password.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		if(!password.equals(confirm)){
			throw new PasswordException("两次密码不一致");
		}
		//检查nick
		if(nick ==null || nick.trim().isEmpty()){
			nick = name;
		}
		
		
		String md5=DigestUtils.md5Hex(password.trim()+salt);
		String id=UUID.randomUUID().toString();
		String token=null;
		User user=new User(id, name, md5, token, nick);
		int n=dao.addUser(user);
		if(n!=1){
			throw new RuntimeException("添加失败");
		}
		return user;
	}

}
