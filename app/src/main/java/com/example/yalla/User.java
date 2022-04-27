package com.example.yalla;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private String password;
    private ArrayList<String> workoutUsed;
    private String favouriteWorkType;

    public User(){}

    public User(String username, String email, String password, ArrayList<String> workoutUsed, String favouriteWorkType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.workoutUsed = workoutUsed;
        this.favouriteWorkType = favouriteWorkType;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.workoutUsed = new ArrayList<String>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getWorkoutUsed() {
        return workoutUsed;
    }

    public void setWorkoutUsed(ArrayList<String> workoutUsed) {
        this.workoutUsed = workoutUsed;
    }

    public String getFavouriteWorkType() {
        return favouriteWorkType;
    }

    public void setFavouriteWorkType(String favouriteWorkType) {
        this.favouriteWorkType = favouriteWorkType;
    }
}
