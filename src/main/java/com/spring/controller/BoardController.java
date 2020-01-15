package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.BoardService;
import com.spring.service.CommentService;
import com.spring.service.UserService;
import com.spring.vo.BoardVO;
import com.spring.vo.CommentVO;

@RestController
public class BoardController {
	@Autowired
	BoardService boardservice;

	@Autowired
	CommentService commentservice;

	@Autowired
	UserService userservice;
	
	// Restful API 게시물 작성
	@RequestMapping(value = "/board", method = RequestMethod.POST, headers = { "Content-type=application/json" })
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
	@RequestMapping(value = "/board", method = RequestMethod.PUT, headers = { "Content-type=application/json" })
	public Map<String, Object> updateBoard(@RequestBody BoardVO boardvo) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (boardvo != null)
			boardservice.updateBoard(boardvo);
		result.put("result", Boolean.TRUE);
		return result;
	}

	// Restful API 게시물 삭제
	@RequestMapping(value = "/board/{bbsID}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteBoard(@PathVariable int bbsID) {
		Map<String, Object> result = new HashMap<String, Object>();
		boardservice.deleteBoard(bbsID);
		result.put("result", Boolean.TRUE);
		return result;
	}

	/*
	// Restful API 전체 로드
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public Map<String, Object> listBoard() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<BoardVO> list = boardservice.listAll();
		result.put("list", list);
		result.put("result", Boolean.TRUE);
		return result;
	}
*/
	
}
