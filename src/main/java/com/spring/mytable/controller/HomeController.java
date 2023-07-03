package com.spring.mytable.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.mytable.domain.Criteria;
import com.spring.mytable.domain.PageMaker;
import com.spring.mytable.domain.RegisterDTO;
import com.spring.mytable.domain.StoreDTO;
import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.service.HomeService;
import com.spring.mytable.service.LoginService;
import com.spring.mytable.service.MemberService;
import com.spring.mytable.service.RegisterService;
import com.spring.mytable.service.StoreService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/mytable/*")
public class HomeController {
	
	@Autowired
	private RegisterService registerservice;
	
	@Autowired
	private StoreService storeservice;
	
	@Autowired
	private MemberService memberservice;

	

	@GetMapping("mytable")
	public void myTable(RegisterDTO rdto, StoreDTO sdto, UserDTO udto, HttpSession session ,Model model, HttpServletRequest req
			,Criteria cri) {
		
		double totalCount = storeservice.selectTotalCount();
		
		PageMaker pageMaker = new PageMaker(cri, totalCount);

		
		model.addAttribute("StoreDTO", sdto);
		
		
		//페이징 리스트 업로드 → 화면에 뿌림
		model.addAttribute("list", storeservice.selectListByPaging(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		System.out.println(session.getAttribute("user_id"));
		
		String customerId = (String)req.getSession().getAttribute("user_id");

		
		//일반 고객(C)인지 사업자 고객(O)인지 확인하기 위함
		udto.setUser_id(customerId);
		model.addAttribute("memberDivision", memberservice.selectDivision(udto));

		
		//일반 고객(C)일 경우 대기 등록 번호 추출
		rdto.setCustomer_id(customerId);
		RegisterDTO registerList = registerservice.selectRegisterList(rdto);
		int waiting_num = registerservice.checkWaitingNum(rdto);
		model.addAttribute("RegisterDTO", registerList);
		model.addAttribute("waiting_num", waiting_num);
		
		model.addAttribute("registerYn", registerservice.countRegisterList(rdto));
	
	}
	
}