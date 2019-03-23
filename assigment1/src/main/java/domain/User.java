package domain;

public class User {
    private int id;
    private String nume;
    private String prenume;
    private String username;
    private String parola;
    private String rol;
    private int money;

    public User(String nume, String prenume, String username, String parola, String rol, int money) {
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.rol = rol;
        this.money = money;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
