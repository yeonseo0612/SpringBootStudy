package com.example.board.model;

import java.util.List;

import lombok.Builder;

@Builder
public class ResponseDTO <T> {
	private String error;
	private List<T> data;
}
