package com.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "com.spring.website.userMapper";

	// 01. 전체 회원 목록 조회
	@Override
	public List<UserVO> listAll() {
		return sqlSession.selectList(namespace + ".listAll");
	}

	// 02. 회원 정보 가입
	@Override
	public void insertUser(UserVO uservo) {
		sqlSession.insert(namespace + ".insertUser", uservo);
	}

	// 03. 회원 정보 수정
	@Override
	public void updateUser(UserVO uservo) {
		sqlSession.update(namespace + ".updateUser", uservo);
	}

	// 04. 회원 정보 삭제
	@Override
	public void deleteUser(String id) {
		sqlSession.delete(namespace + ".deleteUser", id);
	}

	// 05. 회원 정보 조회
	@Override
	public UserVO viewUser(UserVO uservo) {
		return sqlSession.selectOne(namespace + ".viewUser", uservo.getId());
	}

	// 06. 아이디 중복 체크(회원가입)
	@Override
	public boolean checkId(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		int count = sqlSession.selectOne(namespace + ".checkId", map);
		if (count == 0)
			return true;
		return false;
	}
}
