package com.example.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.board.model.BoardDTO;
import com.example.board.model.BoardEntity;
import com.example.board.persistance.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	
	public List<BoardDTO> getAllBoardList() {
		
		List<BoardEntity> entityList = boardRepository.findAll();
		
		List<BoardDTO> dtoList = new ArrayList<>();
		
		for(BoardEntity entity : entityList) {
			
			BoardDTO dto = new BoardDTO();
			
			dto.setId(entity.getId());
			dto.setAuthor(entity.getAuthor());
			dto.setContent(entity.getContent());
			dto.setTitle(entity.getTitle());
			dto.setWritingTime(entity.getWritingTime());
			
			dtoList.add(dto);
			
		}
	
		return dtoList;
	}


	public int create(BoardDTO dto) {
		
		BoardEntity entity = new BoardEntity();
		
		entity.setAuthor(dto.getAuthor());
		entity.setContent(dto.getContent());
		entity.setTitle(dto.getTitle());
		entity.setWritingTime(dto.getWritingTime());
		
		try {
			boardRepository.save(entity);
			// TODO Auto-generated method stub
			
			
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	
	}


	public int delete(Long id) {
		Optional<BoardEntity> boardEntity = boardRepository.findById(id);
		
		if(boardEntity.isPresent()) {
			
			boardRepository.deleteById(id);
			
			return 1;
			
		}else {
			return 0;
		}
		
	}


	public List<BoardDTO> getdetailPost(Long id) {
	
		Optional<BoardEntity> boardEntity = boardRepository.findById(id);
		List<BoardDTO> dtoList = new ArrayList<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		if(boardEntity.isPresent()) {
			dto.setAuthor(boardEntity.get().getAuthor());
			dto.setContent(boardEntity.get().getContent());
			dto.setId(boardEntity.get().getId());
			dto.setTitle(boardEntity.get().getTitle());
			dto.setWritingTime(boardEntity.get().getWritingTime());
			
			dtoList.add(dto);
		}
		return dtoList;
	}


	public int modify(Long id, BoardDTO dto) {
	    Optional<BoardEntity> optional = boardRepository.findById(id);
	    if (optional.isPresent()) {
	        BoardEntity entity = optional.get();
	        entity.setTitle(dto.getTitle());
	        entity.setContent(dto.getContent());
	        boardRepository.save(entity); // ✅ save로 반영
	        return 1;
	    }
	    return 0;
	}

}
