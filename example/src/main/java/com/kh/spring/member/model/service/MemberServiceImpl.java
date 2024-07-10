package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.repository.MemberRepository;
import com.kh.spring.member.model.vo.CretVo;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

// 비즈니스 로직/ 실질적으로 가장 중요한 부분 
// 의사결정 코드 작성/ 
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final SqlSessionTemplate sqlSession;
	private final MemberRepository memberRepository;
	
	public int returnNum() {
		return 1;
	}

	@Override
	public Member login(Member member) {
		//내가 수행해야 하는 SQL문을 호출
		return memberRepository.login(sqlSession,member);
	}

	@Override
	public int insert(Member member) {
		//1. DAO호출
		//2. 컨트롤러로 결과 반환
		return memberRepository.insert(sqlSession,member);
	}

	@Override
	public int update(Member member) {
		return memberRepository.update(sqlSession,member);
	}

	@Override
	public int delete(String userId) {
		return memberRepository.delete(sqlSession,userId);
	}
	


	@Override
	public int idCheck(String checkId) {
		return memberRepository.idCheck(sqlSession, checkId);
	}
	
	@Override
	public int sendMail(CretVo cert) {
	    return memberRepository.sendMail(sqlSession,cert);
	}

	@Override
	public boolean validate(CretVo cert) {
		boolean result = memberRepository.validate(sqlSession,cert);
	
		if(result) {
			memberRepository.deleteCode(sqlSession,cert);
		}
		return result;
	}
}
