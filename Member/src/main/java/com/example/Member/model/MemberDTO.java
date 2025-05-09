package com.example.Member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private int age;
	
	//엔티티와 dto 연결
	public MemberDTO(MemberEntity memberEntity) {
		this.id = memberEntity.getId();
		this.age = memberEntity.getAge();
		this.email = memberEntity.getEmail();
		this.name = memberEntity.getName();
	}

}
