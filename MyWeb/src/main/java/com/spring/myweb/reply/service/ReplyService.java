package com.spring.myweb.reply.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.reply.dto.ReplyListResponseDTO;
import com.spring.myweb.reply.dto.ReplyRegistRequestDTO;
import com.spring.myweb.reply.dto.ReplyRequestDTO;
import com.spring.myweb.reply.entity.Reply;
import com.spring.myweb.reply.mapper.IReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService implements IReplyService {

	private final IReplyMapper mapper;
	private final BCryptPasswordEncoder encoder;
	
	@Override
	public void replyRegist(ReplyRegistRequestDTO dto) {
		// 비밀번호 암호화
		dto.setReplyPw(encoder.encode(dto.getReplyPw()));

		mapper.replyRegist(dto.toEntity(dto));

	}

	@Override
	public List<ReplyListResponseDTO> getList(int bno, int pageNum) {
		Page page = Page.builder()
				.pageNo(pageNum) // 화면에서 전달된 페이지 번호
				.amount(5) // 댓글은 한 화면에 5개씩
				.build();
		
		//List<Reply> list = mapper.getList(bno, page);
		
		Map<String,	Object> map = new HashMap<>();
		map.put("paging", page);
		map.put("boardNo", bno);

		List<ReplyListResponseDTO> dtoList = new ArrayList<>();
		for (Reply reply : mapper.getList(map)) {
			dtoList.add(new ReplyListResponseDTO(reply));
		}
		
		
		System.out.println("데이터베이스에서 불러온 댓글 목록: " + dtoList);
		return dtoList;
	}

	@Override
	public int getTotal(int bno) {
		return mapper.getTotal(bno);
	}

	@Override
	public String pwCheck(int rno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(ReplyRequestDTO dto) {
		if (encoder.matches(dto.getReplyPw(), mapper.pwCheck(dto.getReplyNo()))) {
			mapper.update(dto.toEntity());
			return "updateSuccess";
		}
		else {
			return "pwFail";
		}

	}

	@Override
	public String delete(ReplyRequestDTO dto) {
		if (encoder.matches(dto.getReplyPw(), mapper.pwCheck(dto.getReplyNo()))) {
			mapper.delete(dto.getReplyNo());
			return "deleteSuccess";
		}
		else {
			return "pwFail";
		}

	}

}
