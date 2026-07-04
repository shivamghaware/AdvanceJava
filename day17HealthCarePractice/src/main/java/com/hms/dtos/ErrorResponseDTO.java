package com.hms.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
	private String message;
	private String status;
	private LocalDateTime errortime;
	
	public ErrorResponseDTO(String message,String status) {
		this.message=message;
		this.status=status;
		this.errortime=LocalDateTime.now();
	}
}
