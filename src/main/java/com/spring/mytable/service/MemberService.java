package com.spring.mytable.service;

import java.util.List;

import com.spring.mytable.domain.UserDTO;

public interface MemberService {
	
	public int modifyMemberInfo(UserDTO dto);
	
	public int deleteMemberAccount(UserDTO dto);
	
	public List<UserDTO> selectmemberList(UserDTO dto);
	
	public String selectDivision(UserDTO dto);

}
