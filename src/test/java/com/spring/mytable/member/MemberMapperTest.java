package com.spring.mytable.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.persistence.MemberMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class MemberMapperTest {

	@Autowired
	private MemberMapper mapper;

	//@Test
	public void changeMemberInfo() {
		UserDTO dto = new UserDTO();
		
		dto.setPassword("1111");
		dto.setEmail("1111@naver.com");
		dto.setUser_id("1122dd");
		
		mapper.changeMemberInfo(dto);
	}
	
	//@Test
	public void deleteMemberInfo() {
		UserDTO dto = new UserDTO();
		
		dto.setUser_id("dd");
		
		mapper.deleteMemberInfo(dto);
	}
	

}
