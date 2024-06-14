package com.kh.spring.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//내 개발 방향을  정하고 사용해야한다. (RestController,Controller 중에 사용)
//제어하는 코드가 있어야 한다. (Get,Post)
@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	
	
	private final MemberService memberService;
	
	@RequestMapping("login.do") // RequestMapping타입의 에노테이션을 붙임으로서 HandlerMapping등록
	public void login() {
		log.info("로그인 요청입니다~~");
	
		
	}

}	

