package com.kh.artspark.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Member {

	private String memId;
	private String memPwd;
	private String memNickname;
	private String status;
	private String memEmail;
	private String memEnroll;
	private String memCategory;
	
}
