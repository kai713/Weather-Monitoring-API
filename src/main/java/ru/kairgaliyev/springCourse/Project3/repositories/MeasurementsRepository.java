package ru.kairgaliyev.springCourse.Project3.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kairgaliyev.springCourse.Project3.models.Measurements;

import java.util.Optional;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
    Optional<Measurements> findBySensorName(String sensorName);
}
