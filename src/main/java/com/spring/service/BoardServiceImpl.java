package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.BoardDAOImpl;
import com.spring.vo.BoardVO;
import com.spring.vo.Paging;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	BoardDAOImpl boarddao;

	// 00. 전체 게시글 조회(미사용)
	@Override
	public List<BoardVO> listAll() {
		return boarddao.listAll();
	}

	// 01. 페이지별 게시글 조회(메인)
	@Override
	public List<BoardVO> listPage(Paging paging) {
		return boarddao.listPage(paging);
	}

	// 02. 회원 게시글 등록
	@Override
	public void insertBoard(BoardVO boardvo) {
		boarddao.insertBoard(boardvo);
	}

	// 03. 회원 게시글 상세 조회
	@Override
	public BoardVO viewBoard(int bbsID) {
		return boarddao.viewBoard(bbsID);
	}

	// 04. 회원 게시글 수정 
	@Override
	public void updateBoard(BoardVO boardvo) {
		boarddao.updateBoard(boardvo);
	}

	// 05. 회원 게시글 삭제
	@Override
	public void deleteBoard(int bbsID) {
		boarddao.deleteBoard(bbsID);
	}

	// 06. 게시물 작성 시간 등록
	@Override
	public String getTime() {
		return boarddao.getTime();
	}

	// 07. 회원 게시글 개수 조회(게시판 메인)
	@Override
	public int getCount() {
		return boarddao.getCount();
	}	

	// 09. 회원 게시글 본인 여부 확인
	@Override
	public String checkUser(int bbsID) {
		return boarddao.checkUser(bbsID);
	}

	// 10. 회원 게시글 조회수 업데이트
	@Override
	public void updateClick(int bbsID) {
		boarddao.updateClick(bbsID);
	}
}
