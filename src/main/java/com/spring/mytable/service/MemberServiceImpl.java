package com.spring.mytable.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.persistence.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper mapper;

	@Override
	public int modifyMemberInfo(UserDTO dto) {
		return mapper.changeMemberInfo(dto);
	}

	@Override
	public int deleteMemberAccount(UserDTO dto) {
		return mapper.deleteMemberInfo(dto);
	}

	@Override
	public List<UserDTO> selectmemberList(UserDTO dto) {
		return mapper.selectmemberList(dto);
	}

	@Override
	public String selectDivision(UserDTO dto) {
		return mapper.selectDivision(dto);
	}

}
