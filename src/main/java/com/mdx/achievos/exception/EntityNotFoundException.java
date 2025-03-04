package com.mdx.achievos.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApplicationException {
    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
