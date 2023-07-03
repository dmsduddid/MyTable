package com.spring.mytable.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.mytable.domain.RegisterDTO;
import com.spring.mytable.persistence.RegisterMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService{
	
	private final RegisterMapper mapper;

	@Override
	public int registerWaiting(RegisterDTO dto) {

		return mapper.updateWaiting(dto);
	}

	@Override
	public int cancelWaiting(RegisterDTO dto) {

		return mapper.deleteWaiting(dto);
	}

	@Override
	public int moveToHistory(RegisterDTO dto) {

		return mapper.moveToHistory(dto);
	}

	@Override
	public int deleteFromRegister(RegisterDTO dto) {

		return mapper.deleteFromRegister(dto);
	}

	@Override
	public int checkWaitingNum(RegisterDTO dto) {

		return mapper.countWaitingNum(dto);
	}

	@Override
	public int sendEnteringMsg(RegisterDTO dto) {
		
		return mapper.sendEnteringMsg(dto);
	}

	@Override
	public int countEntringPeopleNum(RegisterDTO dto) {
		
		return mapper.countEntringPeopleNum(dto);
	}

	@Override
	public int countCancelPeopleNum(RegisterDTO dto) {
		
		return mapper.countCancelPeopleNum(dto);
	}

	@Override
	public int countWatingPeopleNum(RegisterDTO dto) {
		
		return mapper.countWatingPeopleNum(dto);
	}

	@Override
	public RegisterDTO selectRegisterList(RegisterDTO dto) {

		return mapper.selectRegisterList(dto);
	}

	@Override
	public List<RegisterDTO> getList(RegisterDTO dto) {

		return mapper.selectList(dto);
	}

	@Override
	public List<RegisterDTO> selectHistoryList(RegisterDTO dto) {
		return mapper.selectHistoryList(dto);
	}

	@Override
	public int selectBusiness_no(RegisterDTO dto) {

		return mapper.selectBusiness_no(dto);
	}

	@Override
	public int countRegisterList(RegisterDTO dto) {

		return mapper.countRegisterList(dto);
	}




	


}
