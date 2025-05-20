package com.example.Member.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Member.model.MemberDTO;
import com.example.Member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	//1.멤버 추가
	
	@PostMapping("/addMember")
	public ResponseEntity<?> createMember(@RequestBody MemberDTO member){
		
		int result = memberService.createMember(member);
		
		if(result == 1) {
			return ResponseEntity.ok("멤버 저장 완료"); 
		}else {
			return ResponseEntity.ok("멤버 저장 실패");
		}
		
	}
	
	
	//2.모든 멤버 조회
	
	@GetMapping("/viewMember")
	public ResponseEntity<?> getAllMemberList(){
		try {
			List<MemberDTO> dtoList = memberService.getMemberList();
			return ResponseEntity.ok(dtoList);
		} catch (Exception e) {		
			return ResponseEntity.status(404).body(null);
		}
	
	}
	

	//3.멤버 삭제
	
	@DeleteMapping("/deleteMember/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable("id") Long id){
		
		try {
			int result = memberService.deleteMemeber(id);
			
			if(result == 1) {
				return ResponseEntity.ok("삭제 성공"); 
			}else {
				return ResponseEntity.ok("삭제 실패");
			}
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
			// TODO: handle exception
		}
		
	}
	
	//4.멤버 정보 수정
	@PutMapping("/updateMember/{id}")
	public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @RequestBody MemberDTO member){
		
		try {
			
			int result = memberService.updateMember(id, member);
			
			if(result == 1) {
				return ResponseEntity.ok("수정 성공");
			}else if(result == 0) {
				return ResponseEntity.ok("수정할 멤버 데이터가 없습니다.");
				
			}else {
				return ResponseEntity.ok("수정 실패");
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
			
		}
	
	}
	

}
