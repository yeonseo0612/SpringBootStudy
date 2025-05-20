package com.korea.Member.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponsesDTO<T> {
	private String error;
	private List<T> data;
}
