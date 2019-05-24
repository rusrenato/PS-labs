package ro.utcluj.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.utcluj.api.WashServiceInterface;
import ro.utcluj.dto.WashDTO;
import ro.utcluj.enitity.User;
import ro.utcluj.enitity.Wash;
import ro.utcluj.mapper.WashMapper;
import ro.utcluj.notification.NotificationService;
import ro.utcluj.repository.WashRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class WashService implements WashServiceInterface {

    private WashRepository washRepository;
    private WashMapper washMapper;

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    @Autowired
    NotificationService notificationService;


    @Autowired
    public WashService(WashRepository washRepository, WashMapper washMapper) {
        this.washRepository = washRepository;
        this.washMapper = washMapper;
    }


    public List<WashDTO> findByCar(Integer id) {
        List<WashDTO> list = new ArrayList<>();

        washRepository.findAllByCar_Client(userService.getUserFromDataBase(id)).forEach(wash -> list.add(washMapper.map(wash)));
        return list;
    }

    public List<WashDTO> findByWorker(Integer id){
        List<WashDTO> list = new ArrayList<>();
        washRepository.findAllByWorkerId(id).forEach(wash -> list.add(washMapper.map(wash)));
        return list;

    }

    public void insert(String numarInmatriculare, String type, String hour, Integer price, String workerName) {
        Wash wash = new Wash(type, price, hour, carService.getCarByNrInmatriculare2(numarInmatriculare), userService.getUserbyUsername(workerName) );
        notificationService.sendMessageToAClient(wash.getWorker().getId() + "","insert " + wash.getHour());
        System.out.println("Din serbice s-a trimis la " + wash.getWorker().getId() + " mesajul insert ");
        washRepository.save(wash);
    }


    public void delete(Integer id){
        Wash wash = washRepository.getWashById(id);
        if(wash != null){
            notificationService.sendMessageToAClient(wash.getCar().getClient().getId() + "", "W" + wash.getHour() + " " + wash.getWorker().getUsername());
            washRepository.delete(wash);
        }
    }

    @Override
    public WashDTO getWashDTO(Integer id) {
        Wash wash = washRepository.getWashById(id);
        return washMapper.map(wash);
    }

    public String getWashString(Integer id){
        return washRepository.getWashById(id).toString();
    }
}
