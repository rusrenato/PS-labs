package ro.utcluj.dto;

import java.io.Serializable;

public class CarDTO implements Serializable {
    private int id;
    private String color;
    private String nrInmatriculare;
    private UserDTO client;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String nrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }

    public UserDTO getClient() {
        return client;
    }

    public void setClient(UserDTO client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", nrInmatriculare='" + nrInmatriculare + '\'' +
                ", client=" + client +
                '}';
    }
}
