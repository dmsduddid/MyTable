package com.spring.mytable.service;

import com.spring.mytable.domain.UserDTO;

public interface LoginService {
	
	public int doLogin(UserDTO dto);
	public int doUserCheck(UserDTO dto);
	public int ownerUpload_YN(UserDTO dto);
	public String selectUserName(UserDTO dto);
	

}
