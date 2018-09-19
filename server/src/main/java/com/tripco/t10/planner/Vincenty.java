package com.tripco.t10.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t10.planner.Distance;
import com.tripco.t10.server.HTTP;
import spark.Request;

//Use Vincenty Formula to Calculate Distance 
public class Vincenty{
  public int calculateDistance(double x1, double x2, double y1, double y2, String units, int unitRadius){
  
    //Convert to Radians 
    x1 = Math.toRadians(x1);
    x2 = Math.toRadians(x2);
    double y = Math.abs(Math.toRadians(y2) - Math.toRadians(y1));

    //Vincenty Formula
    double numerator = Math.sqrt((Math.pow (Math.cos(x2) * Math.sin(y),2)) + Math.pow(( Math.cos(x1) * Math.sin(x2) - Math.sin(x1)* Math.cos(x2) * Math.cos(y) ),2));
    double denominator = (Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y));
    double scalar = Math.atan2(numerator,denominator);

    double e = -1;

    //Calculate Based on Units.
    if(units.equals("miles")){
        e = 3959 * scalar;
    } else if(units.equals("kilometers")){
        e = 6371 * scalar;
    } else if(units.equals("nautical miles")){
        e = 3440 * scalar;
    } else if(units.equals("user defined")){
        e =  unitRadius * scalar;
    } else {
        //TODO: Error Handling
        e = -1;
    }

    return (int)Math.round(e);

  }


}
