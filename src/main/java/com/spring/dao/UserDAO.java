package com.spring.dao;

import java.util.List;

import com.spring.vo.UserVO;

public interface UserDAO {
	public String getTime();

	// 01. 전체 회원 목록 조회
	public List<UserVO> listAll();

	// 02. 회원 등록
	public void insertUser(UserVO uservo);

	// 03. 회원 정보 상세 조회
	public UserVO viewUser(String id);

	// 04. 회원 정보 수정 처리
	public void updateUser(UserVO uservo);

	// 05. 회원 정보 삭제 처리
	public void deleteUser(UserVO uservo);

	// 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
	public boolean checkPw(String id, String pw);

	// 07. 아이디 중복 체크
	public boolean checkId(String id);

}
