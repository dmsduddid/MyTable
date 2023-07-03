package com.spring.mytable.storeUpload;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mytable.domain.StoreDTO;
import com.spring.mytable.service.StoreService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class StoreUploadServiceTest {
	
	@Autowired
	private StoreService storeservice;

	//@Test
	public void registerStore() {
		StoreDTO dto = new StoreDTO();
		
		dto.setBusiness_no(1212121212);
		dto.setBusiness_name("가게2");
		dto.setOwner_id("12345dd");
		//dto.setLocation("창원시");
		
		storeservice.registerStore(dto);
	}
	
	//@Test
	public void requestOwnerConfirm() {
		StoreDTO dto = new StoreDTO();
		
		dto.setOwner_id("12345dd");
		
		storeservice.requestOwnerConfirm(dto);
	}
	
	@Test
	public void selectBusinee_no() {
		StoreDTO dto = new StoreDTO();
		
		dto.setBusiness_no(1111111111);
		storeservice.selectBusinee_no(dto);
		
	} 
	
	
}
