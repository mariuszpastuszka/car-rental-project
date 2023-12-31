package com.sda.carrentalproject.repository;

import com.sda.carrentalproject.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByIdAndAvailableTrue(Long aLong);

    Page<Car> findAllByAvailable(boolean available, Pageable pageable);
}
