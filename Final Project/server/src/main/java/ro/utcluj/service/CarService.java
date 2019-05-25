package ro.utcluj.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.utcluj.api.CarServiceInterface;
import ro.utcluj.dto.CarDTO;
import ro.utcluj.enitity.Car;
import ro.utcluj.mapper.CarMapper;
import ro.utcluj.mapper.UserMapper;
import ro.utcluj.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CarService implements CarServiceInterface {

    private CarRepository carRepository;

    private CarMapper carMapper;
    private UserMapper userMapper;
    @Autowired
    UserService userService;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {this.carRepository = carRepository; this.carMapper = carMapper;}

    public CarDTO getCarByNrInmatriculare(String numarInmatriculare){

        if(carRepository.getCarsByNrInmatriculare(numarInmatriculare) != null)
        return carMapper.map(carRepository.getCarsByNrInmatriculare(numarInmatriculare));
        else
           return null;
    }

    public Car getCarByNrInmatriculare2(String numarInmatriculare){
        return carRepository.getCarsByNrInmatriculare(numarInmatriculare);
    }

    public CarDTO getCarById(Integer id){
        return carMapper.map(carRepository.getCarById(id));
    }

    public List<CarDTO> findAll(){
        List<CarDTO> list = new ArrayList<>();
        carRepository.findAll().forEach(car -> list.add(carMapper.map(car)));
        return list;
    }

    public List<CarDTO> findAllCarsOfAClinet(Integer id) {

        List<CarDTO> list = new ArrayList<>();
        carRepository.findAllByClientId(id).forEach(car -> list.add(carMapper.map(car)));
        return list;
    }

    public void insert(String carNumber, String color, Integer id){
        Car car = new Car(color,carNumber,userService.getUser(id));
        carRepository.save(car);
    }

    public void delete(Integer id){
        Car car = carRepository.getCarById(id);
        if(car != null){
            carRepository.delete(car);
        }
    }


}
