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

	@Override
	// 01. 전체 게시글 조회
	public List<BoardVO> listAll() {
		return sqlSession.selectList(namespace2 + ".listAll");
	}

	@Override
	// 02. 회원 게시글 등록
	public void insertBoard(BoardVO boardvo) {
		sqlSession.insert(namespace2 + ".insertBoard", boardvo);
	}

	@Override
	// 03. 회원 게시글 상세 조회
	public BoardVO viewBoard(String bbsTitle) {
		return sqlSession.selectOne(namespace2 + ".viewBoard", bbsTitle);
	}

	@Override
	// 04. 회원 게시글 수정 처리
	public void updateBoard(BoardVO boardvo) {
		sqlSession.update(namespace2 + ".updateBoard", boardvo);
	}

	@Override
	// 05. 회원 게시글 삭제 처리
	public void deleteBoard(BoardVO boardvo) {
		sqlSession.delete(namespace2 + ".deleteUser", boardvo);
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

}
