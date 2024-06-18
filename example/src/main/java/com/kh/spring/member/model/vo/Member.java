package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import java.time.LocalDateTime;

/**
 * @NoArgsConstructor
 * @AllArgsConstructor
 * @Builder
 * @Getter
 * @Setter
 * @ToString 
 *
 * @Data
 * 
 *  - Lombok 사용 시 주의사항!! --> 
 *  
 *  - 시작글자가 외자인 필드명은 XXXX
 *  - productName --> 올바른 표기법
 *  - pName -->PName() --> 문제 발생 지역은 EL표기법을 이용할 떄 내부적으로 getter에서 오류 발생
 *  - userName --> getUserName()
 *  
 *  ==> EL 표기법을 이용할 떄 내부적으로 getter사용방법 !
 *  
 *  ${pName} ==. getpName()으로 만들어서 문제가 발생
 *  
 *  필드명 작성 시 최소 소문자 두 글자 잇아으로 시작할 것 !!!!
 *  
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {

	private String userId;
	private String userPwd;
	private String userName;
	private String gender;
	private String age;
	private String phone;
	private String email;
	private String address;
	private Date enrollDATE;
	private Date modifyDate;
	private String status;

}
