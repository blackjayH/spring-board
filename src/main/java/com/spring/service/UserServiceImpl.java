package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.UserDAOImpl;
import com.spring.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Inject
	UserDAOImpl userdao;

	// 01. 전체 회원 목록 조회
	@Override
	public List<UserVO> listAll() {
		return userdao.listAll();
	}

	// 02. 회원 등록
	@Override
	public void insertUser(UserVO uservo) {
		userdao.insertUser(uservo);
	}

	// 03. 회원 정보 상세 조회
	@Override
	public UserVO viewUser(String id) {
		return userdao.viewUser(id);
	}

	// 04. 회원 정보 수정 처리
	@Override
	public void updateUser(UserVO uservo) {
		userdao.updateUser(uservo);
	}

	// 05. 회원 정보 삭제 처리
	@Override
	public void deleteUser(UserVO uservo) {
		userdao.deleteUser(uservo);
	}

	// 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
	@Override
	public boolean checkPw(String id, String pw) {
		return userdao.checkPw(id, pw);
	}

	// 07. 아이디 중복 체크
	@Override
	public boolean checkId(String id) {
		return userdao.checkId(id);
	}
}
