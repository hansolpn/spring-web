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

/*
*** 페이징 알고리즘 만들기 ***

#1. 사용자가 보게 될 페이지 화면
- 한 화면에 페이지 버튼을 10개씩 끊어서 보여준다면?
ex) 1 2 3 4 .... 9 10 [다음],   [이전] 31 32 33 34 ... 39 40 [다음]

- 만약 총 게시물의 개수가 67개이고, 한 화면에 게시물을 10개씩 보여준다면?
1 2 3 4 5 6 7 

- 총 게시물의 수가 142개이고, 현재 사용자가 12페이지를 클릭했다면?
[이전] 11 12 13 14 15

#2. 우선 총 게시물의 개수를 조회해야 합니다.
- 총 게시물 수는 DB로부터 수를 조회하는 SQL문을 작성합니다.

#3,. 사용자가 현재 위치한 페이지를 기준으로
끝 페이지 번호를 계산하는 로직을 작성.

- 만약 현재 사용자가 보고 있는 페이지가 3페이지이고,
 한 화면에 보여줄 페이지 버튼이 10개라면?
-> 끝 페이지 번호: 10번
- 만약 현재 페이지가 36페이지고, 한 화면에 보여줄 페이지 버튼 수가
5개라면?
-> 끝 페이지 번호: 40번

공식: Math.ceil(현재 위치한 페이지 번호 / 한 화면에 보여질 버튼 수)
			* 한 화면에 보여질 버튼 수

#4. 시작 페이지 번호 계산하기.
- 현재 위치한 페이지가 15페이지, 한 화면에 보여질 버튼이 10개
-> 시작 번호: 11번

- 현재 위치한 페이지가 37페이지, 한 화면에 버튼 7개씩
-> 시작 번호: 36번

공식: (끝 페이지 번호 - 한 화면에 보여질 버튼 수) + 1;

# 5. 끝 페이지 보정

- 총 게시물 수가 324개이고, 한 페이지당 10개의 게시물을 보여주고 있다.
- 그리고 현재 페이지는 31페이지이다.
- 그리고 한 화면에 페이지 버튼은 10개가 배치된다.
- 그렇다면, 위 공식에 의한 끝 페이지는 몇 번으로 계산되는가? -> 40번
- 하지만 실제 끝 페이지는 몇 번에서 끝나면 되는가? -> 33번

# 5-1. 이전 버튼 활성화 여부
- 시작 페이지 번호가 1로 구해진 시점에서는 비활성 처리, 나머지는 활성.

# 5-2. 다음 버튼 활성화 여부
- 공식: 보정하기 전 끝 페이지 번호 x 한 페이지에 들어갈 게시물 수 >= 총 게시물 수
         -> 비활성

# 5-3. 끝 페이지 값 보정
- 다음 버튼이 비활성화 되었다면 총 게시물 수에 맞춰 끝 페이지 번호를 재 보정합니다.
공식: Math.ceil(총 게시물의 수 / 한 페이지에 보여줄 게시물 수)
*/
