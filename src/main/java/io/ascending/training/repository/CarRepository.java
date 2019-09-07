package io.ascending.training.repository;

import io.ascending.training.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
