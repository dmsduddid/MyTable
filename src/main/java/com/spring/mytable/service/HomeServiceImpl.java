package com.spring.mytable.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.mytable.domain.StoreDTO;
import com.spring.mytable.persistence.HomeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService{
	
	private final HomeMapper homemapper;

	@Override
	public List<StoreDTO> getList() {
		return homemapper.selectList();
	}

}
