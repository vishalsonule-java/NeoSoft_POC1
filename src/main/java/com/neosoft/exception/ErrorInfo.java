package com.neosoft.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorInfo {
	
	private LocalDateTime date;
	private Integer status;
	private String msg;

}
