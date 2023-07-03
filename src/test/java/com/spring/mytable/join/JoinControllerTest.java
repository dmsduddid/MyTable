package com.spring.mytable.join;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class JoinControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	//@Test
	public void idCheck() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.get("/join/idCheck")
															  .param("user_id", "12"))
									   .andReturn()
									   .getModelAndView()
									   .getModelMap().get("user_id")
				);
	}
	
	//@Test
	public void joinBasicMember() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.post("/join/")
															  .param("user_id", "dd")
															  .param("password", "56")
															  .param("name", "56")
															  .param("email", "56")
															  .param("phone", "56"))
									   .andReturn()
									   .getModelAndView()
									   .getModelMap().get("UserDTO")
				);
	}
	
	@Test
	public void joinOwnerMember() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.post("/mytable/ownerjoin")
															  .param("user_id", "얍얍22")
															  .param("password", "56df")
															  .param("name", "56df")
															  .param("email", "56dfd")
															  .param("phone", "01000000")
															  .param("division", "O"))
									   .andReturn()
									   .getModelAndView()
									   .getModelMap().get("UserDTO")
				);
	}
	
	

}
