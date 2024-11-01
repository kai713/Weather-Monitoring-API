package ru.kairgaliyev.springCourse.Project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kairgaliyev.springCourse.Project3.dto.MeasurementsDTO;
import ru.kairgaliyev.springCourse.Project3.models.Measurements;
import ru.kairgaliyev.springCourse.Project3.models.Sensor;
import ru.kairgaliyev.springCourse.Project3.repositories.MeasurementsRepository;
import ru.kairgaliyev.springCourse.Project3.util.MeasureNotCreatedException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final SensorService sensorService;
    private MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }

    public Measurements getMeasurementsById(int id) {
        Optional<Measurements> measurements = measurementsRepository.findById(id);
        return measurements.orElse(null);
    }

    public Measurements getMeasurementsByName(String name) {
        return measurementsRepository.findBySensorName(name)
                .orElseThrow(() -> new RuntimeException("Measurement with name " + name + " not found"));
    }

    public List<Measurements> getAllMeasurements() {
        return measurementsRepository.findAll();
    }


    @Transactional
    public void save(Measurements measurements) {
        enrichMeasurements(measurements);
        measurementsRepository.save(measurements);
    }

//    @Transactional
//    public void enrichMeasurements(Measurements measurements) {
//        measurements.setSensor(sensorService.getSensorByName(measurements.getSensor().getName()).get());
//        measurements.setMeasurementDateTime(LocalDateTime.now());
//    }

    @Transactional
    public void enrichMeasurements(Measurements measurements) {
        Optional<Sensor> sensorOptional = sensorService.getSensorByName(measurements.getSensor().getName());

        if (sensorOptional.isPresent()) {
            measurements.setSensor(sensorOptional.get());
            measurements.setMeasurementDateTime(LocalDateTime.now());
        } else {
            throw new MeasureNotCreatedException("Sensor with name " + measurements.getSensor().getName() + " not found");
        }
    }



}
