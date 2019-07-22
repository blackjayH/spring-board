package com.spring.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.vo.BoardVO;
import com.spring.vo.Paging;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace2 = "com.spring.website.boardMapper";

	// 00. 전체 게시글 조회(미사용)
	@Override
	public List<BoardVO> listAll() {
		return sqlSession.selectList(namespace2 + ".listAll");
	}

	// 01. 페이지별 게시글 조회(메인)
	@Override
	public List<BoardVO> listPage(Paging paging) {
		return sqlSession.selectList(namespace2 + ".listPage", paging);
	}

	// 02. 회원 게시글 등록
	@Override
	public void insertBoard(BoardVO boardvo) {
		boardvo.setBbsID(getNext());
		boardvo.setBbsDate(getTime());
		boardvo.setBbsAvailable(1);
		boardvo.setClick(0);
		sqlSession.insert(namespace2 + ".insertBoard", boardvo);
	}

	// 03. 회원 게시글 상세 조회
	@Override
	public BoardVO viewBoard(int bbsID) {
		return sqlSession.selectOne(namespace2 + ".viewBoard", bbsID);
	}

	// 04. 회원 게시글 수정
	@Override
	public void updateBoard(BoardVO boardvo) {
		sqlSession.update(namespace2 + ".updateBoard", boardvo);
	}

	// 05. 회원 게시글 삭제
	@Override
	public void deleteBoard(int bbsID) {
		sqlSession.delete(namespace2 + ".deleteBoard", bbsID);
	}

	// 06. 게시물 작성 시간 등록
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace2 + ".getTime");
	}

	// 07. 회원 게시글 개수 조회(게시판 메인)
	@Override
	public int getCount() {
		int count = sqlSession.selectOne(namespace2 + ".getCount");
		return count;
	};

	// 08. 회원 게시글 개수 조회(게시글 등록)
	@Override
	public int getNext() {
		int count = sqlSession.selectOne(namespace2 + ".getNext");
		return count + 1;
	}

	// 09. 회원 게시글 본인 여부 확인
	@Override
	public String checkUser(int bbsID) {
		String userID = sqlSession.selectOne(namespace2 + ".checkUser", bbsID);
		return userID;
	}

	// 10. 회원 게시글 조회수 업데이트
	@Override
	public void updateClick(int bbsID) {
		sqlSession.update(namespace2 + ".updateClick", bbsID);
	}
}
