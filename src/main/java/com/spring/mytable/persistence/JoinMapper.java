package com.spring.mytable.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mytable.domain.UserDTO;

@Mapper
public interface JoinMapper {
	
	public int insertMember(UserDTO dto);
	
	public int idCheck(UserDTO dto);
	
	public int insertOwner(UserDTO dto);
	
	public int insertCustomer(UserDTO dto);
	
	public UserDTO findKakaoID(UserDTO dto);
	
	

}
