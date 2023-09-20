package com.spring.basic.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.service.ScoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor //: final 필드가 존재한다면 그것만을 초기화 해 주는 생성자
public class ScoreController {

	private final ScoreService service;

	// 만약에 클래스의 생성자가 단 1개라면
	// 자동으로 @autowired를 작성해 줌
	
//	@Autowired
//	public ScoreController(ScoreService service) {
//		this.service = service;
//	}

	// 1. 성적 등록 화면 띄우기 + 정보 목록 조회
	@GetMapping("/list")
	public String list(Model model) {
		System.out.println("/score/list: GET!");

		model.addAttribute("sList", service.getList());

		return "score/score-list";
	}

	// 2. 성적 정보 등록 처리 요청
	@PostMapping("/register")
	public String register(ScoreRequestDTO dto) {
		// 단순 입력 데이터 읽기
		System.out.println("/score/register: POST! - " + dto);

		//서비스한테 일 시켜야지
		service.insertScore(dto);

		/*
		 등록 요청이 완료되었다면, 목록을 불러오는 로직을 여기다 작성하는 것이 아닌,
		 갱신된 목록을 불러오는 요청이 다시금 들어올 수 있도록 유도를 하자 -> sendRedirect()

		 "redirect:[URL]"을 작성하면 내가 지정한 URL로 자동 재 요청이 일어나면서
		 미리 준비해 둔 로직을 수행할 수 있다.
		 점수 등록 완료 -> 목록을 달라는 요청으로 유도 -> 목록 응답.
		 */
		return "redirect:/score/list";
	}

	// 3. 성적 정보 상세 조회 요청
	@GetMapping("/detail")
	public String detail(int stuNum, Model model) {
		System.out.println("/score/detail: GET!");

		retrieve(stuNum, model);

		return "score/score-detail";
	}

	// 4. 성적 정보 삭제 요청
	@GetMapping("/remove")
	public String remove(int stuNum) {
		System.out.println("/score/remove: GET!");

		service.delete(stuNum);

		return "redirect:/score/list";
	}

	// 5. 수정 화면 열어주기
	@GetMapping("/modify")
	public String modify(int stuNum, Model model) {
		System.out.println("/score/modify: GET!");

		retrieve(stuNum, model);

		return "score/score-modify";
	}
	
	// 6. 성적 정보 수정 요청
	@PostMapping("/modify")
	public String modify(int stuNum, ScoreRequestDTO dto) {
		System.out.println("/score/modify: POST!");
		
		service.modify(stuNum, dto);
		
		return "redirect:/score/detail?stuNum=" + stuNum;
	}

	//상세보기, 수정화면 공통 로직을 메서드화
	private void retrieve(int stuNum, Model model) {
		model.addAttribute("s", service.retrieve(stuNum));
	}
}
