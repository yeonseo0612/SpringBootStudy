package com.example.todo.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ResponseDTO<T> {

	private String error;
	private List<T> data;
}
