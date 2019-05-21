package com.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.UserService;
import com.spring.vo.UserVO;

@Controller
public class UserController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
	@Inject
	UserService userservice;

	// 회원 목록 보기
	@RequestMapping("/user/user_list")
	public String listAll(Model model) {
		List<UserVO> list = userservice.listAll();
		model.addAttribute("list", list);
		return "user/user_list";
	}

	// 회원 가입 하러가기
	@RequestMapping("/user/user_join")
	public void userjoin() {

	}

	// 회원 가입 하러가기
	@RequestMapping("/user/user_login")
	public void userlogin() {

	}

	// 회원가입 확인
	@RequestMapping("/user/user_add")
	@ResponseBody
	public String userAdd(@ModelAttribute UserVO uservo, Model model) {
		userservice.insertUser(uservo);
		return "yes";
	}

	// 아이디 중복 확인
	@RequestMapping("/user/user_checkId") // , method = RequestMethod.GET)
	@ResponseBody
	public String checkId(String id) {
		boolean tf = userservice.checkId(id);
		String str;
		if (tf)
			str = "no";
		else
			str = "yes";
		return str;
	}

	/*
	@RequestMapping("/board/board_login")
	@ResponseBody
	public String userGoLogin(@ModelAttribute UserVO uservo) {
		boolean tf = userservice.checkId(uservo.getId());
		boolean tf2 = userservice.checkPw(uservo.getId(), uservo.getPw());
		String str;
		if (tf) {
			if (tf2)
				str = "yes";
			else
				str = "pwno";
		} else {
			str = "idno";
		}
		return str;
	}
	 
	 */

	// 로그인 전 ID / PW 체크
	@RequestMapping("/user/user_checkIdPw")
	@ResponseBody
	public String userCheckIdPw(@ModelAttribute UserVO uservo) {
		boolean tf = userservice.checkId(uservo.getId());
		boolean tf2 = userservice.checkPw(uservo.getId(), uservo.getPw());
		String str;
		if (tf) {
			if (tf2)
				str = "yes";
			else
				str = "pwno";
		} else {
			str = "idno";
		}
		return str;
	}

	// 미사용
	@RequestMapping("/user/user_check")
	public String useCheck(@ModelAttribute UserVO uservo, Model model) {
		//
		boolean result = userservice.checkId(uservo.getId());
		if (result) { // 회원가입 실패(mysql에 id가 존재하는 경우)
			model.addAttribute("message", "아이디 중복O");
			return "redirect:user_join";
		} else { // 회원가입 성공
			model.addAttribute("message", "아이디 중복X");
			return "redirect:user_list";
		}
	}

	// 유저 탈퇴(미사용)
	@RequestMapping("/user/user_leave")
	public String userDelete(@ModelAttribute UserVO uservo, Model model) {
		boolean result = userservice.checkPw(uservo.getId(), uservo.getPw());
		if (result) { // 탈퇴 성공
			userservice.deleteUser(uservo);
			return "redirect:user_list";
		} else { // 탈퇴 실패
			model.addAttribute("message", "탈퇴 실패");
			return "redirect:user_detail";
		}

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

	// 상세정보 확인
	@RequestMapping("/user/user_detail")
	public String memberView(@RequestParam String id, Model model) {
		// 회원 정보를 model에 저장
		model.addAttribute("dto", userservice.viewUser(id));
		// System.out.println("클릭한 아이디 확인 : "+userId);
		logger.info("클릭한 아이디 : " + id);
		// member_view.jsp로 포워드
		return "user/user_detail";
	}

}
