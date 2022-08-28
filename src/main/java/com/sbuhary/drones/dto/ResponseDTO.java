package com.sbuhary.drones.dto;

import lombok.Data;

@Data
public class ResponseDTO<T> {

	private final int status;
	private final String message;
	private final T data;
}
