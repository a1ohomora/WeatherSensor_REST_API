package ru.rozvezev.weathersensor.dto;

import ru.rozvezev.weathersensor.models.Sensor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull(message = "Value field should not be empty")
    @Min(value = -100L, message = "Temperature should be > -100")
    @Max(value = 100L, message = "Temperature should be < 100")
    private Double value;

    @NotNull(message = "Raining field should not be empty")
    private Boolean raining;

    @NotNull(message = "Sensor field should not be empty")
    private Sensor sensor;

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
