package com.spring.mytable.service;


import java.util.List;

import com.spring.mytable.domain.UserDTO;

public interface JoinService {
	
	public int joinMember(UserDTO dto);

	public int idCheck(UserDTO dto);
	
	public int registerOwner(UserDTO dto);
	
	public int registerCustomer(UserDTO dto);

	public String getKakaoToken(String code);

	public List<UserDTO> getUserInfo(String accessToken);



}
