package com.kh.artspark.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.artspark.member.model.service.MemberService;
import com.kh.artspark.member.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("login")
	public ModelAndView login(Member member,ModelAndView mv, HttpSession session) {
		
		
		return null;
		
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("memId");
		
		return "redirect:/"; 
	}
	
}
