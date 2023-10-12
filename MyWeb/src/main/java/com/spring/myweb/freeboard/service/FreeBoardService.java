package com.spring.myweb.freeboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.FreeContentResponseDTO;
import com.spring.myweb.freeboard.dto.FreeListResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.FreeUpdateRequestDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreeBoardService implements IFreeBoardService {

	private final IFreeBoardMapper mapper;

	@Override
	public void regist(FreeRegistRequestDTO dto) {
		mapper.regist(FreeBoard.builder()
								.title(dto.getTitle())
								.content(dto.getContent())
								.writer(dto.getWriter())
								.build());

	}

	@Override
	public List<FreeListResponseDTO> getList() {
		List<FreeBoard> list = mapper.getList();
		List<FreeListResponseDTO> dtoList = new ArrayList<>();
		
		for (FreeBoard board : list) {
			dtoList.add(new FreeListResponseDTO(board));
		}
		
		return dtoList;
	}

	@Override
	public FreeContentResponseDTO getContent(int bno) {

		return new FreeContentResponseDTO(mapper.getContent(bno));
	}

	@Override
	public void update(FreeUpdateRequestDTO dto) {
		mapper.update(FreeBoard.builder()
								.bno(dto.getBno())
								.title(dto.getTitle())
								.content(dto.getContent())
								.build());

	}

	@Override
	public void delete(int bno) {
		mapper.delete(bno);

	}

}
