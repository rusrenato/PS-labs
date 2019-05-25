package ro.utcluj.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private Role rol;
    private Integer wallet;
    private Integer salary;
    private Integer bonuses;
    private Integer numberOfWashedCars;
    private CarDTO car;
    private Integer numberOfBreaks;
    private Double rank;

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getBonuses() {
        return bonuses;
    }

    public void setBonuses(Integer bonuses) {
        this.bonuses = bonuses;
    }

    public Integer getNumberOfWashedCars() {
        return numberOfWashedCars;
    }

    public void setNumberOfWashedCars(Integer numberOfWashedCars) {
        this.numberOfWashedCars = numberOfWashedCars;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public Integer getNumberOfBreaks() {
        return numberOfBreaks;
    }

    public void setNumberOfBreaks(Integer numberOfBreaks) {
        this.numberOfBreaks = numberOfBreaks;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + rol +
                ", wallet=" + wallet +
                ", salary=" + salary +
                ", bonuses=" + bonuses +
                ", numberOfWashedCars=" + numberOfWashedCars +
                ", car=" + car +
                ", numberOfBreaks=" + numberOfBreaks +
                ", rank=" + rank +
                '}';
    }
}
