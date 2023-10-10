package com.spring.basic.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.basic.board.dto.BoardListResponseDTO;
import com.spring.basic.board.dto.BoardModifyRequestDTO;
import com.spring.basic.board.entity.Board;
import com.spring.basic.board.repository.IBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final IBoardMapper mapper;

	public void insertArticle(String writer, String title, String content) {
		Board board = new Board();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		mapper.insertArticle(board);
		
	}

	public List<BoardListResponseDTO> getArticles() {
		List<Board> boardLists = mapper.getArticles();
		List<BoardListResponseDTO> dtoList = new ArrayList<>();
		
		for (Board b : boardLists) {
			dtoList.add(new BoardListResponseDTO(b));
		}
		
		System.out.println(String.format("count: %d, lists: %s", dtoList.size(), dtoList.toString()));
		return dtoList;
		
	}

	public Board retrieve(int boardNo) {
		return mapper.getArticle(boardNo);
		
	}

	public void modifyContent(BoardModifyRequestDTO dto) {
//		Board b = mapper.getArticle(dto.getBoardNo());
//		b.setWriter(dto.getWriter());
//		b.setTitle(dto.getTitle());
//		b.setContent(dto.getContent());
//		
//		mapper.updateArticle(b);
		
		mapper.updateArticle(Board.builder()
								.boardNo(dto.getBoardNo())
								.writer(dto.getWriter())
								.title(dto.getTitle())
								.content(dto.getContent())
								.build());
	}

	public void deleteContent(int boardNo) {
		mapper.deleteArticle(boardNo);
		
	}
	
	
	
}
