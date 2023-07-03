package com.spring.mytable.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mytable.domain.UserDTO;

@Mapper
public interface MemberMapper {
	
	public int changeMemberInfo(UserDTO dto);
	
	public int deleteMemberInfo(UserDTO dto);
	
	public List<UserDTO> selectmemberList(UserDTO dto);
	
	public String selectDivision(UserDTO dto);

}
