package domain;

public class Bilet {
    private int id;
    private int id_zbor;
    private int id_user;
    private int price;
    private int seat_nr;

    public Bilet(int id_zbor, int id_user, int price, int seat_nr) {
        this.id_zbor = id_zbor;
        this.id_user = id_user;
        this.price = price;
        this.seat_nr = seat_nr;
    }

    public Bilet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_zbor() {
        return id_zbor;
    }

    public void setId_zbor(int id_zbor) {
        this.id_zbor = id_zbor;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeat_nr() {
        return seat_nr;
    }

    public void setSeat_nr(int seat_nr) {
        this.seat_nr = seat_nr;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "id=" + id +
                ", id_zbor=" + id_zbor +
                ", id_user=" + id_user +
                ", price=" + price +
                ", seat_nr=" + seat_nr +
                '}';
    }
}
