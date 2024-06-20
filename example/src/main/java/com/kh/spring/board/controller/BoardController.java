package com.kh.spring.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.common.model.vo.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;

	@GetMapping("boardList")
	public String forwarding(int page) {
		
		// -- 페이징 처리 --
		
		//RowBounds 안쓴거
		
		//RowBounds 쓴거
		
		//필요한 변수들
		int listCount;   //현재 일반게시판의 게시글 총 개수 => BOARD테이블로부터 SELECT Count(*)을 활용해서 조회
		int currentPage; //현재 페이지(사용자가 요청한 페이지) => 앞에서 넘겨서 사용
		int pageLimit;   //페이지 하단에 보여질 페이징바의 최대 개수 => 10개로 고정  
		int boardLimit;  //한 페이지에 보여질 게시글의 최대 개수 => 10개로 고정
		
		int maxPage;     //가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		int startPage;   //페이지 하단에 보여질 페이징바의 시작 수
		int endPage;     //페이지 하단에 보여질 페이징바의 끝 수
		
		// * listCount : 총 게시글의 수
		listCount = boardService.boardCount();
		
		// * currentPage : 현재 페이지(사용자가 요청한 페이지)
		currentPage = page;
		//log.info("게시글의 총 개수 : {}, 현재 요청 페이지 : {} ",listCount,currentPage);
		
		// * pageLimit : 페이징의 최대 개수
		pageLimit =10;
		
		// * boardLimit: 한 페이지에 보여질 게시글의 최대 개수
		boardLimit = 10;
		
		// * maxPage: 가잠 마지막 페이지가 몇 번 페이지인지(총 페이지 개수)
		/**
		 * listCount, boardLimit에 영향을 받음
		 * 
		 *  * 공식 구하기
		 *  단, boardLimit이 10이라는 가정하에 규칙을 찾아보자
		 *  
		 *  총 개수(listCount)   게시글 개수(boardLimit)       maxPage(마지막페이지)
		 *  100              /  10                     ==  10.0  10페이지
		 *  106				 /  10					   ==  10.6  11페이지
		 *  111				 /  11                     ==  11.1  12페이지
		 *  
		 *  => 나눗셈 결과에 소수점을 붙여서 올림처리를 할 경우 maxPage가 됨
		 *
		 *  스텝
		 *  1. listCount를 double로 변환
		 *  2. listCount / boardLimit
		 *  3. Math.ceil() => 결과를 올림처리 
		 *  4. 결과값을 int로 형변환
		 *  
		 */
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit); 
		
		
		// * startPage: 페이지 하단에 보여질 페이징바의 시작 수
		/** 
		 * currentPage, pageLimit에 영향을 받음
		 * 
		 * - 공식
		 * 		단, pageLimit이 10이라고 가정해보자
		 * - startPage: 1,11,21,31,41... => n * 10 + 1;
		 * 
		 * 만약에 pageLimit이 5다! => 1,6,11,16,21 => n * 5 + 1;
		 * 
		 * 즉, startPage = n * pageLimit + 1;
		 * 
		 * currentPage                     startPage
		 * 1                               1
		 * 5                               1
		 * 9                               1
		 * 10                              1
		 * 13                              11
		 * 15                              11
		 * 20                              11
		 * 21                              21
		 * 
		 * => 1~10: n * 10 + 1 ==> n == 0
		 * => 11~20: n * 10 + 1 ==> n == 1
		 * => 21~39: n * 10 + 1 ==> n == 2
		 * 
		 * n = (currentPage - 1) / pageLimit;

		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit  + 1;
		
		// * endPage = 페이지 하단에 보여질 페이징바의 끝 수
		/**
		 * startPage,pageLimit에 영향을 받음(단 maxPage도 마지막 페이징바에 대한 영향을 끼침)
		 * 
		 * - 공식
		 * 단, pageLimit이 10이라고 가정
		 * 
		 * startpage : 1 => endPage : 10
		 * startpage : 11 => endPage :20
		 * startPage : 21 => endPage : 30
		 * 
		 * => endPage = startPage + pageLimit -1;
		 */
		
		endPage =  startPage + pageLimit - 1;
		
		//startPage가 1이라서 endPage가 10이 들어갔는데
		//maxPage가 2다??
		//endPage를 maxPage값을 변경
		
		if(endPage > maxPage) endPage = maxPage;
		
		//1.클래스로 사용시
		//PageInfo pageInfo = new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		
		
		//builer 사용해서 만들어보는 방법
		PageInfo pageInfo = PageInfo.builder().listCount(listCount)
											  .currentPage(currentPage)
											  .pageLimit(pageLimit)
											  .boardLimit(boardLimit)
											  .maxPage(maxPage)
											  .startPage(startPage)
											  .endPage(endPage)
											  .build();
		
		
		/**
		 * boardLimit이  10이라는 가정하에
		 * 
		 * currentPage == 1 ==> 시작값은 1 ,끝값10
		 * currentPage == 2 ==> 시작값은 11 ,끝값20
		 * currentPage == 3 ==> 시작값은 21 ,끝값30
		 * 
		 * 시작값 = (currentPage -1) * boradLimit + 1;
		 * 끝 값 = 시작값 + boardLimit - 1;
		 */
		return "board/list";
	}
	
	
	
	
	
	
	
	
	
	
}
