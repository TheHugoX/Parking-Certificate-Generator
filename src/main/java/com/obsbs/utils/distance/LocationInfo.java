package com.obsbs.utils.distance;

public class LocationInfo {
    public String City;
    public String GPSPositon;


    public String GetGPSPositon() {
        return GPSPositon;
    }

    public void SetGpsPosition(String GPSPos) {
        this.GPSPositon = GPSPos;
    }

    public LocationInfo(String xmlline) {
        XMLLocation XAMP = new XMLLocation();
        this.GPSPositon = XAMP.GetGPSPos(xmlline);
    }

    public LocationInfo() {

    }

}
