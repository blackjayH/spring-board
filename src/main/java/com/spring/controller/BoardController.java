package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
public class BoardController {
	@Autowired
	BoardService boardservice;

	@Autowired
	CommentService commentservice;

	@Autowired
	UserService userservice;
	
	// 로그 아웃 액션
	@RequestMapping("/logout")
	public String logoutUser(HttpSession session) {
		userservice.logoutUser(session);
		return "redirect:/board/view/home";
	}

	// 게시판 게시물 수정 화면 이동
	@RequestMapping("/board2/view/edit")
	public String viewEdit(@RequestParam int bbsID, Model model, HttpSession session) {
		int countcomment = commentservice.getCount(bbsID);
		model.addAttribute("countcomment", countcomment);
		model.addAttribute("boardvo", boardservice.viewBoard(bbsID));
		return "board/board_edit";
	}

	// 게시판 게시물 상세 보기 이동
	@RequestMapping("/board2/view/detail")
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
	@RequestMapping("/board2/view/paging")
	public String viewPaging(@RequestParam int nowPage, Model model) {
		int count = boardservice.getCount();
		// 페이지당 게시물 개수
		int perPage = 2;
		Paging paging = new Paging(nowPage, perPage, count);

		List<BoardVO> list = boardservice.listPage(paging);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("paging", paging);

		return "board/board_main";
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Restful API 전체 로드
	@RequestMapping(value = "/board2", method = RequestMethod.GET)
	public Map<String, Object> listBoard() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<BoardVO> list = boardservice.listAll();
		result.put("list", list);
		result.put("result", Boolean.TRUE);
		return result;
	}

	// Restful API 게시물 하나 로드
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

	// Restful API 게시물 작성
	@RequestMapping(value = "/board2", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public Map<String, Object> addBoard(@RequestBody BoardVO boardvo, HttpSession session) {
		String id = (String) session.getAttribute("userID");
		boardvo.setUserID(id);
		Map<String, Object> result = new HashMap<String, Object>();
		if (boardvo != null)
			boardservice.insertBoard(boardvo);

		result.put("result", Boolean.TRUE);
		return result;
	}

	// Restful API 게시물 수정
	@RequestMapping(value = "/board2", method = RequestMethod.PUT, headers = { "Content-type=application/json" })
	public Map<String, Object> updateBoard(@RequestBody BoardVO boardvo) {
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(boardvo.getBbsTitle());
		if (boardvo != null)
			boardservice.updateBoard(boardvo);

		result.put("result", Boolean.TRUE);
		return result;
	}

	// Restful API 게시물 삭제
	@RequestMapping(value = "/board2/{bbsID}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteBoard(@PathVariable int bbsID) {
		Map<String, Object> result = new HashMap<String, Object>();
		boardservice.deleteBoard(bbsID);
		result.put("result", Boolean.TRUE);
		return result;
	}

}
