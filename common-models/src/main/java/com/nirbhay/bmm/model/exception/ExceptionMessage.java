package com.nirbhay.bmm.model.exception;

import lombok.Data;

@Data
public class ExceptionMessage {

	private String timestamp;
	private int status;
	private String error;
	private String error_description;
	private String message;
	private String path;
}
