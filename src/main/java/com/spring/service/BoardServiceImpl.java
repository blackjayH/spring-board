package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.BoardDAOImpl;
import com.spring.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Inject
	BoardDAOImpl boarddao;

	@Override
	public String getTime() {
		return boarddao.getTime();
	}

	@Override
	// 01. 전체 게시글 조회
	public List<BoardVO> listAll() {
		return boarddao.listAll();
	}

	@Override
	// 02. 회원 게시글 등록
	public void insertBoard(BoardVO boardvo) {
		boarddao.insertBoard(boardvo);
	}

	@Override
	// 03. 회원 게시글 상세 조회
	public BoardVO viewBoard(String bbsTitle) {
		return boarddao.viewBoard(bbsTitle);
	}

	@Override
	// 04. 회원 게시글 수정 처리
	public void updateBoard(BoardVO boardvo) {
		boarddao.updateBoard(boardvo);
	}

	@Override
	// 05. 회원 게시글 삭제 처리
	public void deleteBoard(BoardVO boardvo) {
		boarddao.deleteBoard(boardvo);
	}

	@Override
	public int getNext() {
		return boarddao.getNext();
	}
}
