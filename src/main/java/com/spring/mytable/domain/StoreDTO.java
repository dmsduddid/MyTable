package com.spring.mytable.domain;

import lombok.Data;

@Data
public class StoreDTO {
	
	private int store_seq_no;
	private int storelist_no;
	private int business_no;
	private String business_name;
	private String owner_id;
	private String postcode;
	private String address;
	private String detailAddress;
	private String img;

}
