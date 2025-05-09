package com.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

import com.example.todo.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	
	//필터 클래스 주입하기
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	@Bean //Bean으로 등록해주는 어노테이션
	protected DefaultSecurityFilterChain securityFilterChain(
				HttpSecurity http) throws Exception{
		
	}
	