package com.korea.dependency.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Coding {
	
//	@Autowired
	private final Computer computer; // 코딩을 하기 위해서는 컴퓨터가 필요하다.
	
//	//객체를 메모리에 올리면서 생성자는 호출이 된다.
//	//이떄 필요한 의존성을 매개변수에 스프링이 주입을 해준다.
//	public Coding(Computer computer) {
//		this.computer = computer;
//	}
	
	
	
}
