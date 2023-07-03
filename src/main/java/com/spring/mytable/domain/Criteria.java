package com.spring.mytable.domain;

import lombok.Data;

@Data
public class Criteria {
	
	private int pageNum;
	private int amount;
	
	public Criteria() {
		this(1,9);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

}
