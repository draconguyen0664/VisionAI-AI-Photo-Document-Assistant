package com.visionai.common.api;

import com.visionai.document.application.DocumentAccessDenied;
import com.visionai.document.application.DocumentNotFound;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.UUID;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(DocumentNotFound.class) ResponseEntity<ApiError> notFound(DocumentNotFound e, HttpServletRequest r) { return response(HttpStatus.NOT_FOUND, "DOCUMENT_NOT_FOUND", e.getMessage(), r); }
    @ExceptionHandler(DocumentAccessDenied.class) ResponseEntity<ApiError> forbidden(DocumentAccessDenied e, HttpServletRequest r) { return response(HttpStatus.FORBIDDEN, "DOCUMENT_ACCESS_DENIED", e.getMessage(), r); }
    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class}) ResponseEntity<ApiError> invalid(Exception e, HttpServletRequest r) { return response(HttpStatus.UNPROCESSABLE_ENTITY, "INVALID_REQUEST", "Request validation failed", r); }
    private ResponseEntity<ApiError> response(HttpStatus status, String code, String message, HttpServletRequest request) {
        var supplied = request.getHeader("X-Trace-Id");
        var traceId = supplied == null || supplied.isBlank() ? UUID.randomUUID().toString() : supplied;
        return ResponseEntity.status(status).body(new ApiError(code, message, Instant.now(), traceId));
    }
    public record ApiError(String code, String message, Instant timestamp, String traceId) {}
}
