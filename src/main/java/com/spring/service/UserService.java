package com.spring.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.vo.UserVO;

public interface UserService {
	// 01. 전체 회원 목록 조회
	public List<UserVO> listAll();

	// 02. 회원 정보 등록
	public void insertUser(UserVO uservo);

	// 03. 회원 정보 수정
	public void updateUser(UserVO uservo);

	// 04. 회원 정보 삭제	
	public void deleteUser(String id, HttpSession session);
	
	// 05. 아이디 중복 체크(회원가입)
	public boolean checkId(String id);

	// 06. 회원 로그인
	public boolean loginUser(UserVO uservo);

	// 07. 회원 정보 확인
	public boolean checkUser(UserVO uservo);
	
	// 08. 회원 로그아웃
	public void logoutUser(HttpSession session);
}
