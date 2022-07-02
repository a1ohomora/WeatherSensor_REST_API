package ru.rozvezev.weathersensor.util.exceptions;

import java.time.LocalDateTime;

public class SensorExceptionResponse {

    private String message;

    private String timestamp;

    public SensorExceptionResponse() {}

    public SensorExceptionResponse(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
