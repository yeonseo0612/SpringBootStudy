package com.example.todo.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.todo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class TokenProvider {
	
	//비밀키
	private static final String SECRET_KEY = "cf9f459886a900e5b4784323dc9372b339e56b513da7ba4a061a4e4e6074df40d7a463985ebf5d62199e60660948c40245ba82d778851538ad3fa2bbc917fc33ed411d94a5b3e2fe56411bd7f1545fdf44416d5830016a9198073f4d8397f177e00b1954d1a0dee855e5de1ae0532a16f0a2acb7bed3afbfaa22522528abe6f80e726e99dea39ff63278c250c96123b57b94b8bd00656297bcd0ada53c817e95b2cb8b112c518f2d60f7bdf62a65bc81639da0269a488e1b23bc3b3f6199983a529993d3df93e9512a4728866a497e6793c28301a26688ed14d209884202ebeea39fc6b3920b2e0112a44d1f78e403244777f42ed6c6fbcbf47d7653de20306e7229f7ee36c353d3e483631423641aa2a20d0c332788b0a14a7153c834a66efe455a896ddd0d74a427a7329b813019d2b66ed27a20cf8ba48d99f6df9e1069433aa4a01b888a6ae5bc16ec7d2c23396d58298d33c40ee6e95c9d401e565b08949f8b3b1bf93b722dfec1da548816b530d5e0e5dad01dae8a96f50b5848eeaa485a4cc287c20431378aa3ece37400547f3bb7d5cd4f3ccfa0aeb9c6e1939e4c67c07944a31a20f83a93160945e1a387488a4aad4e92bb943f0ab09f1eb744c470b7cf24ba9ce8187184047ba76a6827e8ec1da59e695ac12450510529bbf3ce263d524366fae083f13f4eb547f7da9192d6e88a2a129d141d93cb3e20d3d25b18";

	
	//토큰을 만드는 메서드
	public String create(UserEntity userEntity) {
		// 기한 지금으로부터 1일로 설정
		//토큰 만료시간을 설정
		//현재 시각 + 1일
		//Instant 클래스 : 타임스탬프로 찍는다.
		Date expiryDate = Date.from(
						Instant.now()
						.plus(1, ChronoUnit.DAYS));

		/*
		{ // header
		  "alg":"HS512"
		}.
		{ // payload
		  "sub":"40288093784915d201784916a40c0001",
		  "iss": "demo app",
		  "iat":1595733657,
		  "exp":1596597657
		}.
		// SECRET_KEY를 이용해 서명한 부분
		Nn4d1MOVLZg79sfFACTIpCPKqWmpZMZQsbNrXdJJNWkRv50_l7bPLQPwhMobT4vBOG6Q3JYjhDrKFlBSaUxZOg
		 */
		// JWT Token 생성
		return Jwts.builder()
						// header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
						.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
						// payload에 들어갈 내용
						.setSubject(userEntity.getId()) // sub 클레임 : 사용자 고유 ID
						.setIssuer("demo app") // iss 클레임 : 토큰 발급
						.setIssuedAt(new Date()) // iat 클레임 : 발급 시각
						.setExpiration(expiryDate) // exp 클레임 : 만료 시각
						.compact(); // 최종 직렬화된 토큰 문자열 반환
	}
	
	public String validateAndGetUserId(String token) {
		// parseClaimsJws메서드가 Base 64로 디코딩 및 파싱.
		// 즉, 헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용 해 서명 후, token의 서명 과 비교.
		// 위조되지 않았다면 페이로드(Claims) 리턴
		// 그 중 우리는 userId가 필요하므로 getBody를 부른다.
		Claims claims = Jwts.parser()
						.setSigningKey(SECRET_KEY) //서명 검증용 키 설정
						.parseClaimsJws(token) //토큰 파싱 및 서명 검증
						.getBody(); //내부 페이로드(Claims)획득

		return claims.getSubject();
	}

}
