package ro.utcluj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcluj.enitity.Car;
import ro.utcluj.enitity.User;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    Car getCarById(int id);
    Car getCarsByNrInmatriculare(String numar);
    List<Car> findAllByClientId(Integer id);
    List<Car> findAll();
}
