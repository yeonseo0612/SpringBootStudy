package com.korea.Member.controller;

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

import com.korea.Member.model.MemberDTO;

import com.korea.Member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/members")
	public ResponseEntity<?> createMember(@RequestBody MemberDTO dto){
		
		int result = memberService.createMem(dto);
		
		if(result == 1) {
			return ResponseEntity.ok("멤버 추가 성공");
		}else {
			return ResponseEntity.ok("멤버 추가 실패");
		}
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getMemberAllList(){
		
		List<MemberDTO> dtoList = memberService.getAllMember();
		
		if(dtoList == null) {
			return ResponseEntity.ok("회원 데이터가 없습니다.");
		}else {
			return ResponseEntity.ok(dtoList);
		}
	}
	
	@GetMapping("/members/{email}")
	public ResponseEntity<?> getFilterMember(@PathVariable(name = "email") String email){
		
		List<MemberDTO> dtoList = memberService.getFindByEmailMember(email);
		
		if(dtoList == null) {
			return ResponseEntity.ok("해당하는 이메일의 회원 데이터가 없습니다.");
		}else {
			return ResponseEntity.ok(dtoList);
		}
	}
	
	@PutMapping("/members/{email}/password")
	public ResponseEntity<?> modifyPassword(@PathVariable(name = "email") String email, @RequestBody MemberDTO dto){
		
		int result = memberService.modifyPwd(email, dto);
		
		if(result == 1) {
			return ResponseEntity.ok("비밀번호 변경 성공");
		}else {
			return ResponseEntity.ok("비밀번호 변경 실패");
		}
		
	}
	
	
	
	@DeleteMapping("members/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable(name = "id") int id){
		
		int result = memberService.deleteMember(id);
		
		if(result == 1) {
			return ResponseEntity.ok("멤버 삭제 성공");
		}else {
			return ResponseEntity.ok("멤버 삭제 실패");
		}
	}
		
	
}
	

