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
    private final SensorService sensorService;

    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> getAll(){
        return measurementRepository.findAll();
    }

    @Transactional
    public void add(Measurement measurement) {
        String name = measurement.getSensor().getName();
        measurement.getSensor().setId(sensorService.getByName(name).get().getId());
        measurementRepository.save(measurement);
    }
}
