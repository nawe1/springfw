package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.spring.notice.model.vo.Notice;

@Service
public interface NoticeService {
	
	List<Notice> findAll();
	
	Notice findById(int noticeNo);
	
	int save(Notice notices);
	
	int update(Notice notices);
	
	int delete(int noticeNo);

}