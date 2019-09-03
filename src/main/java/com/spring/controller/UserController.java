package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	///////////////////////////////////////////////////////////////////////////////////////////////

	// 회원 목록 보기(미사용)
	@RequestMapping("/user/user_list")
	public String listAll(Model model) {
		List<UserVO> list = userservice.listAll();
		model.addAttribute("list", list);
		return "user/user_list";
	}

	// 유저 정보 수정(미사용)
	@RequestMapping("/user/user_update")
	public String userUpdate(@ModelAttribute UserVO uservo, Model model) {
		boolean result = userservice.checkPw(uservo.getId(), uservo.getPw());
		if (result) { // 수정 성공
			return "redirect:user_list";
		} else { // 수정 실패
			model.addAttribute("message", "수정 실패");
			return "redirect:user_detail";
		}
	}

	// 상세정보 확인(미사용)
	@RequestMapping("/user/user_detail")
	public String memberView(@RequestParam String id, Model model) {
		model.addAttribute("dto", userservice.viewUser(id));
		return "user/user_detail";
	}

}
