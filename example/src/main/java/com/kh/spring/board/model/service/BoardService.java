package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;

@Service
public interface BoardService {
	
	//게시글 전체 조회 + 페이징 처리
	//현재 Board테이블의 총 행의 개수를 알아야 페이징 처리를 할 수 있다.
	
	int boardCount();
	
	//그룹함수 5총사
	/**
	 * SUM()
	 * AVG()
	 * MIN()
	 * MAX()
	 * COUNT()
	 */
	
	//배열을 못쓰는 이유 - board가 몇개인지 몰라서 사용하는데 어려움이 있다.
	//Board[] boards = new Board[10];
	
	
	//게시글 목록 조회
	List<Board> findAll(Map<String,Integer> map);
	
	
	//게시글 검색 기능
	int searchCount(Map<String,String>map);
	
	
	//검색 목록 조회
	List<Board> findbyConditionAndKeyWord(Map<String,String> map,RowBounds rowBounds);
	
	//게시글 작성
	int insert(Board board);
	
	
	//게시글 상세보기
	//조회수 증가
	//우선 수행되야한다. 조회 수를 먼저 올려놓고 db가 상세조회가 되게 해애 힌디.
	int increaseCount(int boardNo);
	
	//상세조회
	Board findById(int boardNo);
	
	
	
	//게시글 삭제하기
	int delete(int boardNo);
	
	
	//게시글 수정하기
	int update(Board board);
	//-------------------------------------------- 댓글 관련(AJAX)

	//사진 게시글 목록
	List<Board> selectImages();



	
	//1.AJAX를 활용한 댓글 목록 조회 --> 2. MyBatis기술을 이용한 댓글 조회
	List<Reply> selectReply(int boardNo);

	
	//댓글 작성하기
	int insertReply(Reply reply);
	
	//---------------------------------------------- Top-N문서

}
