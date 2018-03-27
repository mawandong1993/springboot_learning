package com.note.cloud_note.vo;

public class Stars {

	private String id;
	private String userId;
	private int stars;
	
	public Stars(String id, String userId, int stars) {
		super();
		this.id = id;
		this.userId = userId;
		this.stars = stars;
	}
	public Stars() {
	}
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
	@Override
	public String toString() {
		return "Stars [id=" + id + ", userId=" + userId + ", stars=" + stars + "]";
	}
	
	
}
