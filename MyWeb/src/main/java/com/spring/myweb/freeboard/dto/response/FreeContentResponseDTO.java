package com.spring.myweb.freeboard.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.freeboard.entity.FreeBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class FreeContentResponseDTO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private String date;
	
	public FreeContentResponseDTO(FreeBoard board) {
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		this.date = board.getUpdateDate() == null
			  ? makePrettierDateString(board.getRegDate())
			  : makePrettierDateString(board.getUpdateDate()) + " (수정됨)";
		
	}
	
	private String makePrettierDateString(LocalDateTime regDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		return dtf.format(regDate);
		
	}
}
