package com.korea.dependency.dependency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j // 롬복에 있는 콘솔을 쓸 수 있는 어노테이션
public class ComputerTest {
//	@Autowired
//	Coding coding;
	   private Coding coding;

	    // 생성자 주입
//	    @Autowired
//	public ComputerTest(Coding coding) {
//		this.coding = coding;
//	}
	   //세터 주입
	    @Autowired
	    public void setComputer(Coding coding) {
	        this.coding = coding;
	    }
	
	@Test
	public void computerTest() {
	
		//로그에 들어갈 수 있는 내용은 문자열밖에 안된다.
		
		log.info("RAM: " + coding.getComputer().getRam());
	}
}
