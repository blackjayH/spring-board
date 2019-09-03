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
import com.spring.service.CommentService;
import com.spring.vo.BoardVO;
import com.spring.vo.CommentVO;
import com.spring.vo.Paging;

@Controller
public class BoardController {
	@Inject
	BoardService boardservice;

	@Inject
	CommentService commentservice;

	// 게시판 홈 화면 이동
	@RequestMapping("/board/view/user")
	public String viewUser() {
		return "board/board_user";
	}

	// 게시판 홈 화면 이동
	@RequestMapping("/board/view/home")
	public String viewHome() {
		return "board/board_home";
	}

	// 게시판 로그인 화면 이동
	@RequestMapping("/board/view/login")
	public String viewLogin() {
		return "board/board_login";
	}

	// 게시판 회원가입 화면 이동
	@RequestMapping("/board/view/join")
	public String viewJoin() {
		return "board/board_join";
	}

	// 게시판 회원탈퇴 화면 이동
	@RequestMapping("/board/view/withdrawal")
	public String viewWithdrawal() {
		return "board/board_withdrawal";
	}

	// 게시판 게시물 작성 화면 이동
	@RequestMapping("/board/view/write")
	public String viewWrite() {
		return "board/board_write";
	}

	// 게시판 게시물 수정 화면 이동
	@RequestMapping("/board/view/edit")
	public String viewEdit(@RequestParam int bbsID, Model model, HttpSession session) {
		int countcomment = commentservice.getCount(bbsID);
		model.addAttribute("countcomment", countcomment);
		model.addAttribute("boardvo", boardservice.viewBoard(bbsID));
		return "board/board_edit";
	}

	// 게시판 게시물 상세 보기 이동
	@RequestMapping("/board/view/detail")
	public String viewDetail(@RequestParam int bbsID, Model model) {
		boardservice.updateClick(bbsID); // 조회수 업데이트
		model.addAttribute("boardvo", boardservice.viewBoard(bbsID));
		// 댓글 전부 불러오기
		List<CommentVO> commentlist = commentservice.listAll(bbsID);
		// 댓글 카운트
		int countcomment = commentservice.getCount(bbsID);

		model.addAttribute("countcomment", countcomment);
		model.addAttribute("commentlist", commentlist);
		return "board/board_detail";
	}

	// 게시판 페이지 이동 수정본 (수정 필요 컨트롤러 단순화)
	@RequestMapping("/board/view/paging")
	public String viewPaging(@RequestParam int nowPage, Model model) {
		int count = boardservice.getCount(); // 전체 게시물의 수
		int perPage = 2; // 페이지당 게시물 수
		Paging paging = new Paging(nowPage, perPage, count);

		List<BoardVO> list = boardservice.listPage(paging);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("paging", paging);

		return "board/board_main";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 게시판 게시물 작성 액션
	@RequestMapping("/board/action/add")
	public String addBoard(@ModelAttribute BoardVO boardvo, HttpSession session, Model model) {
		String id = (String) session.getAttribute("userID");
		boardvo.setUserID(id);
		boardservice.insertBoard(boardvo);
		return "redirect:/board/view/paging?nowPage=1";
	}

	// 게시판 게시물 수정 액션
	@RequestMapping("/board/action/update")
	public String updateBoard(@ModelAttribute BoardVO boardvo, HttpSession session) {
		String id = (String) session.getAttribute("userID");
		boardservice.updateBoard(boardvo);
		return "redirect:/board/view/paging?nowPage=1";
	}

	// 게시판 게시물 삭제 액션
	@RequestMapping("/board/action/delete")
	public String deleteBoard(@RequestParam int bbsID, HttpSession session, Model model) {
		String id = (String) session.getAttribute("userID");
		boardservice.deleteBoard(bbsID);
		return "redirect:/board/view/paging?nowPage=1";
	}
}
