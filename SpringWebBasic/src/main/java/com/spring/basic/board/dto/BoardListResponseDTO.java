package com.spring.basic.board.dto;

import java.time.LocalDateTime;

import com.spring.basic.board.entity.Board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class BoardListResponseDTO {

	public BoardListResponseDTO(Board b) {
		boardNo = b.getBoardNo();
		writer = b.getWriter();
		title = b.getTitle();
		regDate = b.getRegDate();
	}
	
	private int boardNo;
	private String writer;
	private String title;
	private LocalDateTime regDate;
}
