package com.learning.spring_boot_mybatis_plus.vo;

import vo.BaseVO;

public class User_old extends BaseVO{

	private Long id;
	private String name;
	private String password;
	private String token;
	private String nick;

	public User_old() {
	}

	public User_old(Long id, String name, String password, String token, String nick) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.token = token;
		this.nick = nick;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}
