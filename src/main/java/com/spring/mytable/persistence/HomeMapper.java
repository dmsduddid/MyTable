package com.spring.mytable.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mytable.domain.StoreDTO;

@Mapper
public interface HomeMapper {

	public List<StoreDTO> selectList();

}
