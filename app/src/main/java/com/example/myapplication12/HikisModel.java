package com.example.myapplication12;

public class HikisModel {
    String HikeName, Difficulty, Duration, Facilities;

    public HikisModel() {
    }

    public HikisModel(String HikeName, String Difficulty, String Duration, String Facilities) {
        this.HikeName = HikeName;
        this.Difficulty = Difficulty;
        this.Duration = Duration;
        this.Facilities = Facilities;

    }

    public String getHikeName() {
        return HikeName;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public String getDuration() {
        return Duration;
    }

    public String getFacilities() {
        return Facilities;
    }


}