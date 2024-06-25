package com.kh.spring.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.notice.model.service.NoticeService;
import com.kh.spring.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {
	
	private final NoticeService noticeService;
	
	@GetMapping("noticePage")
	public String forawrding(@RequestParam(value="page", defaultValue="1") int page, Model model) {
		
		
		
		//필요한 변수들 
		int listCount;
		int currentPage;
		int pageLimit;
		int noticeLimit;
		
		int maxPage;
		int startPage;
		int endPage;
		
		// 총 공지사항 글의 수
		listCount = noticeService.noticeCount();
		
		//현재 페이지(사용자가 요청한 페이지)
		currentPage = page;
		
		//페이징의 최대 개수
		pageLimit=10;
		
		//한 페이지에 보여질 게시글의 최대 개수
		noticeLimit=10;
		
		//가장 마지막 페이지가 몇 번 페이지인지(총 페이지 개수)
		maxPage = (int)Math.ceil((double)listCount/ noticeLimit);
		
		//페이지 하단에 보여질 페이징바의 시작 수
		startPage= (currentPage -1) / pageLimit * pageLimit + 1;
		
		//페이지 하단에 보여질 페이징바의 끝 수
		endPage = startPage + pageLimit -1;
		
		//야호!
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = PageInfo.builder().listCount(listCount)
											  .currentPage(currentPage)
											  .pageLimit(pageLimit)
											  .noticeLimit(noticeLimit)
											  .maxPage(maxPage)
											  .startPage(startPage)
											  .endPage(endPage)
											  .build();
		
		Map<String,Integer> map= new HashMap();
		
		int startValue =(currentPage -1) * noticeLimit + 1;
		int endValue = startValue + noticeLimit - 1;
		
		map.put("startValue", startValue);
		map.put("endValue", endValue);
		
		List<Notice> noticeList = noticeService.findAll(map);
		
		model.addAttribute("list",noticeList);
		model.addAttribute("pageInfo",pageInfo);
		
		
		return "notice/list";
	}
	

}
