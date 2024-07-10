package com.kh.spring.member.model.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.CretVo;
import com.kh.spring.member.model.vo.Member;

// Repository: 저장소
// 영속성 작업 : DB CRUD 작업(지금까지 저장할때 이게 가장 편하다고 함)
@Repository
public class MemberRepository {
	
	public Member login(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.selectOne("memberMapper.login",member);
	}

	public int insert(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.insert("memberMapper.insert",member);
	}

	public int update(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.update("memberMapper.update",member);
	}

	public int delete(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.update("memberMapper.delete",userId);
	}

	public int idCheck(SqlSessionTemplate sqlSession, String checkId) {
		return sqlSession.selectOne("memberMapper.idCheck",checkId);
	}

	public int sendMail(SqlSessionTemplate sqlSession, CretVo cert) {
		return sqlSession.insert("memberMapper.sendMail",cert);
	}

	public boolean validate(SqlSessionTemplate sqlSession, CretVo cert) {
		CretVo result= sqlSession.selectOne("memberMapper.validate",cert);
		return result != null;
	}

	public void deleteCode(SqlSessionTemplate sqlSession, CretVo cert) {
		sqlSession.delete("memberMapper.deleteCode",cert);
	}
	

}