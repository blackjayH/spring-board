package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.BoardDAOImpl;
import com.spring.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	BoardDAOImpl boarddao;

	@Override
	public String getTime() {
		return boarddao.getTime();
	}

	// 01. 전체 게시글 조회
	@Override
	public List<BoardVO> listAll() {
		return boarddao.listAll();
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

	// 04. 회원 게시글 수정 처리
	@Override
	public void updateBoard(BoardVO boardvo) {
		boarddao.updateBoard(boardvo);
	}

	// 05. 회원 게시글 삭제 처리
	@Override
	public void deleteBoard(int bbsID) {
		boarddao.deleteBoard(bbsID);
	}

	// 06. 회원 게시글 개수 조회
	@Override
	public int getNext() {
		return boarddao.getNext();
	}

	@Override
	// 07. 회원 게시글 본인 여부 확인
	public String checkUser(int bbsID) {
		return boarddao.checkUser(bbsID);
	}
}
