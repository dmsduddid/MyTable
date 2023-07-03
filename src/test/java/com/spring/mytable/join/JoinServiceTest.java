package com.spring.mytable.join;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.service.JoinService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class JoinServiceTest {
	
	@Autowired
	private JoinService joinservice;

	//@Test
	public void joinBasicMember() {
		UserDTO dto = new UserDTO();
		
		dto.setUser_id("dfkdjfkd");
		dto.setName("이름dd");
		dto.setPassword("12dd");
		dto.setEmail("1212dd@naver.com");
		dto.setPhone("01000000000");
		dto.setDivision("C");
		
		//log.info(joinservice.joinBasicMember(dto));
		
	}

	
	//@Test
	public void idCheck() {
		UserDTO dto = new UserDTO();
		
		dto.setUser_id("11dfdfdfdfdfd");
		
		int cnt = joinservice.idCheck(dto);
		
		if(cnt == 1) {
			log.info("사용 불가한 아이디입니다.");
		}else {
			log.info("사용 가능한 아이디입니다.");
		}
		
	}
	
	

}
