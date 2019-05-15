package com.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.UserService;
import com.spring.vo.UserVO;

@Controller
public class UserController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
	@Inject
	UserService userservice;

	// 회원 목록 보기
	@RequestMapping("/user/user_list")
	public String listAll(Model model) {
		List<UserVO> list = userservice.listAll();
		model.addAttribute("list", list);
		return "user/user_list";
	}

	// 회원 가입 하러가기
	@RequestMapping("/user/user_join")
	public void userjoin() {

	}

	// 회원가입 확인
	@RequestMapping("/user/user_add")
	public String userAdd(@ModelAttribute UserVO uservo, Model model) {
		userservice.insertUser(uservo);
		return "redirect:user_list";
	}

	// 아이디 중복 확인
	@RequestMapping("/user/user_checkId") // , method = RequestMethod.GET)
	@ResponseBody
	public String checkId(String id) {
		boolean temp = userservice.checkId(id);
		String x;
		if (temp)
			x = "no";
		else
			x = "yes";
		return x;
	}

	@RequestMapping("/user/user_check")
	public String useCheck(@ModelAttribute UserVO uservo, Model model) {
		//
		boolean result = userservice.checkId(uservo.getId());
		if (result) { // 회원가입 실패(mysql에 id가 존재하는 경우)
			model.addAttribute("message", "아이디 중복O");
			return "redirect:user_join";
		} else { // 회원가입 성공
			model.addAttribute("message", "아이디 중복X");
			return "redirect:user_list";
		}
	}

	// 유저 탈퇴
	@RequestMapping("/user/user_leave")
	public String userDelete(@ModelAttribute UserVO uservo, Model model) {
		boolean result = userservice.checkPw(uservo.getId(), uservo.getPw());
		if (result) { // 탈퇴 성공
			userservice.deleteUser(uservo);
			return "redirect:user_list";
		} else { // 탈퇴 실패
			model.addAttribute("message", "탈퇴 실패");
			return "redirect:user_detail";
		}

	}

	// 유저 정보 수정
	@RequestMapping("/user/user_update")
	public String userUpdate(@ModelAttribute UserVO uservo, Model model) {
		boolean result = userservice.checkPw(uservo.getId(), uservo.getPw());
		if (result) { // 수정 성공
			return "redirect:user_list";
		} else { // 수정 실패
			model.addAttribute("message", "수정 실패");
			return "redirect:user_detail";
		}

	}

	// 상세정보 확인
	@RequestMapping("/user/user_detail")
	public String memberView(@RequestParam String id, Model model) {
		// 회원 정보를 model에 저장
		model.addAttribute("dto", userservice.viewUser(id));
		// System.out.println("클릭한 아이디 확인 : "+userId);
		logger.info("클릭한 아이디 : " + id);
		// member_view.jsp로 포워드
		return "user/user_detail";
	}

	/*
	 * // 02_02 회원 등록 처리 후 ==> 회원목록으로 리다이렉트 // @ModelAttribute에 폼에서 입력한 데이터가 저장된다.
	 * 
	 * @RequestMapping("member/insert.do") // * 폼에서 입력한 데이터를 받아오는 법 3가지 //public
	 * String memberInsert(HttpServlet request){ //public String memberInsert(String
	 * userId, String userPw, String userName, String userEmail){ public String
	 * memberInsert(@ModelAttribute UserVO vo){ // 테이블에 레코드 입력
	 * memberService.insertMember(vo); // * (/)의 유무에 차이 // /member/list.do : 루트
	 * 디렉토리를 기준 // member/list.do : 현재 디렉토리를 기준 // member_list.jsp로 리다이렉트 return
	 * "redirect:/member/list.do"; }
	 * 
	 * // 03 회원 상세정보 조회
	 * 
	 * @RequestMapping("member/view.do") public String memberView(@RequestParam
	 * String userId, Model model){ // 회원 정보를 model에 저장 model.addAttribute("dto",
	 * memberService.viewMember(userId));
	 * //System.out.println("클릭한 아이디 확인 : "+userId);
	 * logger.info("클릭한 아이디 : "+userId); // member_view.jsp로 포워드 return
	 * "member/member_view"; } // 04. 회원 정보 수정 처리
	 * 
	 * @RequestMapping("member/update.do") public String
	 * memberUpdate(@ModelAttribute MemberVO vo, Model model){ // 비밀번호 체크 boolean
	 * result = memberService.checkPw(vo.getUserId(), vo.getUserPw()); if(result){
	 * // 비밀번호가 일치하면 수정 처리후, 전체 회원 목록으로 리다이렉트 memberService.updateMember(vo); return
	 * "redirect:/member/list.do"; } else { // 비밀번호가 일치하지 않는다면, div에 불일치 문구 출력,
	 * viwe.jsp로 포워드 // 가입일자, 수정일자 저장 MemberVO vo2 =
	 * memberService.viewMember(vo.getUserId());
	 * vo.setUserRegdate(vo2.getUserRegdate());
	 * vo.setUserUpdatedate(vo2.getUserUpdatedate()); model.addAttribute("dto", vo);
	 * model.addAttribute("message", "비밀번호 불일치"); return "member/member_view"; }
	 * 
	 * } // 05. 회원정보 삭제 처리 // @RequestMapping : url mapping // @RequestParam : get
	 * or post방식으로 전달된 변수값
	 * 
	 * @RequestMapping("member/delete.do") public String memberDelete(@RequestParam
	 * String userId, @RequestParam String userPw, Model model){ // 비밀번호 체크 boolean
	 * result = memberService.checkPw(userId, userPw); if(result){ // 비밀번호가 맞다면 삭제
	 * 처리후, 전체 회원 목록으로 리다이렉트 memberService.deleteMember(userId); return
	 * "redirect:/member/list.do"; } else { // 비밀번호가 일치하지 않는다면, div에 불일치 문구 출력,
	 * viwe.jsp로 포워드 model.addAttribute("message", "비밀번호 불일치");
	 * model.addAttribute("dto", memberService.viewMember(userId)); return
	 * "member/member_view"; } }*
	 */

}
