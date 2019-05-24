package ro.utcluj.api;

import ro.utcluj.dto.WashDTO;

import java.util.List;

public interface WashServiceInterface {

    WashDTO getWashDTO (Integer id);

    String getWashString(Integer id);


    List<WashDTO> findByCar(Integer id);

    List<WashDTO> findByWorker(Integer id);

    void insert(String numarInmatriculare, String type, String hour, Integer price, String workerName);

    void delete(Integer id);
}

