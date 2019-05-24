package ro.utcluj.api;

import ro.utcluj.dto.CarDTO;
import ro.utcluj.dto.UserDTO;

import java.util.List;

public interface CarServiceInterface {

   // CarDTO getCarDTO(Integer id);

    CarDTO getCarByNrInmatriculare(String numarInmatriculare);

    CarDTO getCarById(Integer id);

    List<CarDTO> findAll();

    List<CarDTO> findAllCarsOfAClinet(Integer id);

    void insert(String carNumber, String color, Integer id);

    void delete(Integer id);


}
