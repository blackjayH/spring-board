package com.spring.dao;

import java.util.List;

import com.spring.vo.User;
import com.spring.vo.UserVO;

public interface UserDAO {
	// 00. 시간 입력
	public String getTime();

	// 01. 전체 회원 목록 조회
	public List<UserVO> listAll();

	// 02. 회원 가입
	public void insertUser(UserVO uservo);

	// 03. 회원 정보 상세 조회
	public UserVO viewUser(String id);

	// 04. 회원 정보 수정
	public void updateUser(UserVO uservo);

	// 05. 회원 정보 삭제
	public void deleteUser(UserVO uservo);

	// 06. 회원 정보 확인(로그인)
	public boolean checkPw(String id, String pw);

	// 07. 아이디 중복 체크(회원가입)
	public boolean checkId(String id);

	// jpa 회원 가입
	public void insertjpaUser(User user);

	// jpa 회원 정보 상세 조회
	public User viewjpaUser(User user);

	// jpa 회원 정보 수정
	public void updatejpaUser(User user);

	// jpa 회원 정보 삭제
	public void deletejpaUser(User user);

}
