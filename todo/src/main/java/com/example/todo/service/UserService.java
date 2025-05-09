package com.example.todo.service;

import javax.management.RuntimeErrorException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.todo.model.UserEntity;
import com.example.todo.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j 
public class UserService {
	
		//repository 생성자 주입하기
		private final UserRepository userRepository;
		
		
		//유저의 추가
		//회원가입 화면에서 넘어오는 정보
		public UserEntity create(UserEntity entity) {
			//주어진  UsesrEntity가 null이거나 또는 username이 null인 경우 예외를 발생시킨다.
			if(entity == null || entity.getUsername() == null) {
				throw new RuntimeException("Invalid arguments"); 
			}
			//entity에서ㅏ username을 가져온다
			final String username = entity.getUsername();
			
			//주어진 username이 이미 존재하는 경우,경고 로그를 남기고 예외를 던진다
			if(userRepository.existsByUsername(username)) {
				log.warn("Username already exists {}", username);
				throw new RuntimeException("Username already exists");
			}
			//username이 중복되지 않았다면, entity객체를 데이터베이스에 추가
			return userRepository.save(entity);
		}
		
		//주어진 username과 password를 이용해서 entity를 조회한다.
		public UserEntity getByCredential(String username, String password){
			return userRepository.findByUsernameAndPassword(username, password);
		}
		
		
		
}
