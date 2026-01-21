package br.com.typematch.dto;

import java.time.Instant;

public class ErrorResponseDTO {
    private String code;
    private String message;
    private Instant timestamp;
    private String path;
    private String correlationId;

    public ErrorResponseDTO() {}

    public ErrorResponseDTO(String code, String message, Instant timestamp, String path, String correlationId) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
        this.correlationId = correlationId;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
    public Instant getTimestamp() { return timestamp; }
    public String getPath() { return path; }
    public String getCorrelationId() { return correlationId; }

    public void setCode(String code) { this.code = code; }
    public void setMessage(String message) { this.message = message; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public void setPath(String path) { this.path = path; }
    public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }
}