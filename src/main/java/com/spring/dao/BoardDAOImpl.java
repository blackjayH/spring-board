package com.spring.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace2 = "com.spring.website.boardMapper";

	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace2 + ".getTime");
	}

	// 01. 전체 게시글 조회
	@Override
	public List<BoardVO> listAll() {
		return sqlSession.selectList(namespace2 + ".listAll");
	}

	// 02. 회원 게시글 등록
	@Override
	public void insertBoard(BoardVO boardvo) {
		boardvo.setBbsID(getNext());
		// boardvo.setUserID("a");
		boardvo.setBbsDate(getTime());
		boardvo.setBbsAvailable(1);
		sqlSession.insert(namespace2 + ".insertBoard", boardvo);
	}

	// 03. 회원 게시글 상세 조회
	@Override
	public BoardVO viewBoard(int bbsID) {
		return sqlSession.selectOne(namespace2 + ".viewBoard", bbsID);
	}

	// 04. 회원 게시글 수정 처리
	@Override
	public void updateBoard(BoardVO boardvo) {
		// System.out.println(boardvo.getBbsID());
		// System.out.println(boardvo.getBbsTitle());
		// System.out.println(boardvo.getBbsContent());
		sqlSession.update(namespace2 + ".updateBoard", boardvo);
	}

	// 05. 회원 게시글 삭제 처리
	@Override
	public void deleteBoard(int bbsID) {
		sqlSession.delete(namespace2 + ".deleteBoard", bbsID);
	}

	@Override
	public int getNext() {
		int count = sqlSession.selectOne(namespace2 + ".getNext");
		if (count == 0)
			count = 1;
		else
			count = count + 1;
		return count;
	}

	// 07. 회원 게시글 본인 여부 확인
	@Override
	public String checkUser(int bbsID) {
		String userID = sqlSession.selectOne(namespace2 + ".checkUser", bbsID);
		return userID;
	}

}
