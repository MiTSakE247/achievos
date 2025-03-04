package com.mdx.achievos.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Centralized Exception Messages & Status Codes
    private static final Map<Class<? extends Exception>, HttpStatus> statusMap = Map.of(
            EntityNotFoundException.class, HttpStatus.NOT_FOUND,
            DuplicateResourceException.class, HttpStatus.CONFLICT,
            BadRequestException.class, HttpStatus.BAD_REQUEST,
            MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST,
            SQLException.class, HttpStatus.INTERNAL_SERVER_ERROR,
            AccessDeniedException.class, HttpStatus.FORBIDDEN
//            BadCredentialsException.class, HttpStatus.UNAUTHORIZED,
//            AccountStatusException.class, HttpStatus.UNAUTHORIZED,
//            ExpiredJwtException.class, HttpStatus.UNAUTHORIZED,
//            SignatureException.class, HttpStatus.UNAUTHORIZED
    );

    private static final Map<Class<? extends Exception>, String> messageMap = Map.of(
            EntityNotFoundException.class, "Requested entity not found.",
            DuplicateResourceException.class, "Duplicate resource exists.",
            BadRequestException.class, "Invalid request.",
            MethodArgumentNotValidException.class, "Validation error.",
            SQLException.class, "Database error occurred.",
            AccessDeniedException.class, "You do not have permission."
//            BadCredentialsException.class, "The username or password is incorrect.",
//            AccountStatusException.class, "The account is locked.",
//            ExpiredJwtException.class, "The JWT token has expired.",
//            SignatureException.class, "The JWT signature is invalid."
    );

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleSQLException(Exception ex)
    {
        logger.error("An error occurred: ", ex);

        HttpStatus status = statusMap.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        String message = ex instanceof MethodArgumentNotValidException
                ? ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError().getDefaultMessage()
                : ex.getMessage();
        String description = messageMap.getOrDefault(ex.getClass(), "An unexpected error occurred.");

        return ResponseEntity.ok(createProblemDetail(status, ex.getMessage(), description));
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String message, String description) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(status, message);
        errorDetail.setProperty("description", description);
        return errorDetail;
    }
}
