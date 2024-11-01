package ru.kairgaliyev.springCourse.Project3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kairgaliyev.springCourse.Project3.models.Measurements;
import ru.kairgaliyev.springCourse.Project3.services.MeasurementsService;
import ru.kairgaliyev.springCourse.Project3.services.SensorService;

@Component
public class MeasurementsValidator implements Validator {
    private SensorService sensorService;
    private MeasurementsService measurementsService;

    @Autowired
    public MeasurementsValidator(SensorService sensorService, MeasurementsService measurementsService) {
        this.sensorService = sensorService;
        this.measurementsService = measurementsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurements.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurements measurements = (Measurements) target;
        measurementsService.enrichMeasurements(measurements);

        if (measurements.getSensor() == null) {
            return;
        }

        if (sensorService.getSensorByName(measurements.getSensor().getName()).isEmpty()) {
            System.out.println(sensorService.getSensorByName(measurements.getSensor().getName()));
            errors.rejectValue("measurements", "", "Sensor fot this measurements not exists");
        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
