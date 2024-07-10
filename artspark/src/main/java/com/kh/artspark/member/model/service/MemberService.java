package com.kh.artspark.member.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.artspark.member.vo.Member;

@Service
public interface MemberService {

	List<Member> memberList();

	
	// 로그인(SELECT)
	Member login(Member member);
	
	
	//회원수정

	//id중복체크
	
	//아이디찾기
	
	//비밀번호 찾기
	
	//회원탈퇴
	int delete(Member memId);
}
