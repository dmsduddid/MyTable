package com.spring.mytable.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.service.LoginService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/mytable/*")
public class LoginController {
	
	@Autowired
	private LoginService loginservice;
	
	@GetMapping("/1")
	public String mainPage() {
		return "mytable_1";
		
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("login")
	public String doLogin(UserDTO dto, Model model, HttpSession session, HttpServletRequest request, RedirectAttributes
			rttr) {
		
		String id = request.getParameter("user_id");
		
		dto.setUser_id(id);
		
		String name = loginservice.selectUserName(dto);
		
		int cnt = loginservice.doLogin(dto);
		int userCheck = loginservice.doUserCheck(dto);
		int ownerCheck = loginservice.ownerUpload_YN(dto);
		//사업자 회원이 스토어를 업로드 했는지 안 했는지 확인하는 부분
		//loginservice.doUserCheck : 일반 고객이면 1, 사업자 고객이면 0
		//loginservice.doLogin : 로그인 성공이면 1, 로그인 실패면 0

		log.info("id = " +id);

		String failMsg="아이디 또는 비밀번호를 잘못 입력했습니다.\\n다시 확인해주세요.";
		
		if(cnt == 1) {
			if(userCheck == 1) {
				
				request.getSession(); 
				session.setAttribute("user_id", id);
				session.setAttribute("name", name);
				return "redirect:mytable";
				
			}else if(userCheck == 0) {
				
				if(ownerCheck == 1) {
					
					request.getSession(); 
					session.setAttribute("user_id", id);
					session.setAttribute("name", name);
					return "redirect:storeMypage";
					
				}else if(ownerCheck == 0) {
					
					request.getSession(); 
					session.setAttribute("user_id", id);
					session.setAttribute("name", name);
					return "redirect:storeUpload";
				}	
			}
		}
			rttr.addFlashAttribute("failMsg", failMsg);
			return "redirect:login";
		
	}


}
	
