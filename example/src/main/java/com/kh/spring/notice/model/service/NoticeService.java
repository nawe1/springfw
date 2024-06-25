package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.spring.notice.model.vo.Notice;

@Service
public interface NoticeService {
	
	//공지사항 전체 조회
	int noticeCount();
	
	List<Notice> findAll(Map<String,Integer> map);
	
	//공지사항 상세보기
	int increaseCount(int noticeNo);
	
	//공지사항 수정하기
	int update(Notice notice);
	
	
	//공지사항 삭제하기
	int delete(int noticeNo);
}
