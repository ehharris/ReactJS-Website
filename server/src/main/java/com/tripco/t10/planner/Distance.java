package com.tripco.t10.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t10.server.HTTP;
import spark.Request;
import java.util.ArrayList;
import java.lang.Math;


public class Distance {

    private String type;
    private int version;
    private Place origin;
    private Place destination;
    private String units;
    public int distance;

    public int calculateDistance(Place origin, Place destination){

        double x1 = Math.toRadians(origin.latitude);
        double x2 = Math.toRadians(destination.latitude);
        double y = Math.abs(Math.toRadians(destination.longitude) - Math.toRadians(origin.longitude));

        distance = (int)Math.round(Math.atan((Math.sqrt( Math.pow (Math.cos(x2) * Math.sin(y),2)) + Math.pow(( Math.cos(x1) * Math.sin(x2) - Math.sin(x1)* Math.cos(x2) * Math.cos(y) ),2)) / (Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.sin(x2) * Math.cos(y))));
        return distance;

    }



}
