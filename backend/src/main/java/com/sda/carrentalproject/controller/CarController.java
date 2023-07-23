package com.sda.carrentalproject.controller;

import com.sda.carrentalproject.dto.CarDto;
import com.sda.carrentalproject.mapper.CarMapper;
import com.sda.carrentalproject.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class CarController {

    private final CarService carService;

    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    // /cars?available=true?color=blue
    @GetMapping("/cars")
    public List<CarDto> getCars(@RequestParam Map<String, String> queryParams,
                                Pageable pageable) {
        log.info("getting cars");
        log.info("query params: {}", queryParams);
        log.info("paging parameters: [{}]", pageable);

        return carService.findCarsBasedOnQueryParameters(queryParams, pageable)
                .stream()
                .map(car -> carMapper.fromEntityToDto(car))
                .toList();
    }
}
