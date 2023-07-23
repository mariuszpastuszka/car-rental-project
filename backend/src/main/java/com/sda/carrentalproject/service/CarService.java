package com.sda.carrentalproject.service;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.exception.WrongCarIdException;
import com.sda.carrentalproject.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CarService {

    private static final String availableKey = "available";

    private static final String colorKey = "color";

    private static final String brandKey = "brand";

    private static final String modelKey = "model";

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car findCarWithId(long id) {
        log.info("trying to find car with id: [{}]", id);

        return carRepository.findById(id)
                .map(car -> {
                    log.info("Found car: [{}]", car);
                    return car;
                })
                .orElseThrow(() -> new WrongCarIdException("No car with given id: [%s]".formatted(id)));
    }

    public Car save(Car carToBook) {
        log.info("trying to save new car: [{}]", carToBook);

        return carRepository.save(carToBook);
    }

    public Car findAvailableCarWithId(long id) {
        log.info("trying to find available car with given id: [{}]", id);
        return carRepository.findByIdAndAvailableTrue(id)
                .map(car -> {
                    log.info("Found available car: [{}]", car);
                    return car;
                })
                .orElseThrow(() -> new WrongCarIdException("Car with given id: [%s] is unavailable!".formatted(id)));
    }

    public Page<Car> findAllCars(Pageable pageable) {
        log.info("trying to find all cars");
        var carResultPage = carRepository.findAll(pageable);
        log.info("number of all cars: [{}]", carResultPage.getNumberOfElements());
        log.debug("all cars on current page: {}", carResultPage.getContent());

        return carResultPage;
    }

    public Page<Car> findAllCarsAvailableForBooking(Pageable pageable) {
        log.info("trying to find all cars available for booking");
        var availableCars = carRepository.findAllByAvailable(true, pageable);
        log.info("number of available cars: [{}]", availableCars.getNumberOfElements());
        log.debug("available cars: {}", availableCars.getContent());

        return availableCars;
    }

    public Page<Car> findRentCars(Pageable pageable) {
        log.info("trying to find rent cars");
        var rentCars = carRepository.findAllByAvailable(false, pageable);
        log.info("number of rent cars: [{}]", rentCars.getNumberOfElements());
        log.debug("rent cars: [{}]", rentCars);

        return rentCars;
    }

    public Page<Car> findCarsBasedOnQueryParameters(Map<String, String> queryParams,
                                                    Pageable pageable) {
        log.info("finding cars based on query parameters: {}", queryParams);
        log.info("paging params: [{}]", pageable);

        Page<Car> result;
        if (!queryParams.containsKey(availableKey)) {
            result = findAllCars(pageable);
        } else {
            boolean available = Boolean.parseBoolean(queryParams.get(availableKey));
            if (available) {
                result = findAllCarsAvailableForBooking(pageable);
            } else {
                result = findRentCars(pageable);
            }
        }

        return result;
    }
}
