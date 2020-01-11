package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.CommentDAO;
import com.spring.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDAO commentdao;

	// 00. 시간 입력
	@Override
	public String getTime() {
		return commentdao.getTime();
	}

	// 01. 전체 댓글 조회
	@Override
	public List<CommentVO> listAll(int bbsID) {
		return commentdao.listAll(bbsID);
	}

	// 02. 회원 댓글 등록
	@Override
	public void insertComment(CommentVO commentvo) {
		commentdao.insertComment(commentvo);
	}

	// 03. 회원 댓글 상세 조회
	@Override
	public CommentVO viewComment(int commentID) {
		return commentdao.viewComment(commentID);
	}

	// 04. 회원 댓글 수정
	@Override
	public void updateComment(CommentVO commentvo) {
		commentdao.updateComment(commentvo);
	}

	// 05. 회원 댓글 삭제
	@Override
	public void deleteComment(int commentID) {
		commentdao.deleteComment(commentID);
	}

	// 06. 회원 댓글 개수 조회(게시판 상세 목록)
	@Override
	public int getCount(int bbsID) {
		return commentdao.getCount(bbsID);
	}

	@Override
	// 07. 회원 댓글 본인 여부 확인
	public String checkUser(int commentID) {
		return commentdao.checkUser(commentID);
	}
}
