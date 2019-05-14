package com.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	// SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
	// 의존관계 주입(Dependency Injection, DI)
	// 느스한 결함
	// IoC(Inversion of Control, 제어의 역전)

	// Inject애노테이션이 없으면 sqlSession은 null상태이지만
	// Inject애노테이션이 있으면 외부에서 객체를 주입시켜주게 된다.
	// try catch문, finally문, 객체를 close할 필요가 없어졌다.

	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.spring.website.userMapper";

	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	// 01. 전체 회원 목록 조회
	@Override
	public List<UserVO> listAll() {
		return sqlSession.selectList(namespace + ".listAll");
	}

	// 02. 회원 등록
	@Override
	public void insertUser(UserVO uservo) {
		sqlSession.insert(namespace + ".insertUser", uservo);
	}

	// 03. 회원 정보 상세 조회
	@Override
	public UserVO viewUser(String id) {
		return sqlSession.selectOne(namespace + ".viewUser", id);
	}

	// 04. 회원 정보 수정 처리
	@Override
	public void updateUser(UserVO uservo) {
		sqlSession.update(namespace + ".updateUser", uservo);
	}

	// 05. 회원 정보 삭제 처리
	@Override
	public void deleteUser(UserVO uservo) {
		sqlSession.delete(namespace + ".deleteUser", uservo);

	}

	// 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
	@Override
	public boolean checkPw(String id, String pw) {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		int count = sqlSession.selectOne(namespace + ".checkPw", map);
		if (count == 1)
			result = true;
		return result;
	}

	// 07. 아이디 중복 체크
	@Override
	public boolean checkId(String id) {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		int count = sqlSession.selectOne(namespace + ".checkId", map);
		if (count == 1)
			result = true;
		return result;
	}
}
