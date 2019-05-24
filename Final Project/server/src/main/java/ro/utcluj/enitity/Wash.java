package ro.utcluj.enitity;

import javax.persistence.*;

@Entity
public class Wash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private int price;
    private String hour;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "worker")
    private User worker;

    public Wash() {
    }

    public Wash(String type, int price, String hour, Car car, User worker) {
        this.type = type;
        this.price = price;
        this.hour = hour;
        this.car = car;
        this.worker = worker;
    }

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

    public void setCar(Car car) {
        this.car = car;
    }

    public User getWorker() {
        return worker;
    }

    public Car getCar() {
        return car;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {

        return "Wash{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", hour=" + hour +
                ", car=" + car +
                ", worker=" + worker +
                '}';
    }
}
