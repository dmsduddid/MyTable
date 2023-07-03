package com.spring.mytable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mytable.domain.Criteria;
import com.spring.mytable.domain.StoreDTO;
import com.spring.mytable.domain.StoreUploadDTO;
import com.spring.mytable.persistence.StoreMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

	private final StoreMapper storemapper;

	@Override
	public int registerStore(StoreDTO dto) {
		return storemapper.uploadStore(dto);
	}

	@Override
	public int requestOwnerConfirm(StoreDTO dto) {
		return storemapper.updateConfirm(dto);
	}

	@Override
	public int displayStoreList(StoreDTO dto) {
		return storemapper.uploadStoreList(dto);
	}

	@Override
	public int selectBusinee_no(StoreDTO dto) {
		return storemapper.uploadStoreList(dto);
	}

	@Override
	public int uploadFile(StoreUploadDTO udto) {
		return storemapper.uploadFile(udto);
	}

	@Override
	public List<StoreDTO> selectBusinessList(StoreDTO dto) {
		return storemapper.selectBusinessList(dto);
	}

	@Override
	public int selectCnt(StoreUploadDTO udto) {
		return storemapper.selectCnt(udto);
	}

	@Override
	public List<StoreUploadDTO> selectImgASCList(StoreUploadDTO udto) {

		return storemapper.selectImgASCList(udto);
	}

	@Override
	public List<StoreUploadDTO> selectImgDESCList(StoreUploadDTO udto) {

		return storemapper.selectImgDESCList(udto);
	}

	@Override
	public int updateMainImg(StoreDTO dto) {

		return storemapper.updateMainImg(dto);
	}

	@Override
	public int updateRepresent_Y(StoreUploadDTO udto) {

		return storemapper.updateRepresent_Y(udto);
	}

	
	@Override 
	public int updateRepresent_N(StoreUploadDTO udto) {
	 
		return storemapper.updateRepresent_N(udto); 
	 
	}
	 

	@Override
	public int selectRepresentCnt(StoreDTO dto) {

		return storemapper.selectRepresentCnt(dto);
	}

	@Override
	public List<StoreUploadDTO> selectBusinessInfo(StoreUploadDTO udto) {
		
		return storemapper.selectBusinessInfo(udto);
	}

	@Override
	public List<StoreDTO> selectListByPaging(Criteria cri) {
		return storemapper.selectListByPaging(cri);
	}

	@Override
	public double selectTotalCount() {
		return storemapper.selectTotalCount();
	}

	@Override
	public int checkBusinessNo(StoreDTO dto) {
		return storemapper.checkBusinessNo(dto);
	}

	@Override
	public int deleteBusinessInfo(StoreUploadDTO udto) {

		return storemapper.deleteBusinessInfo(udto);
	}

	@Override
	public List<StoreUploadDTO> selectStoreList(StoreUploadDTO udto) {
		return storemapper.selectStoreList(udto);
	}

}
