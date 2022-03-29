package com.example.yalla;

public class Tweet {
    private String userEmail;
    private String content;

    public Tweet(){}

    public Tweet(String userEmail, String content){
        this.userEmail = userEmail;
        this.content = content;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
