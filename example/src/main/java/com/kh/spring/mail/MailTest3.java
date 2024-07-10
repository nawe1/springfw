package com.kh.spring.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailTest3 {
	
	@Autowired
	private JavaMailSender sender;
	
	//sender메소드를 이용해서 MimeMessage를 만드는 것이다!!
	
	@GetMapping("mime-send")
	public String sendMail() throws MessagingException {
		
		//Mime Message객체 생성
		MimeMessage message = sender.createMimeMessage();
		
		//hepler에서 MimeMessage는 필수로 보내야한다.
		MimeMessageHelper helper = new MimeMessageHelper(message,false,"UTF-8");
		
		//제목
		helper.setSubject("형태가 있는 메일은 모양이 어떠한가?");
		
		String str= "뭔가 고유한";
		helper.setText("<a href='http://localhost/spring/auth?str="+str+"'>이걸로 인증하세요!~~</a>",true);
		
		
		//html로 인식해서 보낼꺼면 뒤에 true를 붙여야한다. 
		//helper.setText("<h1 style='color:orangered; font-size:52px'>우하하하</h1>",true);
		
		helper.setTo("qjatn092028@gmail.com");
		
		sender.send(message);
		
		return "redirect:/";
	}
	
	@GetMapping("auth")
	public String auth(String str) {
		if(str.equals("읽기 고유한")) {
			System.out.println("");
		}else{
			System.out.println("인증실ㅍ패");
		}
		return "redirect:/";
	}
	
}
