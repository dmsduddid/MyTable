package com.spring.mytable.service;

import java.util.List;

import com.spring.mytable.domain.RegisterDTO;
import com.spring.mytable.domain.StoreDTO;

public interface RegisterService {
	
	//대기
	public int registerWaiting(RegisterDTO dto);
	public int cancelWaiting(RegisterDTO dto);
	public int moveToHistory(RegisterDTO dto);
	public int deleteFromRegister(RegisterDTO dto);
	public int checkWaitingNum(RegisterDTO dto);
	public RegisterDTO selectRegisterList(RegisterDTO dto);

	//입장
	public int sendEnteringMsg(RegisterDTO dto);
	
	//인원 수 카운트
	public int countEntringPeopleNum(RegisterDTO dto);
	public int countCancelPeopleNum(RegisterDTO dto);
	public int countWatingPeopleNum(RegisterDTO dto);
	
	public List<RegisterDTO> getList(RegisterDTO dto);
	
	//입장완료된 고객
	public List<RegisterDTO> selectHistoryList(RegisterDTO dto);
	
	public int selectBusiness_no(RegisterDTO dto);
	
	public int countRegisterList(RegisterDTO dto);
}
