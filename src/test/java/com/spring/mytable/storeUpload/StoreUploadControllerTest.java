package com.spring.mytable.storeUpload;

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
public class StoreUploadControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void registerStore() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.get("/mytable/storeupload_2/")
															  .param("business_no", "1111111114")
															  .param("business_name", "가게4")
															  .param("owner_id", "얍얍")
															  .param("postcode", "12345")
															  .param("address", "창원시" )
															  .param("detailAddress", "창원대")
															  )
									   .andReturn()
									   .getModelAndView() 
									   .getModelMap()
									   .get("StoreDTO")
				);
		
	}
	
	//@Test
	public void displayStoreList() throws Exception {
		log.info(
				"result - " +
						mockMvc.perform(MockMvcRequestBuilders.get("/storelist/")
															  .param("business_no", "1234567890")
															  .param("business_name", "가게1")
															  .param("owner_id", "dfddfdf")
															  .param("location", "창원시")
															  )
									   .andReturn()
									   .getModelAndView()
									   .getModelMap()
									   .get("StoreDTO")
				);
		
	}

}
