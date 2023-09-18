package com.spring.basic.model;

import java.util.List;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserVO {

	private String userId;
	private String userPw;
	private String userName;
	private List<String> hobby;
}
