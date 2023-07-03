package com.spring.mytable.domain;

import java.util.Date;

import lombok.Data;

@Data
public class RegisterDTO {
	
	private int register_seq_no;
	private int business_no;
	private String business_name;
	private String user_id;
	private String customer_id;
	private int people_num;
	private Date register_sysdate;
	private String use_yn;
	private Date entering_time;

}
