package com.spring.mytable.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/mytable/*")
public class MemberController {
	
	@Autowired
	private MemberService service;

	@GetMapping("memberEdit")
	public void modifyMemberInfo(UserDTO dto, Model model, HttpSession session) {
		
		String user_id = (String) session.getAttribute("user_id");
		dto.setUser_id(user_id);
		model.addAttribute("memberList", service.selectmemberList(dto));
		

	}
	
	@PostMapping("modify")
	@ResponseBody
	public void modifyMemberInfo(UserDTO dto, Model model, @RequestParam("user_id") String user_id,
			@RequestParam("password") String password, @RequestParam("phone") String phone) {
		

		System.out.println(user_id);
		System.out.println(password);
		System.out.println(phone);
		
		dto.setUser_id(user_id);
		dto.setPassword(password);

		
		service.modifyMemberInfo(dto);
		

	}
	
	
	@GetMapping("deleteMem")
	public void deleteMemberAccount(UserDTO dto, Model model) {
		
		model.addAttribute("UserDTO", dto);
		service.deleteMemberAccount(dto);
	}
	
	
	@GetMapping("myPage")
	public void myPage() {
		
	}
	
}
