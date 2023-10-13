package com.spring.myweb.freeboard.dto.page;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class PageCreator {

	// 한 화면에 배치할 버튼의 개수
	private static final int BUTTON_NUM = 5;

	//화면 렌더링 시 페이지의 시작값과 끝값
	private int begin, end;

	// 이전, 다음 버튼 활성화 여부
	private boolean prev, next;

	// 현제 요청 페이지 정보
	private Page page;

	// 총 게시물 수
	private int articleTotalCount;

	// 페이징 알고리즘을 수행하기 위해 외부로부터 필요한 데이터를 전달 받음
	public PageCreator(Page page, int articleTotalCount) {
		this.page = page;
		this.articleTotalCount = articleTotalCount;

		calcDataOfPage(); // 전달 완료 후 알고리즘 수행
	}

	private void calcDataOfPage() {
		// 끝 페이지 계산
		end = (int)(Math.ceil(page.getPageNo() / (double)BUTTON_NUM) * BUTTON_NUM);
		// 시작 페이지 계산
		begin = end - BUTTON_NUM + 1;
		
		//이전 버튼 활성화 확인
		prev = begin > 1;
		
		// 다음 버튼 활성화 확인
		next = articleTotalCount > end * page.getAmount();
//		if (articleTotalCount <= end * page.getAmount()) {
//			next = false;
//		}
//		else {
//			next = true;
//		}
		
		// 끝 페이지 보정
		if (!next) {
			end = (int) Math.ceil(articleTotalCount / (double)page.getAmount());
		}
		
	}

}
