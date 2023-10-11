package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.FreeUpdateRequestDTO;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class FreeBoardController {

	private final IFreeBoardService service;
	
	// 목록 화면
	@GetMapping("/freeList")
	public void freeList(Model model) {
		System.out.println("/freeboard/freeList: GET!");
		
		model.addAttribute("boardList", service.getList());
	}
	
	// 글 등록 처리
	@PostMapping("/freeRegist")
	public String regist(FreeRegistRequestDTO dto) {
		service.regist(dto);

		return "redirect:/freeboard/freeList";
	}
	
	// 글 상세 보기
	@GetMapping("/content")
	public String content(Model model, int bno) {
		
		model.addAttribute("boardContent", service.getContent(bno));
		return "freeboard/freeDetail";
	}
	
	// 글 수정
	@PostMapping("/update")
	public String update(FreeUpdateRequestDTO dto) {
		service.update(dto);
		
		return "redirect:/freeboard/freeDetail?bno=" + dto.getBno();
	}
	
	// 글 삭제
	@GetMapping("/delete")
	public String delete(int bno) {
		service.delete(bno);
		
		return "redirect:/freeboard/freeList";
	}
}
