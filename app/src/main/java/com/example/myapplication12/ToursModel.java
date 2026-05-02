package com.example.myapplication12;

public class ToursModel {
    String TourName, Location, Duration, Guide, Focus;

    public ToursModel() {
    }

    public ToursModel(String TourName, String Location, String Duration, String Guide, String Focus) {
        this.TourName = TourName;
        this.Location = Location;
        this.Duration = Duration;
        this.Guide = Guide;
        this.Focus = Focus;
    }

    public String getTourName() {
        return TourName;
    }

    public String getLocation() {
        return Location;
    }

    public String getDuration() {
        return Duration;
    }

    public String getGuide() {
        return Guide;
    }

    public String getFocus() {
        return Focus;
    }
}