package domain;

public class Zbor {
    private int id;
    private String departure;
    private String arrival;
    private String company;

    public Zbor(){

    }

    public Zbor(int id, String departure, String arrival, String company) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arival) {
        this.arrival = arival;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Zbor{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
