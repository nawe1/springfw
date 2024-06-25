package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.spring.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class NoticeServiceImpl implements NoticeService{

	@Override
	public int noticeCount() {
		return 0;
	}

	@Override
	public List<Notice> findAll(Map<String, Integer> map) {
		return null;
	}

	@Override
	public int increaseCount(int noticeNo) {
		return 0;
	}

	@Override
	public int update(Notice notice) {
		return 0;
	}

	@Override
	public int delete(int noticeNo) {
		return 0;
	}

}
