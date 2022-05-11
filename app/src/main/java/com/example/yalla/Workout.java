package com.example.yalla;

public class Workout {
    private String name;
    private String type;
    private String level;
    private String videoId;
    public Workout(){}

    public Workout(String name, String type, String level, String videoId) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.videoId = videoId;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
