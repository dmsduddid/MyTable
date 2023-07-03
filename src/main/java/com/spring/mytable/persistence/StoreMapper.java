package com.spring.mytable.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mytable.domain.Criteria;
import com.spring.mytable.domain.StoreDTO;
import com.spring.mytable.domain.StoreUploadDTO;

@Mapper
public interface StoreMapper {
	
	
	//스토어 등록
	public int uploadStore(StoreDTO dto);
	
	//사업자 고객이 스토어 등록하면 confirm_yn = 'N'으로 변경
	public int updateConfirm(StoreDTO dto);
	
	//사업자 고객이 스토어 등록하면 storelist에 업로드
	public int uploadStoreList(StoreDTO dto);
	
	
	public int selectBusinee_no(StoreDTO dto);
	
	
	public List<StoreDTO> selectBusinessList(StoreDTO dto);
	
	
	public int uploadFile(StoreUploadDTO udto);
	
	public List<StoreUploadDTO> selectStoreList(StoreUploadDTO udto);
	
	public List<StoreUploadDTO> selectImgASCList(StoreUploadDTO udto);
	
	public List<StoreUploadDTO> selectImgDESCList(StoreUploadDTO udto);
	
	public int selectCnt(StoreUploadDTO udto);
	
	//storelist에 메인 이미지 등록
	public int updateMainImg(StoreDTO dto);
	
	//businessInfo에 represent_yn -> Y로 값 변경
	public int updateRepresent_Y(StoreUploadDTO udto);
	
	//businessInfo에 represent_yn -> N으로 값 변경
	public int updateRepresent_N(StoreUploadDTO udto);

	//대표 이미지 등록되어있는지 유무 확인
	public int selectRepresentCnt(StoreDTO dto);
	
	
	
	public List<StoreUploadDTO> selectBusinessInfo(StoreUploadDTO udto);
	
	//페이징
	public List<StoreDTO> selectListByPaging(Criteria cri);
	
	public double selectTotalCount();
	
	public int checkBusinessNo(StoreDTO dto);
	
	public int deleteBusinessInfo(StoreUploadDTO udto);

}