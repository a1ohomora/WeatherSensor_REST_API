package ru.rozvezev.weathersensor.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rozvezev.weathersensor.dto.MeasurementDTO;
import ru.rozvezev.weathersensor.models.Measurement;
import ru.rozvezev.weathersensor.service.MeasurementService;
import ru.rozvezev.weathersensor.util.MeasurementDTOValidator;
import ru.rozvezev.weathersensor.util.SensorDTOValidator;
import ru.rozvezev.weathersensor.util.exceptions.MeasurementException;
import ru.rozvezev.weathersensor.util.exceptions.MeasurementExceptionResponse;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementDTOValidator measurementDTOValidator;

    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper,
                                 MeasurementDTOValidator measurementDTOValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementDTOValidator = measurementDTOValidator;
    }

    @GetMapping
    public List<MeasurementDTO> getAllMeasurements(){
        return measurementService.getAll().stream()
                .map(this::convertToMeasurementDTO)
                .toList();
    }

    @GetMapping("/rainyDaysCount")
    public long getRainyDaysCount(){
        return measurementService.getAll().stream()
                .filter(Measurement::isRaining)
                .count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        measurementDTOValidator.validate(measurementDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            bindingResult.getFieldErrors().forEach(e -> msg.append(e.getField())
                                                            .append(" - ")
                                                            .append(e.getDefaultMessage())
                                                            .append("; "));
            throw new MeasurementException(msg.toString());
        }

        measurementService.add(this.convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/add-batch")
    public void addMeasurements(){
        //TODO: batch request
    }

    @ExceptionHandler(MeasurementException.class)
    protected ResponseEntity<MeasurementExceptionResponse> handleMeasurementException(MeasurementException e){
        MeasurementExceptionResponse response = new MeasurementExceptionResponse(e.getMessage(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

}
