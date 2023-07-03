package com.spring.mytable.register;

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
public class RegisterControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void registerWaiting() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.get("/mytable/register/")
															  .param("business_no", "1111111111")
															  .param("customer_id", "dd")
															  .param("people_num", "6")
															  )
									   .andReturn()
									   .getModelAndView()
									   .getModelMap()
									   .get("RegisterDTO")
				);
		
	}
	
	//@Test
	public void cancelWaiting() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.get("/register/cancelWait")
															  .param("customer_id", "1dd")
															  )
									   .andReturn()
									   .getModelAndView()
									   .getModelMap()
									   .get("RegisterDTO")
				);
		
	}
	
	//@Test
	public void sendMsg() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.get("/register/sendMsg")
															  .param("customer_id", "1dd")
															  )
									   .andReturn()
									   .getModelAndView()
									   .getModelMap()
									   .get("RegisterDTO")
				);
		
	}
	

}
