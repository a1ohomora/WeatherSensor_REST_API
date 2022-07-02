package ru.rozvezev.weathersensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozvezev.weathersensor.models.Sensor;
import ru.rozvezev.weathersensor.repositories.SensorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> getAll(){
        return sensorRepository.findAll();
    }

    public Optional<Sensor> getByName(String name){
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void add(Sensor sensor) {
        //this.enrich(sensor);
        sensorRepository.save(sensor);
    }

    private void enrich(Sensor sensor){
        sensor.setMeasurements(new ArrayList<>());
    }
}
