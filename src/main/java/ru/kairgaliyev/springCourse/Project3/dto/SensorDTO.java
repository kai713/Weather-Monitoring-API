package ru.kairgaliyev.springCourse.Project3.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3, max = 30, message = "must be between 3 and 30")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
