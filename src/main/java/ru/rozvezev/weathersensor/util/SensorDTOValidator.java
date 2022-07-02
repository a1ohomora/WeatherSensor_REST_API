package ru.rozvezev.weathersensor.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.rozvezev.weathersensor.dto.SensorDTO;
import ru.rozvezev.weathersensor.models.Sensor;
import ru.rozvezev.weathersensor.service.SensorService;

import java.util.Optional;

@Component
public class SensorDTOValidator implements Validator {

    private final SensorService sensorService;

    public SensorDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SensorDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SensorDTO targer = (SensorDTO) o;
        Optional<Sensor> sensorFromDB = sensorService.getByName(targer.getName());

        //Check if given name is in database.
        if (sensorFromDB.isPresent()){
            errors.rejectValue("name", "", "This sensor name is already exist. Please change name");
        }


    }
}
