package ru.kairgaliyev.springCourse.Project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kairgaliyev.springCourse.Project3.models.Sensor;
import ru.kairgaliyev.springCourse.Project3.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> getSensor(int id) {
        Optional<Sensor> sensor = sensorRepository.findById(id);
        return sensor;
    }

    public Optional<Sensor> getSensorByName(String name) {
        Optional<Sensor> sensor = sensorRepository.findByName(name);
        return sensor;
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

}
