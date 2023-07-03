package com.spring.mytable.controller;

import java.util.HashMap;
import java.util.List;

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
import com.spring.mytable.service.JoinService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/mytable/*")
public class JoinController {

	@Autowired
	private JoinService joinservice;
	
	
	@GetMapping("/kakao")
	public String kakao(@RequestParam String code, UserDTO dto, HttpSession session, RedirectAttributes
			rttr) {
		
		String token = joinservice.getKakaoToken(code);
		List<UserDTO> userinfo = joinservice.getUserInfo(token);		

		userinfo.get(0).setPlatform_type("kakao");

		if(userinfo.get(1) == null) {
			
			rttr.addFlashAttribute("UserDTO", userinfo.get(0));
			session.setAttribute("platform_type", "kakao");
			return "redirect:/mytable/join";
		}
		
		session.setAttribute("name", userinfo.get(1).getName());
		session.setAttribute("user_id", userinfo.get(1).getUser_id());
				
		return "redirect:/mytable/mytable";
	}
	
	

	@GetMapping("/join")
	public void join() {		
	}
	
	
	
	@PostMapping("/join")
	@ResponseBody
	public int idCheck(UserDTO dto, Model model) {
		int cnt = joinservice.idCheck(dto);
		model.addAttribute("UserDTO", dto);
		return cnt;
	}
	
	

	@PostMapping("/successjoin")
	public void joinCustomerMember(UserDTO dto, Model model, HttpSession session, HttpServletRequest req) {
		
		model.addAttribute("UserDTO", dto);
		String division = (String) req.getParameter("division");
		log.info("join_success dto --- "  + dto);
		
		log.info(division);
		
		if(division.equals("C")) {
			if(session.getAttribute("platform_type") != null) {
				dto.setPlatform_type("kakako account");
			}else {
				dto.setPlatform_type("basic account");
			}
			joinservice.joinMember(dto);
			joinservice.registerCustomer(dto);
			
		}else if(division.equals("O")) {
			if(session.getAttribute("platform_type") != null) {
				dto.setPlatform_type("kakako account");
			}else {
				dto.setPlatform_type("basic account");
			}
			joinservice.joinMember(dto);
			joinservice.registerOwner(dto);
		}

	}
	
	
	@PostMapping("/ownerjoin") 
	public void joinOwnerMember(UserDTO dto, Model model) {
	  
		model.addAttribute("UserDTO", dto);
		
		log.info("join_success dto --- "  + dto);
		
		joinservice.registerOwner(dto);
	}
	
		 

}
