package ru.rozvezev.weathersensor.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rozvezev.weathersensor.dto.SensorDTO;
import ru.rozvezev.weathersensor.models.Sensor;
import ru.rozvezev.weathersensor.service.SensorService;
import ru.rozvezev.weathersensor.util.exceptions.SensorException;
import ru.rozvezev.weathersensor.util.SensorDTOValidator;
import ru.rozvezev.weathersensor.util.exceptions.SensorExceptionResponse;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorDTOValidator sensorDTOValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorDTOValidator sensorDTOValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorDTOValidator = sensorDTOValidator;
    }

    @GetMapping
    public List<SensorDTO> getAllSensors(){
        return sensorService.getAll().stream().map(this::convertToSensorDTO).toList();
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        sensorDTOValidator.validate(sensorDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            bindingResult.getFieldErrors().forEach(e -> msg.append(e.getField())
                                                            .append(" - ")
                                                            .append(e.getDefaultMessage())
                                                            .append("; "));
            throw new SensorException(msg.toString());
        }

        sensorService.add(this.convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(SensorException.class)
    protected ResponseEntity<SensorExceptionResponse> handleSensorException(SensorException e){
        SensorExceptionResponse response = new SensorExceptionResponse(e.getMessage(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
