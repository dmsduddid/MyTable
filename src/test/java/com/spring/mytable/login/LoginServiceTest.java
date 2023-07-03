package com.spring.mytable.login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.service.LoginService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class LoginServiceTest {
	
	@Autowired
	private LoginService loginservice;

	//@Test
	public void doLogin() {
		UserDTO dto = new UserDTO();
		dto.setUser_id("11");
		dto.setPassword("d");
		
		log.info(loginservice.doLogin(dto));
		
		int cnt = loginservice.doLogin(dto);
		
		if(cnt == 1) {
			log.info("로그인 되었습니다.");
		}else {
			log.info("아이디와 비밀번호가 일치하지 않습니다.");
		}	
	}
}
