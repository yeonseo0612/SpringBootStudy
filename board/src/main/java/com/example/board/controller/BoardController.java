package com.example.board.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.model.BoardDTO;
import com.example.board.model.BoardEntity;
import com.example.board.model.ResponseDTO;
import com.example.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("api/board")
@RestController
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;
	
	@GetMapping("/viewBoard")
	public ResponseEntity<?> getAllBoard(){
		
		List<BoardDTO> dtoList = service.getAllBoardList();
		
		if(dtoList == null) {
			return ResponseEntity.ok("데이터가 없습니다.");
		}else {
			return ResponseEntity.ok(dtoList);
		}
	}
	
	@GetMapping("/post/{id}")
	public ResponseEntity<?> detailPost(@PathVariable(name = "id") Long id){
		
		List<BoardDTO> dtoList = service.getdetailPost(id);

		return ResponseEntity.ok(dtoList);
		
	}
	
	
	@PostMapping("/createBoard")
	public ResponseEntity<?> createBoard(@RequestBody BoardDTO dto){
		
		int result = service.create(dto);
		
		if(result == 1) {
			return ResponseEntity.ok("추가 성공");
		}else {
			return ResponseEntity.ok("추가 실패");
		}
	}
	
	@GetMapping("/modifyViewBoard/{id}")
	public ResponseEntity<?> modifyViewBoard(@PathVariable(name = "id") Long id){
	    List<BoardDTO> dtoList = service.getdetailPost(id);
	    
	    if (dtoList != null && !dtoList.isEmpty()) {
	        return ResponseEntity.ok(dtoList.get(0));  // ✅ 첫 번째 게시글만 반환
	    } else {
	        return ResponseEntity.status(404).body("게시글이 존재하지 않습니다.");
	    }
	}
	
	@PutMapping("/modifyBoard/{id}")
	public ResponseEntity<?> modifyBoard(@PathVariable(name = "id") Long id, @RequestBody BoardDTO dto){
		int result =service.modify(id, dto);
		
		
		if(result == 1) {
			return ResponseEntity.ok("수정 성공");
		}else {
			return ResponseEntity.ok("수정 실패");
		}
	}
	
	
	
	@DeleteMapping("/deleteBoard/{id}")
	public ResponseEntity<?> deleteBoard(@PathVariable(name ="id") Long id){
		
		int result = service.delete(id);
		
		if(result == 1) {
			return ResponseEntity.ok("삭제 성공");
		}else {
			return ResponseEntity.ok("삭제 실패");
		}
		
	}
	
}
