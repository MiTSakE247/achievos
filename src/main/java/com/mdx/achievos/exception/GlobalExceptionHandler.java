package com.mdx.achievos.exception;

import com.mdx.achievos.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<ProblemDetail>> handleMissingRequestBody(HttpMessageNotReadableException ex) {
        ProblemDetail problemDetail = createProblemDetail(
                HttpStatus.BAD_REQUEST,
                "Required request body is missing.",
                "Please provide a valid request body."
        );

        ApiResponse<ProblemDetail> response = new ApiResponse<>(false, "Invalid request", problemDetail);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ProblemDetail>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage) // Only user-friendly error messages
                .toList();

        ProblemDetail problemDetail = createProblemDetail(HttpStatus.BAD_REQUEST, "Validation failed", String.join(", ", errorMessages));
        ApiResponse<ProblemDetail> response = new ApiResponse<>(false, "Validation error", problemDetail);

        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ProblemDetail>> handleException(Exception ex) {
        logger.error("An error occurred: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());

        HttpStatus status = statusMap.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        String message = ex instanceof MethodArgumentNotValidException
                ? Optional.ofNullable(((MethodArgumentNotValidException) ex).getBindingResult().getFieldError())
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Validation error occurred.")
                : ex.getMessage();

        String description = messageMap.getOrDefault(ex.getClass(), "An unexpected error occurred.");

        ProblemDetail problemDetail = createProblemDetail(status, message, description);
        ApiResponse<ProblemDetail> response = new ApiResponse<>(false, "An error occurred", problemDetail);

        return ResponseEntity.status(status).body(response);
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String message, String description) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(status, message);
        errorDetail.setProperty("description", description);
        return errorDetail;
    }
}
