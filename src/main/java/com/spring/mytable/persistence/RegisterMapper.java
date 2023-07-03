package com.spring.mytable.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mytable.domain.RegisterDTO;

@Mapper
public interface RegisterMapper {
	
	//대기
	public int updateWaiting(RegisterDTO dto);
	public int deleteWaiting(RegisterDTO dto);
	public int moveToHistory(RegisterDTO dto);
	public int deleteFromRegister(RegisterDTO dto);
	public int countWaitingNum(RegisterDTO dto);
	public RegisterDTO selectRegisterList(RegisterDTO dto); //고객 기준 대기 명단
	
	//입장
	public int sendEnteringMsg(RegisterDTO dto);
	
	//인원 수 카운트
	public int countEntringPeopleNum(RegisterDTO dto);
	public int countCancelPeopleNum(RegisterDTO dto);
	public int countWatingPeopleNum(RegisterDTO dto);

	//대기명단
	public List<RegisterDTO> selectList(RegisterDTO dto); // store기준
	
	//입장완료된 고객
	public List<RegisterDTO> selectHistoryList(RegisterDTO dto);
	
	public int selectBusiness_no(RegisterDTO dto);
	
	
	public int countRegisterList(RegisterDTO dto);
}
