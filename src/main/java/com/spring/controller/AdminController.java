package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.UserService;
import com.spring.vo.UserVO;

@Controller
public class AdminController {
	@Autowired
	UserService userservice;

	@RequestMapping("/board/view/admin")
	public String viewAdmin(Model model) {
		List<UserVO> list = userservice.listAll();	
		model.addAttribute("list", list);
		return "board/board_admin";
	}

}
