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

    public int calculateDistance(long x2, long x1, long y){

        distance = (int)Math.round(Math.atan((Math.sqrt( Math.pow (Math.cos(x2) * Math.sin(y),2)) + Math.pow(( Math.cos(x1) * Math.sin(x2) - Math.sin(x1)* Math.cos(x2) * Math.cos(y) ),2)) / (Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.sin(x2) * Math.cos(y))));
        return distance;

    }



}
