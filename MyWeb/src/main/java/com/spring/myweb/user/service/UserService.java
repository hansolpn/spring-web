package com.spring.myweb.user.service;

import org.springframework.stereotype.Service;

import com.spring.myweb.user.dto.UserJoinRequestDTO;
import com.spring.myweb.user.entity.User;
import com.spring.myweb.user.mapper.IUserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final IUserMapper mapper;

	public int idCheck(String id) {
		return mapper.idCheck(id);

	}

	public void join(UserJoinRequestDTO dto) {
		mapper.join(User.builder()
						.userId(dto.getUserId())
						.userPw(dto.getUserPw())
						.userName(dto.getUserName())
						.userPhone1(dto.getUserPhone1())
						.userPhone2(dto.getUserPhone2())
						.userEmail1(dto.getUserEmail1())
						.userEmail2(dto.getUserEmail2())
						.addrBasic(dto.getAddrBasic())
						.addrDetail(dto.getAddrDetail())
						.addrZipNum(dto.getAddrZipNum())
						.build());

	}

	public String login(String userId) {
		return mapper.login(userId);
		
	}


}
