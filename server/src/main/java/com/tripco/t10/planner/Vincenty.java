package com.tripco.t10.planner;

//Use Vincenty Formula to Calculate Distance 
public class Vincenty{
  public int calculateDistance(double x1, double x2, double y1, double y2, String units, int unitRadius){
  
    //Convert to Radians 
    x1 = Math.toRadians(x1);
    x2 = Math.toRadians(x2);
    double y = Math.abs(Math.toRadians(y2) - Math.toRadians(y1));

    //Vincenty Formula
    double scalar = Math.atan2(Math.sqrt((Math.pow (Math.cos(x2)
                    * Math.sin(y),2)) + Math.pow(( Math.cos(x1) * Math.sin(x2)
                    - Math.sin(x1)* Math.cos(x2) * Math.cos(y) ),2)), (Math.sin(x1)
                    * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y)));

    double e;

    //Calculate Based on Units.
    if(units.equals("miles")){
        e = 3959 * scalar;
    } else if(units.equals("kilometers")){
        e = 6371 * scalar;
    } else if(units.equals("nautical miles")){
        e = 3440 * scalar;
    } else
        e =  unitRadius * scalar;

    return (int)Math.round(e);

  }


}
