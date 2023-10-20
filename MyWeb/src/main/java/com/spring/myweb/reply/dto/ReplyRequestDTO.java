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
public class ReplyRequestDTO {

	private int replyNo;
	private String replyText;
	private String replyPw;
	
	public Reply toEntity() {
		return Reply.builder()
			.replyNo(replyNo)
			.replyPw(replyPw)
			.replyText(replyText)
			.build();
	}
}
