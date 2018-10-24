package com.tripco.t10.planner;

//Use Vincenty Formula to Calculate Distance 
public class Vincenty{
  public int calculateDistance(double[] coordinates, String units, int unitRadius){
  
    //Convert to Radians 
    double x1 = Math.toRadians(coordinates[0]);
    double x2 = Math.toRadians(coordinates[1]);
    double y = Math.abs(Math.toRadians(coordinates[3]) - Math.toRadians(coordinates[2]));

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
