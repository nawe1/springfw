package com.kh.spring.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reply {

	private int replyNo;
	private String replyContent;
	private String replyWriter;
	private String refBoardNo;
	private String createDate;
	private String status;
	
}
