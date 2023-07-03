package com.spring.mytable.register;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mytable.domain.RegisterDTO;
import com.spring.mytable.persistence.RegisterMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class RegisterMapperTest {
	
	@Autowired
	private RegisterMapper mapper;

	//@Test
	public void updateWaiting() {
		RegisterDTO dto = new RegisterDTO();
		
		dto.setBusiness_no(1212121212);
		dto.setBusiness_name("가게2");
		dto.setCustomer_id("1dd");
		dto.setPeople_num(7);
		
		mapper.updateWaiting(dto);
	}
	
	//@Test
	public void deleteWaiting() {
		RegisterDTO dto = new RegisterDTO();
		
		dto.setCustomer_id("1dd");
		mapper.deleteWaiting(dto);
		mapper.moveToHistory(dto);
		mapper.deleteFromRegister(dto);
	}
	
	
	//@Test
	public void countWaitingNum() {
		RegisterDTO dto = new RegisterDTO();
		
		dto.setCustomer_id("1dd");
		mapper.countWaitingNum(dto);
		
	}
	
	//@Test
	public void sendEnteringMsg() {
		RegisterDTO dto = new RegisterDTO();
		
		dto.setCustomer_id("dd");
		mapper.sendEnteringMsg(dto);
		mapper.moveToHistory(dto);
		mapper.deleteFromRegister(dto);
	}
	
	
	
	
}
