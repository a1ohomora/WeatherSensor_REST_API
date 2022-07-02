package ru.rozvezev.weathersensor.util.exceptions;

public class MeasurementExceptionResponse {
    private String message;

    private String timestamp;

    public MeasurementExceptionResponse() {}

    public MeasurementExceptionResponse(String message, String timestamp) {
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
