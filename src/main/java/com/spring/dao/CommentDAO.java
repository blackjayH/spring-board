package com.spring.dao;

import java.util.List;

import com.spring.vo.CommentVO;

public interface CommentDAO {
	// 00. 시간 입력
	public String getTime();

	// 01. 전체 댓글 조회
	public List<CommentVO> listAll(int bbsID);

	// 02. 회원 댓글 등록
	public void insertComment(CommentVO commentvo);

	// 03. 회원 댓글 상세 조회
	public CommentVO viewComment(int commentID);

	// 04. 회원 댓글 수정 
	public void updateComment(CommentVO commentvo);

	// 05. 회원 댓글 삭제 
	public void deleteComment(int commentID);

	// 06. 회원 댓글 개수 조회(게시판 상세 목록)
	public int getCount(int bbsID);

	// 07. 회원 댓글 본인 여부 확인
	public String checkUser(int commentID);

	// 08. 회원 댓글 개수 조회(댓글 등록)
	public int getNext(int bbsID);

}
