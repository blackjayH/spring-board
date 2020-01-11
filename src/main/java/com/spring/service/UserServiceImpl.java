package com.spring.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.UserDAO;
import com.spring.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userdao;

	// 01. 전체 회원 목록 조회
	@Transactional
	@Override
	public List<UserVO> listAll() {
		return userdao.listAll();
	}

	// 02. 회원 정보 등록
	@Transactional
	@Override
	public void insertUser(UserVO uservo) {
		userdao.insertUser(uservo);
	}

	// 03. 회원 정보 수정
	@Transactional
	@Override
	public void updateUser(UserVO uservo) {
		userdao.updateUser(uservo);
	}

	// 04. 회원 정보 삭제
	@Transactional
	@Override
	public void deleteUser(String id, HttpSession session) {
		String userID = (String) session.getAttribute("userID");
		userdao.deleteUser(id);
		if (!userID.equals("admin")) 
			logoutUser(session);
	}

	// 05. 아이디 중복 체크(회원가입)
	@Transactional
	@Override
	public boolean checkId(String id) {
		return userdao.checkId(id);
	}

	// 06. 회원 로그인
	@Transactional
	@Override
	public boolean loginUser(UserVO uservo) {
		UserVO temp = userdao.viewUser(uservo);
		return uservo.getPw().equals(temp.getPw());
	}

	// 07. 회원 정보 확인
	@Transactional
	@Override
	public boolean checkUser(UserVO uservo) {
		UserVO temp = userdao.viewUser(uservo);
		return uservo.getPw().equals(temp.getPw());
	}

	// 08. 회원 로그아웃
	@Transactional
	@Override
	public void logoutUser(HttpSession session) {
		session.invalidate();
	}

}
