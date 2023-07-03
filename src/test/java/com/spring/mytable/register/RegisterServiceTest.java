package com.spring.mytable.register;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mytable.domain.RegisterDTO;
import com.spring.mytable.service.RegisterService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class RegisterServiceTest {
	
	@Autowired
	private RegisterService service;

	//@Test
	public void updateWaiting() {
		RegisterDTO dto = new RegisterDTO();
		
		dto.setBusiness_no(1212121212);
		dto.setBusiness_name("가게2");
		dto.setCustomer_id("1dd");
		dto.setPeople_num(7);
		
		service.registerWaiting(dto);
	}
	//@Test
	public void checkWaitingNum() {
		RegisterDTO dto = new RegisterDTO();
		
		dto.setCustomer_id("dd");
		service.checkWaitingNum(dto);
	}
	
	//@Test
	public void cancelWaiting() {
		RegisterDTO dto = new RegisterDTO();
		
		dto.setCustomer_id("1dd");
		
		service.cancelWaiting(dto);
		service.moveToHistory(dto);
		service.deleteFromRegister(dto);
	}
	
	//@Test
	public void sendEnteringMsg() {
		RegisterDTO dto = new RegisterDTO();
		
		dto.setCustomer_id("dd");
		service.sendEnteringMsg(dto);
		service.moveToHistory(dto);
		service.deleteFromRegister(dto);
	}
	
	
	

	
	
}

