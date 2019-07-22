package com.spring.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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

	// 02. 회원 가입
	@Override
	public void insertUser(UserVO uservo) {
		userdao.insertUser(uservo);
	}

	// 03. 회원 정보 상세 조회
	@Override
	public UserVO viewUser(String id) {
		return userdao.viewUser(id);
	}

	// 04. 회원 정보 수정 
	@Override
	public void updateUser(UserVO uservo) {
		userdao.updateUser(uservo);
	}

	// 05. 회원 정보 삭제
	@Override
	public void deleteUser(UserVO uservo) {
		userdao.deleteUser(uservo);
	}

	// 06. 회원 정보 확인(로그인)
	@Override
	public boolean checkPw(String id, String pw) {
		return userdao.checkPw(id, pw);
	}

	// 07. 아이디 중복 체크(회원가입)
	@Override
	public boolean checkId(String id) {
		return userdao.checkId(id);
	}

	// 08. 유저 로그인
	@Override
	public String loginUser(UserVO uservo, HttpSession session) {
		boolean tf = userdao.checkId(uservo.getId());
		boolean tf2 = userdao.checkPw(uservo.getId(), uservo.getPw());
		String str;
		if (tf) {
			if (tf2)
				str = "yes";
			else
				str = "pwno";
		} else {
			str = "idno";
		}
		if (str.equals("yes"))
			session.setAttribute("userID", uservo.getId());
		return str;
	}

	// 09. 유저 로그아웃
	public void logoutUser(HttpSession session) {
		session.invalidate();
	}
}
