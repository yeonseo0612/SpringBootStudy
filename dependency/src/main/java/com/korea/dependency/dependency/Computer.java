package com.korea.dependency.dependency;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
//코딩은 컴퓨터가 있어야 할 수 있다.
//코딩클래스는 컴퓨터에 의존성이 필요하다.
public class Computer {
	
 int ram = 16;
}
