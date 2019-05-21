package com.spring.dao;

import java.util.List;

import com.spring.vo.BoardVO;

public interface BoardDAO {
	public String getTime();

	// 01. 전체  게시글 조회
	public List<BoardVO> listAll();

	// 02. 회원 게시글 등록
	public void insertBoard(BoardVO boardvo);

	// 03. 회원 게시글 상세 조회
	public BoardVO viewBoard(String bbsTitle);

	// 04. 회원 게시글 수정 처리
	public void updateBoard(BoardVO boardvo);

	// 05. 회원 게시글 삭제 처리
	public void deleteBoard(BoardVO boardvo);

	// 06. 회원 게시글 개수 조회
	public int getNext();
		
	
	// 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
	//public boolean checkPw(String id, String pw);

	// 07. 아이디 중복 체크
	//public boolean checkId(String id);
}
