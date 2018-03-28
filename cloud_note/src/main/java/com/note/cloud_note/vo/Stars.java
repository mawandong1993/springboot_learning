package com.note.cloud_note.vo;

import vo.BaseVO;

public class Stars extends BaseVO {

	private String id;
	private String userId;
	private int stars;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
}
