package com.tripco.t10.planner;

/**
 * Called from child classes to calculate distance.
 */
public class Vincenty{
    /**
     * Calculates distance using vincenty formula.
     */
  public int calculateDistance(double[] coordinates, String units, Double unitRadius){
  
    //Convert to Radians 
    double x1 = Math.toRadians(coordinates[0]);
    double x2 = Math.toRadians(coordinates[1]);
    double y = Math.abs(Math.toRadians(coordinates[3]) - Math.toRadians(coordinates[2]));

    //Vincenty Formula
    double scalar = Math.atan2(Math.sqrt((Math.pow (Math.cos(x2)
                    * Math.sin(y),2)) + Math.pow(( Math.cos(x1) * Math.sin(x2)
                    - Math.sin(x1)* Math.cos(x2) * Math.cos(y) ),2)), (Math.sin(x1)
                    * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y)));

    double result = 0;

    //Calculate Based on Units.
    if(units.equals("miles")){
        result = 3959 * scalar;
    } else if(units.equals("kilometers")){
        result = 6371 * scalar;
    } else if(units.equals("nautical miles")){
        result = 3440 * scalar;
    } else if(unitRadius != null){
        result = unitRadius * scalar;
    } else {
      System.out.println("ERROR: Invalid");
    }

    return (int)Math.round(result);

  }


}
