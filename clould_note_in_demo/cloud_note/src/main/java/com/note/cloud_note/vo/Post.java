package com.note.cloud_note.vo;

import vo.BaseVO;

import java.util.ArrayList;
import java.util.List;

public class Post extends BaseVO {

	private Integer id;
	private String title;
	
	/** 发帖人 */
	private Person person;
	
	/** 当前帖子收到的回复 */
	private List<Comment> comments = new ArrayList<Comment>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}