package ru.rozvezev.weathersensor.dto;

import ru.rozvezev.weathersensor.models.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull(message = "Value field should not be empty")
    @Min(value = -100L, message = "Temperature should be > -100")
    @Max(value = 100L, message = "Temperature should be < 100")
    private Double value;

    @NotNull(message = "Raining field should not be empty")
    private Boolean raining;

    @NotNull(message = "Sensor field should not be empty")
    private SensorDTO sensor;

    public MeasurementDTO() {}

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
