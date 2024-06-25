package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

//import com.kh.spring.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

//내 개발 방향을  정하고 사용해야한다. (RestController,Controller 중에 사용)
//제어하는 코드가 있어야 한다. (Get,Post)
@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	
	
	private final MemberService memberService;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;
	/*
	@RequestMapping("login.do") // RequestMapping타입의 에노테이션을 붙임으로서 HandlerMapping등록
	public String login(HttpServletRequest requset) {
		
		String userId = requset.getParameter("id)");
		String userPwd = requset.getParameter("pwd)");
		
		
		log.info("회원이 입력한 아이디 값: ()", userId);		
		log.info("회원이 입력한 비밀번호 값: ()", userPwd);
		
		return "main";
	}
	
	/**
	 * 3. @RequestParam에노테이션을 생략하는 방법
	 * 
	 * 단, 매개변수 식별자를  jsp의 name 속성값(요청시 전달하는 값의 키값)과 동일하게 작성해주어야만 자동으로 값이 주입
	 * 단점이라고 한다면 2.에 사용했던 defaultValue속성을 사용할 수 없음.
	*/
	
	/*
	@RequestMapping("login.do")
	public String login(String id, String pwd) {
		log.info("회원이 입력한 아이디 값: {}", id);
		log.info("회원이 입력한 비밀번호 값: {}", pwd);
		
		
		// 컨트롤러가 해야 할 일1 -> 제어
		//1. 데이터 가공 -> DTO(Data Transform Object)
		Member member = new Member();
		member.setUserId(id);
		member.setUserPwd(pwd);
		 	  
		// 1.5 서비스 호출 
		//memberService.login(id,pwd);
		 
		 
		// 2. 응답화면 지정
		 
		return "main";
	}
	
	*/
	
	/**
	 * 4. 커멘드 객체 방식(model Attribute)
	 *
	 * **** 반드시 name속성값과 담고자하는 필드명이 동일해야함! + setter가 꼭 있어야함! + 기본생성자가 꼭 있어야함!
	 * 해당 메서드의 매개변수로
	 * 요청 시 전달값을 담고자하는 클래스의 타입을 지정한뒤
	 * 요청 시 전달값의 키값(jsp의 name속성값)을 클래스의 담고자하는 필드명과 동일하게 작성
	 * 
	 * 스프링 컨테이너가 해당 객체를 기본생성자로 생성한 후 내부적으로 Setter메소드를 찾아서 요청 시 전달값을 해당 필드에 담아줌(Setter injection)
	 */
	/*
	@RequestMapping("login.do")
	public String login(Member member) {
		
		log.info("가공된 멤버객체 : {} ", member);
		
		Member loginMember = memberService.login(member);
		
		return "main";
	}
	*/
	//1. 자바에서는 대입연산자를 기준으로 왼쪽과 오른쪽의 자료형이 같아야 함!
	//2. 자바에서는 동일한 타입의 값끼리만 연산이 가능함 => 연산의 결과도 동일한 타입이여야함.
	
	
	//로그인 ,회원가입 ,아이디 찾기, 비밀번호 찾기, 개인정보수정, 배송지조회, 배송지수정, 배송지삭제,회원탈되...
	
	//1.로그인
	
	//로그인이란 무엇인가...
	
	//우리팀이 생각하는 로그인:

	//로그인 대전체: 1.회원가입이 성공적으로 수행되어있어야함.
	//			 2.회원가입시 사용자에게 id값과 password값을 입력 받았어야 함.

