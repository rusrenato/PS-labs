package ro.utcluj.enitity;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String review;
    private int note;

    @ManyToOne
    @JoinColumn(name = "workerReviewed")
    private User workerReviewed;

    public Review(String review, int note, User workerReviewed) {
        this.review = review;
        this.note = note;
        this.workerReviewed = workerReviewed;
    }

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public User getWorkerReviewed() {
        return workerReviewed;
    }

    public void setWorkerReviewed(User workerReviewed) {
        this.workerReviewed = workerReviewed;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", note=" + note +
                ", worker=" + workerReviewed +
                '}';
    }
}
