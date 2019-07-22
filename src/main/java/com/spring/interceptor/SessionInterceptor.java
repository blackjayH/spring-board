package com.spring.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.service.BoardService;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(SessionInterceptor.class);
	@Inject
	BoardService boardservice;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userID");
		String temp = request.getParameter("bbsID");
		int bbsID = Integer.parseInt(temp);
		String result = boardservice.checkUser(bbsID);
		if (!id.equals(result)) { // 게시물 작성자와 로그인 ID 동일시
			response.sendRedirect(request.getContextPath() + "/board/view/detail?bbsID=" + bbsID); // 로그인
			return false;
		} else
			return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}