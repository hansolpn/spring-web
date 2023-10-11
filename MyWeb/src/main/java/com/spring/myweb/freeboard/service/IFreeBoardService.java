package com.spring.myweb.freeboard.service;

import java.util.List;

import com.spring.myweb.freeboard.dto.FreeContentResponseDTO;
import com.spring.myweb.freeboard.dto.FreeListResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.FreeUpdateRequestDTO;

public interface IFreeBoardService {

	// 글 등록
	void regist(FreeRegistRequestDTO dto);
	
	// 글 목록
	List<FreeListResponseDTO> getList();
	
	// 상세보기
	FreeContentResponseDTO getContent(int bno);
	
	// 수정
	void update(FreeUpdateRequestDTO dto);
	
	// 삭제
	void delete(int bno);
}
