package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.dto.page.PageCreator;
import com.spring.myweb.freeboard.dto.request.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.request.FreeUpdateRequestDTO;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class FreeBoardController {

	private final IFreeBoardService service;

	// 목록 화면
	@GetMapping("/freeList")
	public void freeList(Model model, Page page) {
		System.out.println("/freeboard/freeList: GET!");

		int totalCount = service.getTotal(page);
		
		if (totalCount == 0) {
			page.setKeyword(null);
			page.setCondition(null);
			totalCount = service.getTotal(page);
			model.addAttribute("msg", "searchFail");
		}
		
		model.addAttribute("boardList", service.getList(page));
		model.addAttribute("pc", new PageCreator(page, totalCount));
	}

	// 글 등록 처리
	@PostMapping("/freeRegist")
	public String regist(FreeRegistRequestDTO dto) {
		System.out.println("/freeboard/freeRegist: POST!");
		service.regist(dto);

		return "redirect:/freeboard/freeList";
	}
	
	// 글쓰기 페이지를 열어주는 메서드
	@GetMapping("/freeRegist")
	public void regist() {}

	// 글 상세 보기
	@GetMapping("/content")
	public String content(Model model, int bno,
						  @ModelAttribute("p") Page page) {
		System.out.println("/freeboard/content: GET!");
		model.addAttribute("article", service.getContent(bno));

		return "freeboard/freeDetail";
	}

	//글 수정 페이지 이동 요청
	@PostMapping("/modPage")
	public String modPage(@ModelAttribute("article") FreeUpdateRequestDTO dto) {
		
		return "freeboard/freeModify";
	}
	
	// 글 수정
	@PostMapping("/modify")
	public String modify(FreeUpdateRequestDTO dto) {
		System.out.println("/freeboard/update: POST!");
		service.update(dto);

		return "redirect:/freeboard/content?bno=" + dto.getBno();
	}

	// 글 삭제
	@PostMapping("/delete")
	public String delete(int bno) {
		System.out.println("/freeboard/delete: POST!");
		service.delete(bno);

		return "redirect:/freeboard/freeList";
	}
}
