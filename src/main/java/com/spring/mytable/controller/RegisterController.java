package com.spring.mytable.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mytable.domain.RegisterDTO;
import com.spring.mytable.domain.api.MsgApiDTO;
import com.spring.mytable.service.RegisterService;
import com.spring.mytable.service.api.MsgAPIService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/mytable/*")
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	@Autowired
	private MsgAPIService apiservice;
	
	
	@GetMapping("cancelWait")
	public String cancelWaiting(RegisterDTO dto, Model model, HttpSession session) {
		
		dto.setCustomer_id((String) session.getAttribute("user_id"));
		System.out.println(session.getAttribute("user_id"));
		model.addAttribute("RegisterDTO", dto);
		
		service.cancelWaiting(dto);
		service.moveToHistory(dto);
		service.deleteFromRegister(dto);
		
		return "redirect:/mytable/mytable";

	}
	
	@GetMapping("sendMsg")
	public void sendMsg(RegisterDTO dto, Model model) {
		
		model.addAttribute("RegisterDTO", dto);
		
		service.sendEnteringMsg(dto);
		service.moveToHistory(dto);
		service.deleteFromRegister(dto);

	}
	
	@PostMapping("register")
	@ResponseBody
	public boolean registerWaiting(RegisterDTO dto, Model model, @RequestParam("business_no") int business_no,
			@RequestParam("customer_id") String customer_id, @RequestParam("people_num") int people_num, RedirectAttributes
			rttr) {
		
		model.addAttribute("RegisterDTO", dto);
		
		dto.setBusiness_no(business_no);
		System.out.println("business_no = " + business_no);
		dto.setCustomer_id(customer_id);
		dto.setPeople_num(people_num);
		
		int cnt = service.countRegisterList(dto);
		
		System.out.println("==========================");
		System.out.println("cnt = " + cnt);
		
		//String message="";
		
		boolean registerResult= true;
		
		if(cnt == 0) {			
			service.registerWaiting(dto);
			return registerResult;
		}else {
			//message = "이미 등록한 가게가 존재합니다. 취소하고 다시 예약하세요.";
			registerResult= false;
			return registerResult;
		}	
		
	}
	
	@PostMapping("sendMsg")
	@ResponseStatus(value= HttpStatus.OK)
	public void sendKakaoMsg(MsgApiDTO apidto, RegisterDTO dto, Model model, @RequestParam("customer_id") String customer_id) {
		System.out.println("=========================");
		System.out.println("id = " + customer_id);
		System.out.println("=========================");
		apidto.setCustomer_id(customer_id);

		MsgApiDTO MDTO = apiservice.selectRegisterList(apidto);
		service.sendEnteringMsg(dto);
		service.moveToHistory(dto);
		service.deleteFromRegister(dto);
		apiservice.sendTalk(MDTO);

	}

	

}
