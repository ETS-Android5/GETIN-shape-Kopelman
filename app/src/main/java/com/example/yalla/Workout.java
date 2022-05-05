package com.example.yalla;

public class Workout {
    private String name;
    private String type;
    private String countUsers;
    private String level;
    public Workout(){}

    public Workout(String name, String type, String countUsers, String level) {
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

    public String getCountUsers() {
        return countUsers;
    }

    public void setCountUsers(String countUsers) {
        this.countUsers = countUsers;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
