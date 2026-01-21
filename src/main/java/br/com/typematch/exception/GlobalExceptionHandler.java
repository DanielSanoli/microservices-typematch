package br.com.typematch.exception;

import br.com.typematch.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String MDC_KEY = "correlationId";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req) {
        log.warn("Bad request: path={} correlationId={} msg={}",
                req.getRequestURI(), MDC.get(MDC_KEY), ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "BAD_REQUEST", ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex, HttpServletRequest req) {
        log.error("Unhandled error: path={} correlationId={}",
                req.getRequestURI(), MDC.get(MDC_KEY), ex);

        return build(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "Erro inesperado.", req.getRequestURI());
    }

    private ResponseEntity<ErrorResponseDTO> build(HttpStatus status, String code, String message, String path) {
        String correlationId = MDC.get(MDC_KEY);
        ErrorResponseDTO body = new ErrorResponseDTO(code, message, Instant.now(), path, correlationId);
        return ResponseEntity.status(status).body(body);
    }
}