package com.spring.myweb.reply.dto;

import com.spring.myweb.reply.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRegistRequestDTO {

	private int bno;
	private String replyText;
	private String replyId;
	private String replyPw;
	
	public Reply toEntity(ReplyRegistRequestDTO dto) {
		return Reply.builder()
				.bno(dto.getBno())
				.replyWriter(dto.getReplyId())
				.replyPw(dto.getReplyPw())
				.replyText(dto.getReplyText())
				.build();
	}
}
