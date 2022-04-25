package com.example.yalla;

public class Workout {
    private String name;
    private String type;
    private int countUsers;
    private String level;
    public Workout(){}

    public Workout(String name, String type, int countUsers, String level) {
        this.name = name;
        this.type = type;
        this.countUsers = countUsers;
        this.level = level;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
