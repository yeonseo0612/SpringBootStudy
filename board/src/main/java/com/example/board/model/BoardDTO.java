package com.example.board.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
	
	private Long id;
	private String title;
	private String author;
	private String writingTime;
	private String content;
	
	public BoardDTO(BoardEntity entity) {
		this.id = entity.getId();
		this.author = entity.getAuthor();
		this.title = entity.getTitle();
		this.writingTime = entity.getWritingTime();
		this.content = entity.getContent();
	}
}
