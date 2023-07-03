package com.spring.mytable.domain.api;

import lombok.Data;

@Data
public class MsgApiDTO {
	
	private String customer_id; //고객 아이디
	private String username;
	private int phone;
	private String storename;
	
/*	private String url;
	private String timestamp;
	private String method;
	private String accessKey;
	private String secretKey;
	private String serviceId;*/
	

}
