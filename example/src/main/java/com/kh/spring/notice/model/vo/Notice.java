package com.kh.spring.notice.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Notice {
	private int noticeNo;           //공지사항 번호
	private String noticeTitle;     //공지사항 제목
	private String noticeWriter;    //공지사항 작성자 아이디
	private String noticeContent;   //공지사항 내용
	private String createDate;     // 공지사항 작성 날짜
}
