package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.UserService;
import com.spring.vo.User;
import com.spring.vo.UserVO;

@Controller
public class UserController {
	@Inject
	UserService userservice;
	
	// 회원 가입시 아이디 중복 확인 액션
	@RequestMapping(value = "/user/action/checkid", method = RequestMethod.POST)
	@ResponseBody
	public String checkId(String id) {
		String str;
		boolean tf = userservice.checkId(id);
		if (tf)
			str = "no";
		else
			str = "yes";
		return str;
	}

	// 회원 가입 액션
	@RequestMapping(value = "/user/action/join", method = RequestMethod.POST)
	@ResponseBody
	public String addUser(@ModelAttribute UserVO uservo) {
		userservice.insertUser(uservo);
		return "yes";
	}

	// 로그인 액션(ID / PW 확인)
	@RequestMapping(value = "/user/action/login", method = RequestMethod.POST)
	@ResponseBody
	public String checkIdPw(@ModelAttribute UserVO uservo, HttpSession session) {
		String str = userservice.loginUser(uservo, session);
		return str;
	}

	// 로그 아웃 액션
	@RequestMapping("/user/action/logout")
	public String logoutUser(HttpSession session) {
		userservice.logoutUser(session);
		return "redirect:/board/view/home";
	}

	// 회원 탈퇴 액션
	@RequestMapping("/user/action/withdrawal")
	@ResponseBody
	public String userDelete(@ModelAttribute UserVO uservo, HttpSession session) {
		String str = userservice.checkUser(uservo);
		if (str.equals("yes")) { // 탈퇴 성공
			userservice.logoutUser(session);
			userservice.deleteUser(uservo);
		}
		return str;
	}

	// JPA 테스트 add
	@RequestMapping("/user/jpa/add")
	@ResponseBody
	@Transactional
	public Map<String, Object> AddjpaUser(@ModelAttribute User user) {
		userservice.insertjpaUser(user);
		Map<String, Object> result = new HashMap<>();
		result.put("result", Boolean.TRUE);
		return result;
	}

	// JPA 테스트 update
	@RequestMapping("/user/jpa/update")
	@ResponseBody
	public Map<String, Object> UpdatejpaUser(@ModelAttribute User user) {
		userservice.updatejpaUser(user);
		Map<String, Object> result = new HashMap<>();
		result.put("result", Boolean.TRUE);
		return result;
	}

	// JPA 테스트 delete
	@RequestMapping("/user/jpa/delete")
	@ResponseBody
	public Map<String, Object> DeletejpaUser(@ModelAttribute User user) {
		userservice.deletejpaUser(user);
		Map<String, Object> result = new HashMap<>();
		result.put("result", Boolean.TRUE);
		return result;
	}
}
