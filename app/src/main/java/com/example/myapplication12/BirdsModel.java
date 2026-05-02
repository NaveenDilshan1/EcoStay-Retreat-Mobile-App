package com.example.myapplication12;

public class BirdsModel {
    String  Facilities, Time, Guide, Location;

    public BirdsModel() {
    }

    public BirdsModel(String Facilities, String Time, String Guide, String Location) {

        this.Facilities = Facilities;
        this.Time = Time;
        this.Guide = Guide;
        this.Location = Location;
    }



    public String getFacilities() {
        return Facilities;
    }

    public String getTime() {
        return Time;
    }

    public String getGuide() {
        return Guide;
    }

    public String getLocation() {
        return Location;
    }
}