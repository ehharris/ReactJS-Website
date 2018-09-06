package com.tripco.t10.planner;

import java.lang.Math;


public class Distance {

    private String type;
    private int version;
    private Place origin;
    private Place destination;
    private String units;
    private int distance;

    //TODO: possible to do conversion from mi - km here
    //Constructor makes the distance object values

    public int getDistance(){
        return this.distance;
    }

    public void calculateDistance(){

        double x1, x2, y;
        x1 = Math.toRadians(this.origin.latitude);
        x2 = Math.toRadians(this.destination.latitude);
        y = Math.abs(Math.toRadians(this.destination.longitude) - Math.toRadians(this.origin.longitude));
        //TODO: Fix and make more readable
        this.distance = (int)Math.round(Math.atan((Math.sqrt( Math.pow (Math.cos(x2) * Math.sin(y),2)) + Math.pow(( Math.cos(x1) * Math.sin(x2) - Math.sin(x1)* Math.cos(x2) * Math.cos(y) ),2)) / (Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.sin(x2) * Math.cos(y))));
    }



}
