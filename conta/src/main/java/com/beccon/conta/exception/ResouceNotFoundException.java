package com.beccon.conta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResouceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4338850455403983833L;

	public ResouceNotFoundException(String exception) {
		super(exception);
	}
	
	
}
