package com.spring.vo;

public class UserVO { // 유저
	private String id;
	private String pw;
	private int warning;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getWarning() {
		return warning;
	}

	public void setWarning(int warning) {
		this.warning = warning;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + ", warning=" + warning + "]";
	}
}
