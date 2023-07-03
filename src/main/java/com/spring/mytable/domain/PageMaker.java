package com.spring.mytable.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PageMaker {

	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private boolean start;
	private boolean end;
	private int displayNum; //화면에 보여지는 페이지 수
	private double total;
	private int realEndPage;
	private Criteria cri;
	
	public PageMaker(Criteria cri, double totalCount) {
		this.cri = cri;
		this.total =totalCount;
		this.displayNum = 10;
		
		//endPage
		//pageNum 15
		//display 10, 11~20
		//case 1: page 1, endPage 10	endPage = (pageNum/displayNum)*displayNum + (pageNum%displayNum)
		//case 2: page 10, endPage 10
		//case 3: page 11, endPage 20
		//case 4: page 15, endPage 20
		this.endPage = (int)Math.ceil((cri.getPageNum()*1.0 / this.displayNum)) * displayNum;
		
		System.out.println("endPage = " + endPage);
		
		//startPage
		//case 1: displayNum 10, endPage 10, start 1	startPage = (endPage-(displayNum-1))
		//case 2: displayNum 10, endPage 20, start 11
		//case 3: displayNum 5, endPage 10, start 6	
		this.startPage = endPage - (displayNum-1);
		
		System.out.println("startPage = " + startPage);
		
		this.realEndPage = (int)(Math.ceil(totalCount/this.cri.getAmount()));
		System.out.println("totalCount = " + totalCount);
		System.out.println("Amount = " + this.cri.getAmount());

		
		//10페이지 안될때
		if(this.realEndPage < this.endPage) {
			this.endPage = this.realEndPage;
		}
		
		//prev 활성화
		
		if(this.startPage > 1) {
			this.prev = true;
			this.start = true;
		}
		if(this.endPage < this.realEndPage) {
			this.next = true;
			this.end = true;
		}
		
			
			
		/*
		 * this.prev = this.startPage > 1; this.next = this.endPage < this.realEndPage;
		 * 
		 * this.start = this.startPage > 1; this.end = this.endPage < this.realEndPage;
		 */
		
		System.out.println("realEndPage = " + realEndPage);
		System.out.println("prv = " + prev);
		System.out.println("next = " + next);
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		System.out.println("startPage = " + startPage);
		
		
		

	}
}
