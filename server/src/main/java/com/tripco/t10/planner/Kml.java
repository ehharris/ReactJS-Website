package com.tripco.t10.planner;

public class Kml {
    private Trip trip;

    public Kml(Trip trip){
        this.trip = trip;
    }

    public String kmlBuilder(){
        return "This will be a kml string";
    }
}
