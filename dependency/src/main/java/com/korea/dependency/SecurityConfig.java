package com.korea.dependency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	   public SecurityConfig() {
	        System.out.println("🔥 SecurityConfig 로드됨");
	    }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        System.out.println("✅ filterChain 빈 등록 시작");

	        http
	            .authorizeHttpRequests((authz) -> authz
	                .anyRequest().permitAll()
	            )
	            .csrf().disable()
	            .formLogin().disable();

	        return http.build();
	    }
}