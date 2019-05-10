package com.spring.website.controller;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/user/list")
	public String userList(Model model) {
		return "user/list";
	}
	// 
	@RequestMapping("/user/add")
	public String userAdd() {
		return "user/add";
	}
	// 등록
	@RequestMapping("/user/enroll")
	public String userEnroll() {
		return "user/enroll";
	}
	
	
}
