package com.example.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor 
@Data // 멤버변수의 getter와 setter 메서드 구현
public class TodoDTO {
	private String id;
	private String title;
	private boolean done;

	public TodoDTO(TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
		}
}
	

