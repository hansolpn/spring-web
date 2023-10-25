package com.spring.myweb.snsboard.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.snsboard.entity.SnsBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SnsBoardResponseDTO {

	private int bno;
	private String writer;
	private String fileLoca;
	private String fileName;
	private String content;
	private String regDate;
	private int likeCnt;
	
	public SnsBoardResponseDTO(SnsBoard board) {
		bno = board.getBno();
		writer = board.getWriter();
		fileLoca = board.getFileLoca();
		fileName = board.getFileName();
		content = board.getContent();
		regDate = makePrettierDateString(board.getRegDate());
		likeCnt = board.getLikeCnt();
		
	}
	
    private String makePrettierDateString(LocalDateTime regDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dtf.format(regDate);
    }
}
