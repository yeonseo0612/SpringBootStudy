package com.example.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.model.ResponseDTO;
import com.example.todo.model.UserDTO;
import com.example.todo.model.UserEntity;
import com.example.todo.security.TokenProvider;
import com.example.todo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

	
		//userService 생성자 주입하기
		private final UserService service;
		
		//TokenProvider 클래스 주입받기
		private final TokenProvider tokenProvider;
		
		//회원가입
		//로그인을 해야 토큰ㅇ르 주는거지, 회원가입을 했다고 토큰ㅇ르 주는게 아니다
		@PostMapping("/signup")
		public ResponseEntity<?> registerUser(@RequestBody UserDTO dto){
			//요청본문에 포함된 UserDTO 객체를 수신하여 처리한다.
			try {
				UserEntity entity = UserEntity.builder().username(dto.getUsername()).password(dto.getPassword()).build();
				
				UserEntity responseUserEntity = service.create(entity);
				
				//등록된 UserEntity정보를 UserDTO로 변환하여 응답에 사용한다
				UserDTO responseUserDTO = UserDTO.builder().id(responseUserEntity.getId()).username(responseUserEntity.getUsername()).build();
				
				return ResponseEntity.ok(responseUserDTO);
			} catch (Exception e) {
				// TODO: handle exception
				//예외가 발생한 경우, 에러 메시지를 포함한 ResponseDTO객체를 만들어 응답에 보낸다.
				ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
				
				return ResponseEntity.badRequest().body(responseDTO); //badRequest() http 400응답을 생성한다. //에러메시지를 포함한 응답 본문을 반환한다.
			}
			
		}
		
		//Get으로 만들면 브라우저으 ㅣ주소창에 아이디와 비밀번호가 노출됟ㄹ 수 있다.
		@PostMapping("/signin")
		public ResponseEntity<?> authenticate(@RequestBody UserDTO dto){
			//요청 본문으로 전달된 UserDTO의 username과 password을 기반으로 유저를 조회
			UserEntity user = service.getByCredential(dto.getUsername(), dto.getPassword());
			
			//조회된 user가 있으면 
			if(user != null) {
				//인증에 성공한 경유 유저 정보를 UserDTO로 변환하여 응답에 사용한다.
//				final UserDTO responseUserDTO = UserDTO.builder().id(dto.getId()).username(user.getUsername()).build();
				final String token = tokenProvider.create(user);
				
				
				return ResponseEntity.ok().body(dto);
			}else {
				//조회된 유저가 없거나, 인증이 실패한 경우 에러 메시지를 포함한 RespnseDTO를 반환한다.
				ResponseDTO responseDTO = ResponseDTO.builder().error("Login failed").build();
				
				return ResponseEntity.badRequest().body(responseDTO);
			}
		}
		
		
		
		
		
}
