package com.spring.myweb.snsboard.service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.snsboard.dto.SnsBoardRequest;
import com.spring.myweb.snsboard.dto.SnsBoardResponseDTO;
import com.spring.myweb.snsboard.entity.SnsBoard;
import com.spring.myweb.snsboard.mapper.ISnsBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SnsBoardService {

	private final ISnsBoardMapper mapper;

	public void insert(SnsBoardRequest dto) {
		
		//날짜별로 폴더를 생성해서 관리할 예정입니다.(yyyyMMdd)
        //날짜는 LocalDateTime과 DateTimeFormatter를 이용하세요.
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		String strDate = dateTime.format(dtf);
		
        //기본 경로는 C:/test/upload로 사용 하겠습니다.
        String uploadPath = "C:/test/upload";
        
        //폴더가 존재하지 않다면 새롭게 생성해 주시라~
        File uploadFolder = new File(uploadPath + "/" + strDate);
        
        if (!uploadFolder.exists()) {
        	uploadFolder.mkdirs();
        }
        
        //저장될 파일명을 UUID를 이용한 파일명으로 저장합니다.
        //UUID가 제공하는 랜덤 문자열에 -을 제거해서 전부 사용하시면 됩니다.
        UUID uuid = UUID.randomUUID();
        String strUUID = uuid.toString().replace("-", "");
        String fileRealName = dto.getFile().getOriginalFilename();
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."));
        String fileName = strUUID + fileExtension;
        
        //실제 전달된 파일을 지정한 로컬 경로에 전송(transferTo) 하세요.
		try {
        	File saveFile = new File(uploadFolder.getPath() + "/" + fileName);
			dto.getFile().transferTo(saveFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
        
        //DB에 각각의 값을 저장하세요. (INSERT)
        //uploadPath -> "C:/test/upload"
        //fileLoca -> 날짜로 된 폴더명
        //fileName -> 랜덤 파일명
        //fileRealName -> 실제 파일명
        mapper.insert(SnsBoard.builder()
        		.uploadPath(uploadPath)
        		.fileLoca(strDate)
        		.fileName(fileName)
        		.fileRealName(fileRealName)
        		.writer(dto.getWriter())
        		.content(dto.getContent())
        		.build());
		
		
	}

	public List<SnsBoardResponseDTO> getList(int page) {
		List<SnsBoardResponseDTO> dtoList = new ArrayList<>();
		List<SnsBoard> list = mapper.getList(Page.builder()
							.pageNo(page)
							.amount(3)
							.build());
		
		for (SnsBoard board : list) {
			dtoList.add(new SnsBoardResponseDTO(board));
		}
		
		return dtoList;
	}

	public SnsBoardResponseDTO getContent(int bno) {
		SnsBoard board = mapper.getDetail(bno);
		
		return new SnsBoardResponseDTO(board);
		
	}
	
}
