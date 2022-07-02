package ru.rozvezev.weathersensor.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozvezev.weathersensor.models.Measurement;
import ru.rozvezev.weathersensor.repositories.MeasurementRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> getAll(){
        return measurementRepository.findAll();
    }

    @Transactional
    public void add(Measurement measurement) {
        measurementRepository.save(measurement);
    }
}
