package com.spring.mytable.service;

import org.springframework.stereotype.Service;

import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.persistence.LoginMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final LoginMapper user;
	
	@Override
	public int doLogin(UserDTO dto) {
		
		return user.checkLogin(dto);
	}

	@Override
	public int doUserCheck(UserDTO dto) {

		return user.checkDivision(dto);
	}

	@Override
	public int ownerUpload_YN(UserDTO dto) {

		return user.ownerUpload_YN(dto);
	}

	@Override
	public String selectUserName(UserDTO dto) {
		
		return user.selectUserName(dto);
	}

}
