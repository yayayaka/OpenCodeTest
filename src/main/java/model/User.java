package model;

public class User {

    public User() {
    }

    public User(int id, String name, String password, int roundsTotal, int stepsTotal) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roundsTotal = roundsTotal;
        this.stepsTotal = stepsTotal;
    }

    private int id;
    private String name;
    private String password;
    private int roundsTotal;

    public int getRoundsTotal() {
        return roundsTotal;
    }

    public void setRoundsTotal(int roundsTotal) {
        this.roundsTotal = roundsTotal;
    }

    public int getStepsTotal() {
        return stepsTotal;
    }

    public void setStepsTotal(int stepsTotal) {
        this.stepsTotal = stepsTotal;
    }

    private int stepsTotal;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
