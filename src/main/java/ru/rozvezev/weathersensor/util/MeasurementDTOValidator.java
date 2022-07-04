package ru.rozvezev.weathersensor.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.rozvezev.weathersensor.dto.MeasurementDTO;
import ru.rozvezev.weathersensor.models.Sensor;
import ru.rozvezev.weathersensor.service.SensorService;

import java.util.Optional;

@Component
public class MeasurementDTOValidator implements Validator {

    private final SensorService sensorService;

    public MeasurementDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MeasurementDTO target = (MeasurementDTO) o;

        if (target.getSensor() == null) return;
        Optional<Sensor> sensorFromDB = sensorService.getByName(target.getSensor().getName());

        //Check if given name is not in database. Set identifier if sensor is present!
        if (sensorFromDB.isEmpty())
            errors.rejectValue("sensor", "", "Given sensor is not in database");
    }
}
