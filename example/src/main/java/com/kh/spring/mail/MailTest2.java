package com.kh.spring.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailTest2 {
	//root-content.xml에 bean등록을 다 했기 때문에 메시지만 만들면 된다.
	@Autowired
	private JavaMailSenderImpl sender;

	@GetMapping("mail-test")
	public String mail() {
		
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		//제목,내용,첨부파일,받는사람,참조,숨은참조
		message.setSubject("또받아라");
		message.setText("테스트");
		String[] toArr = {"qjatn092028@gmail.com","qkrqjatn09@naver.com"};
		
		
		//받는 분
		message.setTo(toArr);
		
		sender.send(message);
		
		return "redirect:/";
	}
}
