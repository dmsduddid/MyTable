package com.spring.mytable.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mytable.domain.UserDTO;

@Mapper
public interface LoginMapper {
	
	
	public int checkLogin(UserDTO dto);
	public int checkDivision(UserDTO dto);
	public int ownerUpload_YN(UserDTO dto);
	public String selectUserName(UserDTO dto);

	
	

}
