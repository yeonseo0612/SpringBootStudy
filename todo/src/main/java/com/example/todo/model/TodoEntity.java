package com.example.todo.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@NoArgsConstructor //엔티티클래스로 지정할때는 매개변수가 없는 생성자가 필요하다.
//내부적으로 Class.newInstace() 과 비슷한 방식으로 객체를 만드는데
//매개변수가 있는 생성자만 있으면 어떤 인자를 넘겨야하는지 몰라서 예외가 발생한다.
@AllArgsConstructor 
@Data // 멤버변수의 getter와 setter 메서드 구현
@Table(name = "Todo")
public class TodoEntity {
	@Id// 엔티티의 기본키(Primary key) 필드를 나타낸다.
	@GeneratedValue(generator="system-uuid")//기본키 값을 자동생성하도록한다.
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	//Hibernage가 제공하는 커스텀 생성 전략을 정의한다.
	//name ="sysyem-uuid" : 이 이름을 @GeneratedValue에서 참조한다.
	//strategy= "uuid" UUID 문자열을 기본키로 생성
	private String id; //이 객체의 id
	private String userId; //이 객체를 생성한 유저의 아이디
	private String title; // Todo의 타이틀
	private boolean done; //todo의 완료 여부
}
