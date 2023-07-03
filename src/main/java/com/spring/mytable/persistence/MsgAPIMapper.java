package com.spring.mytable.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.domain.api.MsgApiDTO;

@Mapper
public interface MsgAPIMapper {
	
	public MsgApiDTO selectMsgAPIInfo(MsgApiDTO dto);

}
