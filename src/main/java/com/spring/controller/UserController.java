package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.UserService;
import com.spring.vo.UserVO;

@RestController
public class UserController {
	@Autowired
	UserService userservice;

	// 회원 정보 등록
	@RequestMapping(value = "/user", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public Map<String, Object> insertUser(@RequestBody UserVO uservo) {
		Map<String, Object> result = new HashMap<String, Object>();
		userservice.insertUser(uservo);
		result.put("result", Boolean.TRUE);
		return result;
	}

	// 아이디 중복 체크(회원가입)
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public Map<String, Object> checkId(@PathVariable String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (userservice.checkId(id))
			result.put("result", Boolean.TRUE);
		else
			result.put("result", Boolean.FALSE);
		return result;
	}

	// 회원 정보 수정
	@RequestMapping(value = "/user", method = RequestMethod.PUT, headers = { "Content-type=application/json" })
	public Map<String, Object> updateUser(@RequestBody UserVO uservo) {
		Map<String, Object> result = new HashMap<String, Object>();
		userservice.updateUser(uservo);
		result.put("result", Boolean.TRUE);
		return result;
	}

	// 회원 정보 삭제
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteUser(@PathVariable String id, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		userservice.deleteUser(id, session);
		result.put("result", Boolean.TRUE);
		return result;
	}

	// 회원 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public Map<String, Object> loginUser(@RequestBody UserVO uservo, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (userservice.checkId(uservo.getId())) { // 0
			result.put("result", Boolean.FALSE);
			result.put("message", "없는 ID");
		} else {
			if (userservice.loginUser(uservo)) {
				session.setAttribute("userID", uservo.getId());
				result.put("result", Boolean.TRUE); // 로그인 성공
			} else {
				result.put("result", Boolean.FALSE); // 로그인 실패
				result.put("message", "비밀번호 체크");
			}
		}
		return result;
	}

	// 회원 정보 확인
	@RequestMapping(value = "/check", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public Map<String, Object> checkUser(@RequestBody UserVO uservo) {
		Map<String, Object> result = new HashMap<String, Object>();
		userservice.checkUser(uservo);
		result.put("result", Boolean.TRUE);
		return result;
	}
}
