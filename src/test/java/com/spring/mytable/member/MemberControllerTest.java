package com.spring.mytable.member;

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
public class MemberControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	
	//@Test
	public void modifyMemberInfo() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.get("/modify/")
															  .param("user_id", "1dd")
															  .param("password", "123")
															  .param("email", "1122dd")
															  )
									   .andReturn()
									   .getModelAndView()
									   .getModelMap()
									   .get("UserDTO")
				);
		
	}
	
	//@Test
	public void deleteMemberAccount() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.get("/modify/deleteMem")
															  .param("user_id", "ddddd")
															  )
									   .andReturn()
									   .getModelAndView()
									   .getModelMap()
									   .get("UserDTO")
				);
		
	}

}
