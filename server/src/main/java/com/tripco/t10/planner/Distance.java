package com.tripco.t10.planner;

import java.lang.Math;

public class Distance extends Vincenty {

    private String type;
    private int version;
    private Place origin;
    private Place destination;
    private String units;
    private int distance;

    //Used in Calculate.java for the distance TFFI.
    public int getDistance(){ return this.distance; }
    public Place getOrigin() { return this.origin; }
    public Place getDestination() { return this.destination; }
    public String getUnits() { return this.units; }
    public void setDistance(int input){ this.distance = input; }

}
