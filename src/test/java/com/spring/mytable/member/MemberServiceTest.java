package com.spring.mytable.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class MemberServiceTest {
	
	@Autowired
	private MemberService service;

	//@Test
	public void modifyMemberInfo() {
		UserDTO dto = new UserDTO();
	
		dto.setUser_id("12345");
		dto.setPassword("11");
		dto.setEmail("df");
		
		service.modifyMemberInfo(dto);
	}
	
	//@Test
	public void deleteMemberAccount() {
		UserDTO dto = new UserDTO();
		
		dto.setUser_id("12345");
		service.deleteMemberAccount(dto);
	}
	
	
}
