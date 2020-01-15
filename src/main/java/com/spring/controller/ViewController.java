package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.service.BoardService;
import com.spring.service.CommentService;
import com.spring.service.UserService;
import com.spring.vo.BoardVO;
import com.spring.vo.CommentVO;
import com.spring.vo.Paging;

@Controller
public class ViewController {
	@Autowired
	BoardService boardservice;

	@Autowired
	CommentService commentservice;

	@Autowired
	UserService userservice;

	// 메인
	@RequestMapping("/")
	public String main() {
		return "board/board_start";
	}

	// 게시판 홈 화면 이동
	@RequestMapping("/board/view/home")
	public String viewHome() {
		return "board/board_home";
	}

	// 게시판 회원가입 화면 이동
	@RequestMapping("/board/view/join")
	public String viewJoin() {
		return "board/board_join";
	}

	// 게시판 유저 정보 수정 전에 체크화면
	@RequestMapping("/board/view/check")
	public String viewCheck() {
		return "board/board_check";
	}

	// 게시판 유저 수정 화면
	@RequestMapping("/board/view/user")
	public String viewUser() {
		return "board/board_user";
	}

	// 게시판 로그인 화면 이동
	@RequestMapping("/board/view/login")
	public String viewLogin() {
		return "board/board_login";
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
	@Transactional
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

	// Restful API 게시물 하나 로드(상세 보기)
	@RequestMapping(value = "/board/{bbsID}", method = RequestMethod.GET)
	public String detailBoard(@PathVariable int bbsID, Model model) {
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
		int perPage = 10; // 페이지당 게시물 수
		Paging paging = new Paging(nowPage, perPage, count);

		List<BoardVO> list = boardservice.listPage(paging);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("paging", paging);

		return "board/board_main";
	}

	// 로그 아웃 액션
	@RequestMapping("/logout")
	public String logoutUser(HttpSession session) {
		userservice.logoutUser(session);
		return "redirect:/board/view/home";
	}

}
