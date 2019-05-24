package com.spring.service;

import java.util.List;

import com.spring.vo.BoardVO;

public interface BoardService {
	public String getTime();

	// 01. 전체  게시글 조회
	public List<BoardVO> listAll();

	// 02. 회원 게시글 등록
	public void insertBoard(BoardVO boardvo);

	// 03. 회원 게시글 상세 조회
	public BoardVO viewBoard(int bbsID);

	// 04. 회원 게시글 수정 처리
	public void updateBoard(BoardVO boardvo);

	// 05. 회원 게시글 삭제 처리
	public void deleteBoard(int bbsID);

	// 06. 회원 게시글 개수 조회
	public int getNext();
	
	// 07. 회원 게시글 본인 여부 확인
	public String checkUser(int bbsID);

}
