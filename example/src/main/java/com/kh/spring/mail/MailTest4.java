package com.kh.spring.mail;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailTest4 {
	
	@Autowired
	private JavaMailSender sender;
	
	
	@GetMapping("fild-send")
	public String sendFile() throws MessagingException {
		
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
		
		helper.setTo("qjatn092028@gmail.com");
		helper.setSubject("파일 보낸다.");
		helper.setText("내용");
		
		//파일 첨부
		//javax.activation.DataSource
		//사진 경로 지정!!
		DataSource source = new FileDataSource("C:\\Users\\user1\\Desktop");
		helper.addAttachment(source.getName(),source);
		
		sender.send(message);
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
}
