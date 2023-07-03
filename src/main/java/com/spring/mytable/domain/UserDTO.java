package com.spring.mytable.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserDTO {
	
	private int mno;
	private String user_id;
	private String platform_type;
	private String name;
	private String password;
	private String email;
	private String phone;
	private Date join_date;
	private Date modify_date;
	private String division;

}
