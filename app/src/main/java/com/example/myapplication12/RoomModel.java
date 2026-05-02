package com.example.myapplication12;

public class RoomModel {
    String RoomNo,RoomType, NoofBeds, Facility1, Facility2;
    public RoomModel()
    {
    }
    public RoomModel(String RoomNo, String RoomType, String NoofBeds, String Facility1,String Facility2 )
    {
        this.RoomNo = RoomNo;
        this.RoomType = RoomType;
        this.Facility1 = Facility1;
        this.Facility2 = Facility2;
        this.NoofBeds = NoofBeds;
    }
    public String getRoomNo() {
        return RoomNo;
    }

    public String getRoomType() {
        return RoomType;
    }

    public String getNoofBeds() {
        return NoofBeds;
    }

    public String getFacility1() {
        return Facility1;
    }

    public String getFacility2() {
        return Facility2;
    }
}