package com.spring.dao;

import java.util.List;

import com.spring.vo.BoardVO;
import com.spring.vo.Paging;

public interface BoardDAO {
	// 00. 전체 게시글 조회(미사용)
	public List<BoardVO> listAll();

	// 01. 페이지별 게시글 조회(메인)
	public List<BoardVO> listPage(Paging paging);

	// 02. 회원 게시글 등록
	public void insertBoard(BoardVO boardvo);

	// 03. 회원 게시글 상세 조회
	public BoardVO viewBoard(int bbsID);

	// 04. 회원 게시글 수정
	public void updateBoard(BoardVO boardvo);

	// 05. 회원 게시글 삭제
	public void deleteBoard(int bbsID);

	// 06. 게시물 작성 시간 등록
	public String getTime();

	// 07. 회원 게시글 개수 조회(게시판 메인)
	public int getCount();

	// 08. 회원 게시글 개수 조회(게시글 등록)
	public int getNext();

	// 09. 회원 게시글 본인 여부 확인
	public String checkUser(int bbsID);

	// 10. 회원 게시글 조회수 업데이트
	public void updateClick(int bbsID);
}
