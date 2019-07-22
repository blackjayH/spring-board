package com.spring.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.vo.CommentVO;

@Repository
public class CommentDAOImpl implements CommentDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace3 = "com.spring.website.commentMapper";

	// 00. 시간 입력
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace3 + ".getTime");
	}

	// 01. 전체 댓글 조회
	@Override
	public List<CommentVO> listAll(int bbsID) {
		return sqlSession.selectList(namespace3 + ".listAll", bbsID);
	}

	// 02. 회원 댓글 등록
	@Override
	public void insertComment(CommentVO commentvo) {
		commentvo.setCommentID(getNext(commentvo.getBbsID()));
		commentvo.setCommentDate(getTime());
		commentvo.setCommentAvailable(1);
		commentvo.setCommentSecret(0);
		sqlSession.insert(namespace3 + ".insertComment", commentvo);
	}

	// 03. 회원 댓글 상세 조회
	@Override
	public CommentVO viewComment(int commentID) {
		return sqlSession.selectOne(namespace3 + ".viewComment", commentID);
	}

	// 04. 회원 댓글 수정
	@Override
	public void updateComment(CommentVO commentvo) {
		sqlSession.update(namespace3 + ".updateComment", commentvo);
	}

	// 05. 회원 댓글 삭제
	@Override
	public void deleteComment(int commentID) {
		sqlSession.delete(namespace3 + ".deleteComment", commentID);
	}

	// 06. 회원 댓글 개수 조회(게시판 상세 목록)
	@Override
	public int getCount(int bbsID) {
		int count = sqlSession.selectOne(namespace3 + ".getCount", bbsID);
		return count;
	}

	// 07. 회원 댓글 본인 여부 확인
	@Override
	public String checkUser(int commentID) {
		String userID = sqlSession.selectOne(namespace3 + ".checkUser", commentID);
		return userID;
	}

	// 08. 회원 댓글 개수 조회(댓글 등록)
	@Override
	public int getNext(int bbsID) {
		int count = sqlSession.selectOne(namespace3 + ".getNext", bbsID);
		return count + 1;
	}
}
