package com.example.yalla;

import java.util.ArrayList;

public class User {
    private String email;
    private ArrayList<String> workoutUsed;
    private String favouriteWorkType;
    private String fullname;

    public User(){}

    public User(String email, ArrayList<String> workoutUsed, String favouriteWorkType, String fullname) {
        this.email = email;
        this.workoutUsed = workoutUsed;
        this.favouriteWorkType = favouriteWorkType;
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

