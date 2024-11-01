package ru.kairgaliyev.springCourse.Project3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kairgaliyev.springCourse.Project3.dto.SensorDTO;
import ru.kairgaliyev.springCourse.Project3.models.Sensor;
import ru.kairgaliyev.springCourse.Project3.services.SensorService;

@Component
public class SensorValidator implements Validator {
    SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if(sensorService.getSensorByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Sensor with name " + sensor.getName() + " already exists");
        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
