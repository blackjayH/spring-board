package com.spring.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.BoardService;
import com.spring.vo.BoardVO;

@Controller
public class BoardController {
	@Inject
	BoardService boardservice;
	
	// 게시판 홈 화면 이동
	@RequestMapping("/board/home")
	public String boardGoToHome() {
		return "board/board_home";
	}

	// 게시판 로그인 화면 이동
	@RequestMapping("/board/login")
	public String boardGoToLogin() {
		return "board/board_login";
	}

	// 게시판 회원가입 화면 이동
	@RequestMapping("/board/join")
	public String boardGoToJoin() {
		return "board/board_join";
	}

	// 게시판 메인 화면 이동
	@RequestMapping("/board/main")
	public String boardGoToMain() {
		return "board/board_main";
	}

	// 게시판 게시물 수정
	@RequestMapping("/board/update")
	public String boardUpdate() {
		return "board/board_update";
	}

	// 게시판 게시물 작성
	@RequestMapping("/board/write")
	public String boardWrite() {
		return "board/board_write";
	}
	
	// 회원가입 확인
		@RequestMapping("/board/board_add")
		@ResponseBody
		public String userAdd(@ModelAttribute BoardVO boardvo, Model model) {
			boardservice.insertBoard(boardvo);
			return "yes";
		}
}
