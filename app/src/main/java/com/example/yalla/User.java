package com.example.yalla;

import java.util.ArrayList;

public class User {
    private String email;
    private ArrayList<String> lastworkoutUsed;
    private String fullname;
    private String totalKM;

    public User() {
    }

    public User(String email, ArrayList<String> lastworkoutUsed, String fullname, String totalKM) {
        this.email = email;
        this.lastworkoutUsed = lastworkoutUsed;
        this.fullname = fullname;
        this.totalKM = totalKM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getLastworkoutUsed() {
        return lastworkoutUsed;
    }

    public void setLastworkoutUsed(ArrayList<String> lastworkoutUsed) {
        this.lastworkoutUsed = lastworkoutUsed;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTotalKM() {
        return totalKM;
    }

    public void setTotalKM(String totalKM) {
        this.totalKM = totalKM;
    }
}