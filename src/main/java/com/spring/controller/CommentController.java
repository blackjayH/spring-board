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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.CommentService;
import com.spring.vo.BoardVO;
import com.spring.vo.CommentVO;

@RestController
public class CommentController {
	@Autowired
	CommentService commentservice;


	// Restful API 댓글 작성
	@RequestMapping(value = "/comment", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public Map<String, Object> addComment(@RequestBody CommentVO commentvo, HttpSession session) {
		String id = (String) session.getAttribute("userID");
		commentvo.setUserID(id);
		commentservice.insertComment(commentvo);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		return result;
	}

	// Restful API 댓글 수정
	@RequestMapping(value = "/comment", method = RequestMethod.PUT, headers = { "Content-type=application/json" })
	public Map<String, Object> rupdateComment(@RequestBody CommentVO commentvo) {
		Map<String, Object> result = new HashMap<String, Object>();
		commentservice.updateComment(commentvo);
		result.put("result", Boolean.TRUE);
		return result;
	}

	// Restful API 댓글 삭제
	@RequestMapping(value = "/comment/{commentID}", method = RequestMethod.DELETE)
	public Map<String, Object> rdeleteComment(@PathVariable int commentID) {
		Map<String, Object> result = new HashMap<String, Object>();
		commentservice.deleteComment(commentID);
		result.put("result", Boolean.TRUE);
		return result;
	}
}