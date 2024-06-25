package com.kh.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AjaxController {
	
	/**
	 * 1. HTTPServletReponse객체로 응답데이터를 응답하기(Stream을 이용한 방식)
	 * @throws IOException 
	 * 
	 */
	
	//아날로그한 방식이다!!!
	/*
	@GetMapping("ajax1.do")
	public void calSum(String menu,int amount,HttpServletResponse response) throws IOException {
		
		//System.out.println("사용자가 입력한 메뉴 : " + menu);
		//System.out.println("사용자가 입력한 수량 : " + amount);
		
		int price =0;
		switch(menu) {
		case "알밥" : price = 1000; break;
		case "돈까스" :price = 30000; break;
		case "서브웨이" :price = 2000; break;
		case "김치찜" :price =4000; break;
		case "막국수" :price = 6000; break;
		}
		
		price *= amount;
		
		//System.out.println(price);
		
		// 서비스 다녀와서 요청처리가 다 끝났다.
		// 요청한 페이지에 반환할 데이터를 완성해냈다!!
		
		//한글이 있으면 꺠질 수 있어서 데이터 형식을 지정해 줘야한다.
		response.setContentType("text/html; charset=UTF-8");
		
		//출력
		response.getWriter().print(price);
		
	}
	*/
	/**
	 * 2.응답할 데이터를 문자열로 반환
	 * 		=> HttpServletResponse를 사용하지 않는 방법
	 * 			=> String 반환하면 포워딩 => 응답뷰의 경로로 인식을해서 뷰 리졸버로 전달
	 * 
	 * 따라서 스프링을 사용해서 응답데이터를 반환 할때는
	 * 
	 * => MessageConverter로 이동하게끔 해주어야함! ==> @ResponseBody 에노테이션!
	 */
	
	@ResponseBody
	@GetMapping(value="ajax1.do",produces="text/html; charset=UTF-8")
	public String calSum(String menu,int amount){
		
		int price =0;
		switch(menu) {
		case "알밥" : price = 1000; break;
		case "돈까스" :price = 30000; break;
		case "서브웨이" :price = 2000; break;
		case "김치찜" :price =4000; break;
		case "막국수" :price = 6000; break;
		}
		
		price *= amount;
		
		return String.valueOf(price) + "한글";
		
	}
	
	@GetMapping("responseData")
	public void test(HttpServletResponse response) throws IOException{
		
		PrintWriter writer = response.getWriter();
	}
	
	
	
}
