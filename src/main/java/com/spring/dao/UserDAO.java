package com.spring.dao;

import java.util.List;

import com.spring.vo.UserVO;

public interface UserDAO {
	// 01. admin 전체 회원 조회
	public List<UserVO> listAll();

	// 02. 회원 정보 등록
	public void insertUser(UserVO uservo);

	// 03. 회원 정보 수정
	public void updateUser(UserVO uservo);

	// 04. 회원 정보 삭제
	public void deleteUser(String id);

	// 05. 회원 정보 조회
	public UserVO viewUser(UserVO uservo);

	// 06. 아이디 중복 체크(회원가입)
	public boolean checkId(String id);
}
