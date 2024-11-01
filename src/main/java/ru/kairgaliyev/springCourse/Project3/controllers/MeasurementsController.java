package ru.kairgaliyev.springCourse.Project3.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kairgaliyev.springCourse.Project3.dto.MeasurementsDTO;
import ru.kairgaliyev.springCourse.Project3.dto.MeasurementsResponse;
import ru.kairgaliyev.springCourse.Project3.models.Measurements;
import ru.kairgaliyev.springCourse.Project3.services.MeasurementsService;
import ru.kairgaliyev.springCourse.Project3.util.MeasureNotCreatedException;
import ru.kairgaliyev.springCourse.Project3.util.MeasurementsErrorResponse;
import ru.kairgaliyev.springCourse.Project3.util.MeasurementsValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final MeasurementsValidator measurementsValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, MeasurementsValidator measurementsValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementsValidator = measurementsValidator;
    }

    @GetMapping
    public MeasurementsResponse getAll(){
//        usually list of objects wrapping by special own object
        return new MeasurementsResponse(measurementsService.getAllMeasurements()
                .stream().map(this::convertToMeasurementsDTO).collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount(){
        return measurementsService.getAllMeasurements().stream().filter(Measurements::isRaining).count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid MeasurementsDTO measurementsDTO, BindingResult bindingResult) {
        measurementsValidator.validate(convertToMeasurements(measurementsDTO), bindingResult);
        if(bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                message.append(" - ").append(fieldError.getDefaultMessage()).append(";");
            }
            throw new MeasureNotCreatedException(message.toString());
        }
        measurementsService.save(convertToMeasurements(measurementsDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementsErrorResponse> handleException(MeasureNotCreatedException ex) {
        MeasurementsErrorResponse errorResponse = new MeasurementsErrorResponse(
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Measurements convertToMeasurements(MeasurementsDTO measurementsDTO) {
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    private MeasurementsDTO convertToMeasurementsDTO(Measurements measurements) {
        return modelMapper.map(measurements, MeasurementsDTO.class);
    }
}
