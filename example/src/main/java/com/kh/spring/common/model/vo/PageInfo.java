package com.kh.spring.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class PageInfo {

	private int listCount;   
	private int currentPage; 
	private int pageLimit;   
	private int boardLimit;  
	
	private int maxPage;    
	private int startPage;  
	private int endPage;   
	
}
