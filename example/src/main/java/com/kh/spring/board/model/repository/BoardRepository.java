package com.kh.spring.board.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;

@Repository
public class BoardRepository {


	public int boardCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.boardCount");
	}


	public List<Board> findAll(SqlSessionTemplate sqlSession, Map<String, Integer> map) {
		return sqlSession.selectList("boardMapper.findAll",map);
		//sqlSession.selectList("boardMapper.findAll",null,rowBounds);
		//RowBounds객체를 넘겨야할 경우
		//SelectList()의 오러로딩된 형태중 매개변수가 3개인 메소드로 반드시 꼭 꼭 꼭 무조건 절대로 이걸로 호출해야함!!!
		// 두 번쨰 인자값으로 전달할 값이 없다면 null값을 넘기면됨!
	}


	public int searchCount(SqlSessionTemplate sqlSession, Map<String, String> map) {
		return sqlSession.selectOne("boardMapper.searchCount",map);
	}


	public List<Board> findbyConditionAndKeyWord(SqlSessionTemplate sqlSession, Map<String, String> map,
			RowBounds rowBounds) {
		return sqlSession.selectList("boardMapper.findByConditionAndKeyWord",map,rowBounds);
	}


	public int insert(SqlSessionTemplate sqlSession, Board board) {
		return sqlSession.insert("boardMapper.insert",board);
	}
	
	public int increaseCount(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}


	public Board findById(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.findById",boardNo);
	}


	public int delete(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.delete",boardNo);
	}


	public int update(SqlSessionTemplate sqlSession, Board board) {
		return sqlSession.update("boardMapper.update",board);
	}


	public List<Board> selectImages(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("boardMapper.selectImages");
	}


	public List<Reply> selectReply(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectList("boardMapper.selectReply",boardNo);
	}


	public int insertReply(SqlSessionTemplate sqlSession, Reply reply) {
		return sqlSession.insert("boardMapper.insertReply",reply);
	}


	public Board boardAndReply(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.boardAndReply",boardNo);
	}


	public List<Board> findTopBoard(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("boardMapper.findTopBoard");
	}
	

	
}
