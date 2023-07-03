package com.spring.mytable.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.spring.mytable.controller.LoginController;
import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.service.LoginService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	
	//@Test
	public void testExit() {
		log.info(mockMvc);
	}
	
	//@Test
	public void test() throws Exception {
		log.info(
				"getTime2 result - " +
								mockMvc.perform(MockMvcRequestBuilders.get("/test/"))
									   .andReturn()
									   .getModelAndView()
									   .getModelMap()
									   .get("time2")
				);
	}
	
	
	//@Test
	public void doLogin() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.post("/login/")
															  .param("user_id", "11")
															  .param("password", "dd"))
									   .andReturn()
									   .getModelAndView()
									   .getModelMap()
									   .get("user_id")
				);
		
	}
	
	
}
