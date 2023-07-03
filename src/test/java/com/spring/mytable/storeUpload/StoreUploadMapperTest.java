package com.spring.mytable.storeUpload;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mytable.domain.StoreDTO;
import com.spring.mytable.persistence.StoreMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class StoreUploadMapperTest {
	
	@Autowired
	private StoreMapper storemapper;

	//@Test
	public void storeUpload() {
		StoreDTO dto = new StoreDTO();
		
		dto.setBusiness_no(1111111111);
		dto.setBusiness_name("가게명");
		dto.setOwner_id("12345");
		//dto.setLocation("창원시");
		
		storemapper.uploadStore(dto);
	}
	
	//@Test
	public void ownerConfirm() 
	{
		StoreDTO dto = new StoreDTO();
		
		dto.setOwner_id("12345");
		
		storemapper.updateConfirm(dto);
		
	}
	
	//@Test
	public void uploadStoreList() {
		StoreDTO dto = new StoreDTO();
		
		dto.setBusiness_no(1212121212);
		storemapper.uploadStoreList(dto);
	}
	
	//@Test
	public void selectBusinee_no() {
		StoreDTO dto = new StoreDTO();
		
		dto.setBusiness_no(1111111111);
		storemapper.selectBusinee_no(dto);
		
	}

}

