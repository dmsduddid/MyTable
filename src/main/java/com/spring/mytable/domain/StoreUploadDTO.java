package com.spring.mytable.domain;

import lombok.Data;

@Data
public class StoreUploadDTO {
	

	private int business_no;
	private String business_name;
	
	//이미지 등록
	private int business_info_no;
	private String business_info;
	private String business_info_img;
	private int represent_yn;
	//private String img_path;
	
	private int cnt;


}
