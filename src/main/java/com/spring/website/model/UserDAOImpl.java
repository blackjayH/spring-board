package com.spring.website.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

	// 01. 전체 회원 목록 조회
	@Override
	public List<UserVO> userList() {
		return sqlSession.selectList("user.userList");
	}

	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace +".getTime");
	}
	
	// 02. 회원 등록
	@Override
	public void insertUser(UserVO uservo) {
		sqlSession.insert(namespace + ".insertUser", uservo);
	}

	// 03. 회원 정보 상세 조회
	@Override
	public UserVO viewUser(String userId) {
		return sqlSession.selectOne("user.viewUser", userId);
	}

	// 04. 회원 정보 수정 처리
	@Override
	public void deleteUser(String userId) {
		sqlSession.delete("user.deleteUser", userId);
	}

	// 05. 회원 정보 삭제 처리
	@Override
	public void updateUser(UserVO vo) {
		sqlSession.update("user.updateUser", vo);

	}

	// 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
	@Override
	public boolean checkPw(String userId, String userPw) {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userPw", userPw);
		int count = sqlSession.selectOne("user.checkPw", map);
		if (count == 1)
			result = true;
		return result;
	}

}
