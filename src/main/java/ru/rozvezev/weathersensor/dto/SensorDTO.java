package ru.rozvezev.weathersensor.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotNull(message = "Enter name please")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 50, message = "Name length should be between 3 and 50 characters")
    private String name;

    public SensorDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
