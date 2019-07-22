package com.spring.vo;

public class CommentVO {
	private int bbsID; // 게시물 번호
	private String userID; // 댓글 작성 유저
	private int commentID; // 댓글 번호
	private String commentDate; // 댓글 작성 날짜
	private String commentContent; // 댓글 내용
	private int commentAvailable; // 댓글 표시 여부
	private int commentSecret; // 비밀 댓글 여부

	public int getBbsID() {
		return bbsID;
	}

	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getCommentAvailable() {
		return commentAvailable;
	}

	public void setCommentAvailable(int commentAvailable) {
		this.commentAvailable = commentAvailable;
	}

	public int getCommentSecret() {
		return commentSecret;
	}

	public void setCommentSecret(int commentSecret) {
		this.commentSecret = commentSecret;
	}

	@Override
	public String toString() {
		return "CommnetVO [bbsID=" + bbsID + ", userID=" + userID + ", commentID=" + commentID + ", commentDate="
				+ commentDate + ", commnetContent=" + commentContent + ", commentAvailable=" + commentAvailable
				+ ", commentSecret=" + commentSecret + "]";
	}

}
