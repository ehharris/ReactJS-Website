package com.tripco.t10.planner;

import java.lang.Math;


public class Distance {

    private String type;
    private int version;
    private Place origin;
    private Place destination;
    private String units;
    private int distance;
    
    public int getDistance(){
        return this.distance;
    }

    public void calculateDistance(){


        double x1, x2, y;
        x1 = Math.toRadians(this.origin.latitude);
        x2 = Math.toRadians(this.destination.latitude);
        y = Math.abs(Math.toRadians(this.destination.longitude) - Math.toRadians(this.origin.longitude));

        //Vincenty Formula
        double numerator = Math.sqrt((Math.pow (Math.cos(x2) * Math.sin(y),2)) + Math.pow(( Math.cos(x1) * Math.sin(x2) - Math.sin(x1)* Math.cos(x2) * Math.cos(y) ),2));
        double denominator = (Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y));;
        double scalar = Math.atan2(numerator,denominator);

        double e;
        
        //Check units
        if(this.units.equals("miles")){
            e = 3959 * scalar;
        } else if(this.units.equals("kilometers")){
            e = 6371 * scalar;
        } else {
            e = 3440 * scalar;
        }

        this.distance = (int)Math.round(e);


    }



}
