package com.spring.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	// 게시판 상세 보기 이동
	@RequestMapping("/board/detail")
	public String boardGoToDetail(@RequestParam int bbsID, Model model) {
		model.addAttribute("boardvo", boardservice.viewBoard(bbsID));
		return "board/board_detail";
	}

	// 게시판 수정 화면 이동
	@RequestMapping("/board/edit")
	public String boardGoToEdit(@RequestParam int bbsID, Model model, HttpSession session) {
		String temp = boardservice.checkUser(bbsID);
		String id = (String) session.getAttribute("userID");
		if (id.equals(temp)) {
			model.addAttribute("boardvo", boardservice.viewBoard(bbsID));
			return "board/board_edit";
		}
		return "redirect:main";
	}

	// 게시판 게시물 작성 화면 이동
	@RequestMapping("/board/write")
	public String boardGoToWrite() {
		return "board/board_write";
	}

	// 게시판 게시물 수정
	@RequestMapping("/board/board_update")
	public String boardUpdate(@ModelAttribute BoardVO boardvo, HttpSession session) {
		String id = (String) session.getAttribute("userID");
			boardservice.updateBoard(boardvo);
		return "redirect:main";
	}

	// 게시판 게시물 삭제
	@RequestMapping("/board/delete")
	public String boardDelete(@RequestParam int bbsID, HttpSession session) {
		String id = (String) session.getAttribute("userID");
		boardservice.deleteBoard(bbsID);
		return "redirect:main";
	}

	// 게시판 게시물 작성 액션
	@RequestMapping("/board/board_add")
	public String userAdd(@ModelAttribute BoardVO boardvo, HttpSession session) {
		String id = (String) session.getAttribute("userID");
		boardvo.setUserID(id);
		boardservice.insertBoard(boardvo);
		return "redirect:main";
	}

	// 게시판 게시물 보기(메인화면
	@RequestMapping("/board/main")
	public String listAll(Model model) {
		List<BoardVO> list = boardservice.listAll();
		model.addAttribute("list", list);
		return "board/board_main";
	}
}
