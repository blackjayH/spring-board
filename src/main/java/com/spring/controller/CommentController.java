package com.spring.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.service.CommentService;
import com.spring.vo.CommentVO;

@Controller
public class CommentController {
	@Inject
	CommentService commentservice;

	// 게시판 댓글 작성 액션
	@RequestMapping("/comment/action/add")
	public String addComment(@ModelAttribute CommentVO commentvo, HttpSession session) {
		String id = (String) session.getAttribute("userID");
		commentvo.setUserID(id);
		commentservice.insertComment(commentvo);
		return "redirect:/board/view/detail?bbsID=" + commentvo.getBbsID();
	}

	// 게시판 댓글 삭제 액션
	@RequestMapping("/comment/action/delete")
	public String deleteComment(@RequestParam int bbsID, @RequestParam int commentID, HttpSession session) {
		commentservice.deleteComment(commentID);
		return "redirect:/board/view/detail?bbsID=" + bbsID;
	}

	// 게시판 댓글 수정 액션(UI 미결정)
	@RequestMapping("/comment/action/update")
	public String updateComment(@RequestParam int bbsID, @ModelAttribute CommentVO commentvo, HttpSession session) {
		commentservice.updateComment(commentvo);
		return "redirect:/board/view/detail?bbsID=" + bbsID;
	}

}