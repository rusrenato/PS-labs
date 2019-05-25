package ro.utcluj.dto;

import java.io.Serializable;

public class ReviewDTO implements Serializable {
    private Integer id;
    private String review;
    private Integer note;
    private UserDTO worker;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public UserDTO getWorker() {
        return worker;
    }

    public void setWorker(UserDTO worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", note=" + note +
                ", worker=" + worker +
                '}';
    }
}
