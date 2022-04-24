package com.example.yalla;

public class Workout {
    private String name;
    private String type;
    private int countUsers;

    public Workout(){}

    public Workout(String name, String type, int countUsers) {
        this.name = name;
        this.type = type;
        this.countUsers = countUsers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCountUsers() {
        return countUsers;
    }

    public void setCountUsers(int countUsers) {
        this.countUsers = countUsers;
    }

    @Override
    public String toString() {
        return this.name + "," + this.type + "," + this.countUsers;
    }
}
