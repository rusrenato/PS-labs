package ro.utcluj.enitity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String username;
    private String password;
    private Role rol;
    private int wallet;
    private int salary;
    private int bonuses;
    private int numberOfWashedCars;
    private int numberOfBreaks;
    private int rank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car", referencedColumnName = "id")
    private Car car;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Car> clientsCars;

    @OneToMany(mappedBy = "worker",cascade = CascadeType.ALL)
    private Set<Wash> washList;

    public User() {
    }

    public User(String name, String username, String password, Role rol, int wallet, int salary, int bonuses, int numberOfWashedCars, int numberOfBreaks, Car car) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.wallet = wallet;
        this.salary = salary;
        this.bonuses = bonuses;
        this.numberOfWashedCars = numberOfWashedCars;
        this.numberOfBreaks = numberOfBreaks;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public Set<Car> getClientsCars() {
        return clientsCars;
    }

    public void setClientsCars(Set<Car> clientsCars) {
        this.clientsCars = clientsCars;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }

    public int getNumberOfWashedCars() {
        return numberOfWashedCars;
    }

    public void setNumberOfWashedCars(int numberOfWashedCars) {
        this.numberOfWashedCars = numberOfWashedCars;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Wash> getWashList() {
        return washList;
    }

    public void setWashList(Set<Wash> washList) {
        this.washList = washList;
    }

    public int getNumberOfBreaks() {
        return numberOfBreaks;
    }

    public void setNumberOfBreaks(int numberOfBreaks) {
        this.numberOfBreaks = numberOfBreaks;
    }


}
