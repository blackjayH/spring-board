package com.spring.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonErrorController {
	@RequestMapping(value = "/error/error{error_code}")
	public ModelAndView error(HttpServletRequest request, @PathVariable String error_code, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/errorPage");
		String msg = (String) request.getAttribute("javax.servlet.error.message");

		Map<String, Object> map = new HashMap<>();
		map.put("STATUS_CODE", request.getAttribute("javax.servlet.error.status_code"));
		map.put("REQUEST_URI", request.getAttribute("javax.servlet.error.request_uri"));
		map.put("EXCEPTION_TYPE", request.getAttribute("javax.servlet.error.exception_type"));
		map.put("EXCEPTION", request.getAttribute("javax.servlet.error.exception"));
		map.put("SERVLET_NAME", request.getAttribute("javax.servlet.error.servlet_name"));

		try {
			int status_code = Integer.parseInt(error_code);
			switch (status_code) {
			case 400:
				msg = "잘못된 요청입니다.";
				break;
			case 403:
				msg = "접근이 금지되었습니다.";
				break;
			case 404:
				msg = "페이지를 찾을 수 없습니다.";
				break;
			case 405:
				msg = "요청된 메소드가 허용되지 않습니다.";
				break;
			case 500:
				msg = "서버에 오류가 발생하였습니다.";
				break;
			case 503:
				msg = "서비스를 사용할 수 없습니다.";
				break;
			default:
				msg = "알 수 없는 오류가 발생하였습니다.";
				break;
			}
		} catch (Exception e) {
			msg = "기타 오류가 발생하였습니다.";
		} finally {
			map.put("MESSAGE", msg);
		}

		if (map.isEmpty() == false) {
			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			Entry<String, Object> entry = null;
			while (iterator.hasNext()) {
				entry = iterator.next();
			}
		}

		mv.addObject("error", map);
		return mv;
	}
}
