package ru.kairgaliyev.springCourse.Project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kairgaliyev.springCourse.Project3.models.Measurements;
import ru.kairgaliyev.springCourse.Project3.models.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
