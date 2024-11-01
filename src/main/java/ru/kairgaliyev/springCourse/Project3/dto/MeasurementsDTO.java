package ru.kairgaliyev.springCourse.Project3.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.kairgaliyev.springCourse.Project3.models.Measurements;
import ru.kairgaliyev.springCourse.Project3.models.Sensor;

import java.util.List;

public class MeasurementsDTO {
    @Min(value = -100, message = "Value must be greater than or equal to -100")
    @Max(value = 100, message = "Value must be less than or equal to 100")
    @NotNull
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull
    Sensor sensor;

    // Getters and Setters
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public @NotNull Sensor getSensor() {
        return sensor;
    }

    public void setSensor(@NotNull Sensor sensor) {
        this.sensor = sensor;
    }
}
