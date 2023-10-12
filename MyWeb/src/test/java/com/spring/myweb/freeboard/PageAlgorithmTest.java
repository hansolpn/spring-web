package com.spring.myweb.freeboard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PageAlgorithmTest {

	@Autowired
	private IFreeBoardMapper mapper;

	@Test
	@DisplayName("페이지 버튼 알고리즘 테스트")
	void pageTest() {
		int page = 16; // 사용자가 요청한 페이지 번호
		int cpp = 20; // 한 화면에 보여줄 게시물 개수
		int btnNum = 5; // 한 화면에 보여줄 버튼 개수

		int articleTotalCount = mapper.getTotal();
		System.out.println("총 개시물 수: " + articleTotalCount);

		// 끝 페이지 번호 구하기
		int endpage = (int)(Math.ceil(page / (double)btnNum) * btnNum);
		System.out.println("보정 전 끝 페이지 번호: " + endpage);

		// 시작 페이지 번호 구하기
		int beginPage = endpage - btnNum + 1;
		System.out.println("시작 페이지 번호: " + beginPage);

		boolean prev = beginPage == 1 ? false : true;
		boolean next = articleTotalCount <= endpage * cpp ? false : true;

		System.out.println("이전 버튼 활성화: " + prev);
		System.out.println("다음 버튼 활성화: " + next);

		if (!next) {
			endpage = (int) Math.ceil(articleTotalCount / (double)cpp);
		}

		System.out.println("보정 후 끝 페이지 번호: " + endpage);

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
