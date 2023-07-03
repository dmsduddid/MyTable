package com.spring.mytable.service;

import java.util.List;

import com.spring.mytable.domain.Criteria;
import com.spring.mytable.domain.StoreDTO;
import com.spring.mytable.domain.StoreUploadDTO;

public interface StoreService {
	
	public int registerStore(StoreDTO dto);
	
	public int requestOwnerConfirm(StoreDTO dto);
	
	public int displayStoreList(StoreDTO dto);
	
	public int selectBusinee_no(StoreDTO dto);
	
	public List<StoreDTO> selectBusinessList(StoreDTO dto);
	
	public int uploadFile(StoreUploadDTO udto);
	
	public List<StoreUploadDTO> selectStoreList(StoreUploadDTO udto);
	public List<StoreUploadDTO> selectImgASCList(StoreUploadDTO udto);
	public List<StoreUploadDTO> selectImgDESCList(StoreUploadDTO udto);
	
	public int selectCnt(StoreUploadDTO udto);
	
	public int updateMainImg(StoreDTO dto);
	
	//businessInfo에 represent_yn -> Y로 값 변경
	public int updateRepresent_Y(StoreUploadDTO udto);
	
	//businessInfo에 represent_yn -> N으로 값 변경
	public int updateRepresent_N(StoreUploadDTO udto);

	public int selectRepresentCnt(StoreDTO dto);
	
	public List<StoreUploadDTO> selectBusinessInfo(StoreUploadDTO udto);
	
	//페이징
	public List<StoreDTO> selectListByPaging(Criteria cri);
	
	public double selectTotalCount();
	
	public int checkBusinessNo(StoreDTO dto);
	
	public int deleteBusinessInfo(StoreUploadDTO udto);
	

}
