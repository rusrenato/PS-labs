package ro.utcluj.dto;

import java.io.Serializable;

public class WashDTO implements Serializable {
    private int id;
    private String type;
    private int price;
    private String hour;
    private CarDTO car;
    private UserDTO worker;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public UserDTO getWorker() {
        return worker;
    }

    public void setWorker(UserDTO worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "WashDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", hour='" + hour + '\'' +
                ", car=" + car +
                ", worker=" + worker +
                '}';
    }
}
