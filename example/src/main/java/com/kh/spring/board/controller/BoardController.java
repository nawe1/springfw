package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.PageTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	
	//menubar.jsp에서 클릭시 => boardList로!
	
	@GetMapping("boardList")
	public String forwarding(@RequestParam(value="page",defaultValue="1") int page,Model model) {
		
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
		
		Map<String, Integer> map = new HashMap();
		
		int startValue = (currentPage -1) * boardLimit + 1;
		int endValue = startValue + boardLimit - 1;
		
		map.put("startValue", startValue);
		map.put("endValue", endValue);
		
		List<Board> boardList =boardService.findAll(map);
		
		log.info("조회된 게시글의 개수: {}", boardList.size());
		log.info("---------------------------------------");
		log.info("조회된 게시글 목록: {}", boardList);
		
		model.addAttribute("list",boardList);
		model.addAttribute("pageInfo",pageInfo);
		
		return "board/list";
	}
	
	
	
	
	@GetMapping("search.do")
	public String search(String condition, String keyword,
			@RequestParam(value="page",defaultValue="1") int page,Model model) {
		
		log.info("검색조건: {}", condition);
		log.info("검색 키워드: {}", keyword);
	
		// 사용자가 선택한 조건과 입력한 키워드를 가지고
		// 페이징처리를 끝낸 후 검색결과를 들고가야함~
		
		// "writer","title","content"
		// 사용자가 입력한 키워드
		
		// Map, Class 중 Map 선택
		Map<String, String> map = new HashMap();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		int searchCount =boardService.searchCount(map);
		log.info("검색 조건에 부합하는 행의 수:{}", searchCount);
		int currentPage =page;
		int pageLimit =3;
		int boardLimit = 3;
		
		PageInfo pageInfo = PageTemplate.getPageInfo(searchCount, currentPage, pageLimit, boardLimit);
		
		//offset, limit
		RowBounds rowBounds = new RowBounds((currentPage - 1) * boardLimit,boardLimit);
		
		//MyBatis에서는 페이징 처리를 위해 RowBounds라는 클래스를 제공
		// *offset, limit 
		
		/**
		 * boardLimit 이 3일 경우
		 * 
		 * currentPage : 1-> 1~3 ==> 0(offset)
		 * currentPage : 2-> 4~6 ==> 3(offset)
		 * currentPage : 3-> 7~9 ==> 6(offset)
		 * 
		 * 
		 * (currentPage - 1) * boardLimit;
		 * 
		 * 
		 */
		List<Board> boardList = boardService.findbyConditionAndKeyWord(map, rowBounds);
		model.addAttribute("list",boardList);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("keyword",keyword);
		model.addAttribute("condition",condition);
		
		return "board/list";
	}
	
	
	@GetMapping("boardForm.do")
	public String boardFormForwarding() {
		return "board/insertForm";
	}
	
	@PostMapping("insert.do")
	public String insert(Board board,MultipartFile upfile, HttpSession session, Model model) { //주소가 있나 없나로 판단해야한다.
		
		//log.info("게시글정보:{}",board);
		//log.info("파일의 정보: {}",upfile);
		
		//첨부파일 존재 o / 존재 x
		//Multipart객체는 무조건 생성!!
		// => fileName필드에 원본명이 존재하는가 / 없는가
		
		//전달된 파일이 존재할 경우=> 파일 업로드!!
		
		if(!upfile.getOriginalFilename().equals("")) {
			// kh_년월일시분초_랜덤한값.확장자
			
			String originName = upfile.getOriginalFilename();
			
			String ext =originName.substring(originName.lastIndexOf("."));
			
			int num = (int)(Math.random() * 100) + 1;
			//double - 0.0 ~ 0.9999999999999
			
			String currentTime =new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
			
			String changeName ="KH_" + currentTime + "_" + num + ext;
			
			try {
				upfile.transferTo(new File(savePath + changeName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//첨부파일이 존재한다.
			//1. 업로드 완료
			//2. Board객체에 originName + changeName
			board.setOriginName(originName);
			board.setChangeName(savePath + changeName);
		}
		
		//첨부파일이 존재하지 않을 겨우 board  제목/ 내용 / 작성자  
		//첨부파일이 존재할 경우 board: 제목/ 내용 / 작성자 / 원본명 / 변경된 경로와 이름
		
		//어쩌고 저쩌고 디비간다 와~~~~
		if(boardService.insert(board) > 0) {
			session.setAttribute("alertMsg","게시글 작성 성공~");
			
			//무조건 리다이렉트 해야함!!
			return "redirect:boardList";
			
		}else {
			model.addAttribute("errorMsg","게시글 작성 실패 ... ㅠ");
			return "common/errorPage";
		}
		
		
		//return "redirect: board/insertForm.do";
	}
}
