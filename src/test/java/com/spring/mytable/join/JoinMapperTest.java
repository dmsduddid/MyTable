package com.spring.mytable.join;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.persistence.JoinMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class JoinMapperTest {
	
	@Autowired
	private JoinMapper mapper;

	
		//기본회원가입
		//@Test
		public void insertBasicMember() {
			UserDTO dto = new UserDTO();
			
			dto.setUser_id("22dfdf");
			dto.setName("22dfd");
			dto.setPassword("22");
			dto.setEmail("22");
			dto.setPhone("000");
			dto.setDivision("C");
			
			log.info("insert : "  + mapper.insertMember(dto));

		}
		
		
		//아이디 중복확인
		//@Test
		public void idCheck() {
			UserDTO dto = new UserDTO();
			
			dto.setUser_id("333d");
			log.info(mapper.idCheck(dto));
			
			if(mapper.idCheck(dto) == 1) {
				log.info("사용 불가 합니다.");
			}else {
				log.info("사용 가능한 id 입니다.");
			}
		
		}
		
		//owner테이블에 값 넣기
		//@Test
		public void insertOwner() {
			UserDTO dto = new UserDTO();
			
			log.info(mapper.insertOwner(dto));
		
		}
		
		//customer테이블에 값 넣기
		//@Test
		public void insertCustomer() {
			UserDTO dto = new UserDTO();
			
			log.info(mapper.insertCustomer(dto));
		
		}
		
		@Test
		public void insertOwnerMember() {
			UserDTO dto = new UserDTO();
			
			dto.setUser_id("dfddfdf");
			dto.setPassword("ddd");
			dto.setName("dfsdfsd");
			dto.setEmail("ddd");
			dto.setPhone("00000");
			
			mapper.insertOwner(dto);
		}

}
