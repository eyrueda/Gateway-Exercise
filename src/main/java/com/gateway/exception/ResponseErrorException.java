package com.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Error Found")
public class ResponseErrorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResponseErrorException(String message) {
        super(message);
    }

    public ResponseErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
