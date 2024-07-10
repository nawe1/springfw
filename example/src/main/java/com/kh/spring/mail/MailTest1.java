package com.kh.spring.mail;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailTest1 {

	
	public static JavaMailSenderImpl sender;
	
	public static void main(String[] args) {

		JavaMailSenderImpl impl = new JavaMailSenderImpl();  //편지 봉투의 역할
		
		// 계정에 관련 설정
		impl.setHost("smtp.gmail.com");
		impl.setPort(587);
		impl.setUsername("qjatn092028@gmail.com");
		impl.setPassword("qmlg orti wmoz gfie");
		
		
		
		// 보안 관련 설정
		//키,값으로 이루어진 맵 형태이다.
		//properties는 무조건 문자열로 적자 해서 키,값을 무조건 문자열,문자열로 사용한다!!
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable","true"); //보안을 설정하겠다는 뜻(tls)
				
		impl.setJavaMailProperties(prop);
		
		sender =impl;
		
		
		//메시지 설정
		SimpleMailMessage message = new SimpleMailMessage();
		
		
		//메시지 정보 세팅: 제목, 내용, 첨부파일(SimpleMail에선 불가), 받는사람, 참조, 숨은 참조
		
		//제목
		message.setSubject("제목");
		
		//본문
		message.setText("테스트");
		
		//받는 분
		String to ="받는분";
		
		String[] toArr = {"qjatn092028@gmail.com","qkrqjatn09@naver.com"}; //배열을 활용해서 여러명에게 전달가능
		
		
		message.setTo(toArr);
		
		/**
		 * 
		 * 참조
		 * a
		 * 메시지객체,setCc(참조할 주소)
		 * 
		 * 
		 * 숨은 참조
		 * 
		 * 메시지객체,setBcc(숨은 참조할 주소)
		 */
		
		//보내기 버튼
		sender.send(message);
	}

}