//	로그인: 사용자가 회원가입 기능 사용 시 입력했던 id값과 password값을 입력하면 유효성 검사를 거쳐
//	화면사에서 자바스크립트 정규표현식을 사용하여 올바른 값이 입력되었는지 확인한 뒤 Controller -> Service -> Repository를
//	거쳐 DataBase에 id값과 password값을 Where조건절의 비교대상으로 검사하여 조회된 결과가 존재할 시 회원의 정보를 반환받아 VO객체의 필드에
//	대입하여 Session영역에 저장해놓는 것
//	
//	화면상에서 정상적인 입력값을 입력하지 않았을 시: 입력할 수 없는 특수문자, 이상한 문자 --> 자바스크립트를 통해 이벤트 실행 x
//	올바른 값을 입력했지만 DB에 값이 존재하지 않을 시 : NULL값을 반환해서 alert창/ 에러페이지
//	로그인 기능 구현시 필요한 화면의 개수 : menubar.jsp/errorpage.jsp
//	로그인 기능 구현시 필요한 클래스: MemberController, MemberServiceImpl, MemberRepository,Member
//	로그인 기능 구현 시 매팽 값: login.do
//	로그인 기능 구현 시 사용할 메서드명 : login, selectMember, selectById, findById
//	
//	발생할 수 있는 예외: ~~ 예외처리 ~
//	담당자: 홍길동
//
//	참고 사이트: abc.com ~ bbbb.com 요런식으로 구현할 것 ~~ 근데 애네는 뭐시어쩌고가 이거 어찌한지 모름

	
	/**
	 * Spring에서 Handeler가 요청 시 전달값(Parameter)을 받는 방법
	 * 
	 * 1.HttpServletRequset을 이용해서 전달받기(기존의 JSP/Servlet방식)
	 * 
	 * 해당 핸들러의 매개변수로
	 */
	
	
	//REST방식의 URL만들기
	//localhost/spring/member/12
	
	/*
	@GetMapping("/member/{id}")
	public void restTest(PathVariable String id) {
		
		log.info("앞단에서 넘긴값 : {}", id);
	}
	*/
	
	/*
	 * 요청 처리 후 응답데이터를 담고 응답페이지로 포워딩 또는 리다이렉트 하는 방법
	 * 
	 * 1.스프링에서 제공하는 Model객체를 사용하는 방법
	 * forwarding할 응답 뷰로 전달하고자하는 데이터를 Key-Value형태로 담을 수 있는 영역
	 * Model객체는 requestScope
	 */
	//여러분들은 화면으로 jsp를 사용하고 있다!
	/*
	@PostMapping("login.do")
	public String login(Member member,
						Model  model,
						HttpSession session) {
		
		Member loginUser = memberService.login(member);
		
		if(loginUser == null) { //로그인 실패! => 에러문구 requestScope에 담아서 에러페이지로 포워딩
			
			model.addAttribute("errorMsg:","로그인에 실패했어요~~ ㅠ.ㅠ");
			
			//포워딩
			// /WEB-INF/views/ ~~~~~~ .jsp
			// /WEB-INF/views/common/errorPage.jsp
			
			// -prefix : /WEB-INF/views/
			
			// - suffix: .jsp
			
			return "common/errorPage";
		}else {//로그인 성공! => loginUser를 sessionScope에 담고 응답화면
			session.setAttribute("loginUser", loginUser);
			
			//redirect 방식~
			// /redirect:/
			return "redirect:/";
		}
		
		//return "main";
	}
	*/
	
	/*
	 * 2.Spring에서 제공하는 ModelAndView 타입을 사용하는 방법
	 * Model은 데이터를 KEy-value세트로 담을 수 있는 공간이라고 한다면
	 * View는 응답 뷰에 대한 정보를 담을 수 있는 공간
	 * 
	 * Model객체와 View가 결합된 형태의 객체
	 */
	
	@PostMapping("login.do")
	public ModelAndView login(Member member,
							  ModelAndView mv,
							  HttpSession session) {
		
		Member loginUser =memberService.login(member);
		
		//member => userId필드 : 사용자가 입력한 아이디 값
		//    	 => userPwd필드 : 사용자가 입력한 비밀번호 값(평문)
		
		// MEMBER테이블에 사용자가 입력한 userId값이 존재하고 Status컬럼의 값이 'Y'와 일치한다면
		
		//loginUser => 조회된 ResultSet의 컬럼의 값이 필드에 차곡차곡담긴 Member의 객체의 주소값!
		//          => userPwd 필드 : DB에 기록된 암호화된 비밀번호(암호문) 
		
		//BcryptPasswordEncoder객체 matches()
		//matches(평문, 암호문)
		
		//암호문에 포함되어있는 Salt값을 판단해서 평문에 Salt값을 더해서 암호화를 반복하여 두 값이 같은지 비교!
		//일치하면 true / 일치하지 않으면 false
		
		
		log.info("loginUser: {}", loginUser);
		if(loginUser != null && bcryptPasswordEncoder.matches(member.getUserPwd(), loginUser.getUserPwd())) {
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		}else {
			mv.addObject("errorMsg","로그인 실패 ㅠㅠ...")
				.setViewName("common/errorPage");
			
		}
		return mv;
	}
		
	
	@GetMapping("logout.do")
	public String logout(HttpSession session) {
		
		// sessionScope에 존재하는
		//  loginUser 제거
		//session.invalidate(); //세션 초기화
		session.removeAttribute("loginUser");
		
		return "redirect:/";
	}
	
	
	@GetMapping("enroll.do")
	public String enrollForm() {
		
		
		// /WEB-INF/views/member/enrollForm.jsp 파일의 위치로 가야한다.
		// prefix => /WEB-INF/views/
		// surfix => .jsp 이기때문에 중간값만 적어주면 된다.
		
		return "member/enrollForm";
	}
	
	@PostMapping("join.do")
	public String join(Member member, Model model) {
		
		log.info("회원가입 객체 : {}", member);
		//log.info("평문: {}",member.getUserPwd());
		String encPwd = bcryptPasswordEncoder.encode(member.getUserPwd());
		//log.info("암호문: {}", encPwd);
		member.setUserPwd(encPwd);;
		// Insert할 데이터가 필드에 담긴 Member객체의 userPwd필드에 평문이 아닌 암호문을 담아서 DB로 보내기!
		
		//memberService.insert(member);
		
		//1. 데이터 가공 -> DTO(Data Transform Obje
		// 1.5 서비스 호출 	 
		//2.응답화면 지정
		String viewName ="";
		if(memberService.insert(member) > 0) {//성공 => 메인~
			
			//return "redirect:/";
			viewName ="redirect:/";
		}else {//실패 => 에러메시지담아서 에러페이지로 포워딩
			
			model.addAttribute("errorMsg","회원가입실패");
			
			//return "cmommon.errorPage";
			viewName =" common/errorPage";
		}
		
			return viewName;
		//1. 한글이 깨짐! => web.xml에 스프링이 제공하는 인코딩 필터 등록!
		//2. 비밀번호가 사용자가 입력한 있는 그대로의 평문(plain text)
		
	}
	
	@GetMapping("myPage.do")
	public String myPage() {
		return "member/myPage";
	}
		
	@PostMapping("update.do")
	public String update(Member member, HttpSession session, Model model) {
		
		log.info("수정 요청 멤버:{}",member);
	
		// 1 / 0
		if(memberService.update(member) > 0) {
			
			//DB로부터 수정된 회원정보를 다시 조회해서
			//sessionScope에 loginUser라는 키값으로 덮어씌워줄것!!!
			
			session.setAttribute("loginUser",memberService.login(member));
			
			//1,포워딩
			//주소값이 노출된다
			//코드 중복은 유지보수를 힘들게 해서 사용하지 않는게 좋다!
			//return "member/myPage";
			
			
			//2.리다이렉트
			
			//성공메시지 띄우기
			session.setAttribute("alertMsg","정보 수정 성공");	
		
			return "redirect:myPage.do";
			
		}else {
			model.addAttribute("errorMsg","정보 수정에 실패했습니다.");
			return "common/errorPage";
		}
		
	}
	
	@PostMapping("delete.do")
	public String delete(Member member, HttpSession session, Model model) {
		
		//이제 뭘 해야할까?
		//아이디/비밀번호
		
		//비밀번호 잘 맞게 썼나?
		
		//매개변수 Member => userPwd : 사용자가 입력한 비밀번호 평문
		//session의 loginUser키값으로 저장되어있는 Member객체의 userPwd 필드 : DB에 기록된 암호화된 비밀번호
		
		String plainPwd = member.getUserPwd();
		String encPwd = ((Member)session.getAttribute("loginUser")).getUserPwd();
		//String encPwd = session.getAttribute("loginUser");
		//1절 Member의 주소
		//2절 타입
		
		if(bcryptPasswordEncoder.matches(plainPwd, encPwd)) {
			
			if(memberService.delete(member.getUserId()) > 0) {
				session.setAttribute("alertMsg", "탈퇴 성공");
				session.removeAttribute("loginUser");
				return "redirect:/";
			}else{
				model.addAttribute("errorMsg","회원 탈퇴에 실패했습니다.");
				return "common/errorPage";
			}
			//회원 탈퇴기 때문에 아이디 받아서 사용!
		}else {
			session.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			return "redirect : mypage.do";
		}
	}
	
	//정리!!!
	//기획 할 떄 !!!
	//누가 어떤 매핑값을 쓰자!!
	
	//spring/member/123
	//spring/board
	
	//get/post/put/patch/delete
	@ResponseBody
	@GetMapping("idcheck.do")
	public String checkId(String checkId) {
		
		//int result = memberService.idCheck(checkId);
		
		/*
		if(result > 0) {
			return "NNNNN";
		}else {
			return "NNNNY";
		}
		*/
		// NNNNY / NNNNN 이 나오게 하는 것이다.
		//return "";
		
		//return result > 0 ? "NNNNN" : "NNNNY";
		//오늘의 과제
		//마이페이지 => 회원 정보 수정 / 회원 탈퇴
		// 비밀번호 변경 
		//AJAX를 활용한 비밀번호 변경 기능 구현하기! - 비밀번호/새로운 비밀번호/ 새로운 비밀번호 재입력을 구현해야한다! - 할려면 비밀번호가 맞는지 중복체크부터!
	
		return memberService.idCheck(checkId) > 0 ? "NNNNN" : "NNNNY";
	}
	
	

}
	

