package com.spring.mytable.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import com.spring.mytable.domain.RegisterDTO;
import com.spring.mytable.domain.StoreDTO;
import com.spring.mytable.domain.StoreUploadDTO;
import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.service.HomeService;
import com.spring.mytable.service.MemberService;
import com.spring.mytable.service.RegisterService;
import com.spring.mytable.service.StoreService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/mytable/*")
public class StoreController {
	
	@Autowired
	private StoreService storeservice;
	
	@Autowired
	private RegisterService registerservice;
	
	@Autowired
	private HomeService homeservice;
	
	@Autowired
	private MemberService memberservice;
	
	
	
	@GetMapping("/storeUpload")
	public void storeUpload() {
	}
	
	@GetMapping("/storeUpload_success")
	public void storeUpload_success() {

	}

	
	@PostMapping("/storeUpload")
	public int searchBusiness_no(StoreDTO dto, Model model) {
		
		model.addAttribute("StoreDTO", dto);
		
		int cnt = storeservice.selectBusinee_no(dto);
		dto.setBusiness_no(cnt);
		
		
		return cnt;
	
	}
	
	@GetMapping("/storePage")
	public void storePage(StoreDTO sdto, StoreUploadDTO sudto, UserDTO udto, Model model, HttpSession session,
							@RequestParam("business_no") int business_no) { //@RequestParam("business_name") String business_name, @RequestParam("business_no") int business_no
		
		log.info("/storePage -- " + sdto);
		
		//일반 고객(C)인지 사업자 고객(O)인지 확인하기 위함
		
		String customerId = (String) session.getAttribute("user_id");
		
		udto.setUser_id(customerId);
		model.addAttribute("memberDivision", memberservice.selectDivision(udto));
		
		model.addAttribute("StoreDTO", sdto);
		model.addAttribute("list", homeservice.getList());
		
		model.addAttribute("business_name", sdto.getBusiness_name());
		
		sudto.setBusiness_no(business_no);
		model.addAttribute("businessInfoList", storeservice.selectBusinessInfo(sudto));
		
	}
	
	@GetMapping("/storeMypage")
	public void storeMypage(RegisterDTO rdto, StoreDTO sdto, StoreUploadDTO udto, Model model,HttpSession session) {
				
		//STORE 관리 리스트
		String owner_id = (String) session.getAttribute("user_id");
		sdto.setOwner_id(owner_id);
		
		List<StoreDTO> BusinessList = storeservice.selectBusinessList(sdto);
		int business_no = BusinessList.get(0).getBusiness_no();
		udto.setBusiness_no(business_no);
		
		int cnt = storeservice.selectCnt(udto);
		model.addAttribute("storeListCnt", storeservice.selectCnt(udto));
		
		udto.setCnt(cnt);
		
		model.addAttribute("storeList", storeservice.selectStoreList(udto));
		
		
		 if(cnt > 3) { 
			 model.addAttribute("storeImgASCList", storeservice.selectImgASCList(udto)); 
			 model.addAttribute("storeImgDESCList",storeservice.selectImgDESCList(udto)); 
		 }else {
		  model.addAttribute("storeImgASCList", storeservice.selectImgASCList(udto)); }
		 
		
		//model.addAttribute("storeImgList", storeservice.selectImgList(udto));
		
		
		//예약현황 리스트
		rdto.setBusiness_no(business_no);
		log.info(business_no);
		
		
		model.addAttribute("RegisterDTO", rdto);
		model.addAttribute("list", registerservice.getList(rdto));
		
		model.addAttribute("register_seq_no", rdto.getRegister_seq_no());
		model.addAttribute("customer_id", rdto.getCustomer_id());
		model.addAttribute("people_num", rdto.getPeople_num());
		model.addAttribute("register_sysdate", rdto.getRegister_sysdate());

	}

	@PostMapping("/uploadStoreMypage")
	public String uploadStoreMypage(StoreDTO dto, StoreUploadDTO udto, Model model, HttpSession session, 
									@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		
		
		String owner_id = (String) session.getAttribute("user_id");
		dto.setOwner_id(owner_id);
		
		List<StoreDTO> BusinessList = storeservice.selectBusinessList(dto);
		
		
		if(!file.isEmpty()) {
			
			String filePath = "C:\\sts-4\\workspace\\MyTable\\src\\main\\resources\\static\\images\\storeUpload\\" + file.getOriginalFilename();
			udto.setBusiness_info_img(file.getOriginalFilename());
			file.transferTo(new File(filePath));
		}
		

		System.out.println(BusinessList);
		
		udto.setBusiness_no(BusinessList.get(0).getBusiness_no());
		udto.setBusiness_name(BusinessList.get(0).getBusiness_name());
		
		storeservice.uploadFile(udto);
		
		return "redirect:storeMypage";
	
	}
	
	@PostMapping("/changeMainImg")
	@ResponseStatus(value=HttpStatus.OK)
	public void changeMainImg(StoreDTO dto, StoreUploadDTO udto, HttpServletRequest req, HttpSession session) {
		
		String business_info_no = req.getParameter("business_info_no");
		udto.setBusiness_info_img(business_info_no);		
		
		String owner_id = (String) session.getAttribute("user_id");
		dto.setOwner_id(owner_id);
		
		List<StoreDTO> BusinessList = storeservice.selectBusinessList(dto);
		int business_no = BusinessList.get(0).getBusiness_no();
		dto.setBusiness_no(business_no);
		udto.setBusiness_no(business_no);

		
		int cnt = storeservice.selectRepresentCnt(dto);
		
		if(cnt >= 1) {
			storeservice.updateRepresent_N(udto);
		}
		
		storeservice.updateRepresent_Y(udto);
		storeservice.updateMainImg(dto);
		
		
	}
	
	@PostMapping("/checkBusinessNo")
	@ResponseBody
	public int checkBusinessNo(StoreDTO dto, HttpServletRequest req) {
		
		int businessNo = Integer.valueOf(req.getParameter("business_no"));
		dto.setBusiness_no(businessNo);
		
		int cnt = storeservice.checkBusinessNo(dto);
		
		return cnt;
	}
	
	@PostMapping("/uploadBusiness")
	public String uploadBusinessInfo(StoreDTO dto, HttpServletRequest req, HttpSession session) {
		
		String owner_id = (String) session.getAttribute("user_id");
		
		System.out.println("owner_id = " + owner_id);
		dto.setOwner_id(owner_id);
		storeservice.registerStore(dto);
		storeservice.displayStoreList(dto);
		
		return "mytable/storeUpload_success";
	}
	
	@PostMapping("/deleteInfo")
	@ResponseStatus(value=HttpStatus.OK)
	public void deleteInfo(StoreDTO dto, StoreUploadDTO udto, HttpServletRequest req, HttpSession session) {
		
		String business_info_no = req.getParameter("business_info_no");
		udto.setBusiness_info_img(business_info_no);		
		
		storeservice.deleteBusinessInfo(udto);
	
	}
	
	/*
	 * @PostMapping("selectRegisterList")
	 * 
	 * @ResponseBody public List<RegisterDTO> selectRegisterList(RegisterDTO rdto,
	 * StoreDTO sdto, Model model, HttpSession session) {
	 * 
	 * //STORE 관리 리스트 String owner_id = (String) session.getAttribute("user_id");
	 * sdto.setOwner_id(owner_id);
	 * 
	 * List<StoreDTO> BusinessList = storeservice.selectBusinessList(sdto); int
	 * business_no = BusinessList.get(0).getBusiness_no();
	 * 
	 * rdto.setBusiness_no(business_no);
	 * 
	 * //model.addAttribute("list", registerservice.getList(rdto));
	 * 
	 * List<RegisterDTO> list = registerservice.getList(rdto);
	 * 
	 * return list;
	 * 
	 * }
	 */
	
}
