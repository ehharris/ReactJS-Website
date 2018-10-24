package com.tripco.t10.planner;

/**
 * Contains the id, name, latitude, longitude for a trip in TFFI format.
 */
public class Place {
  public String id;
  public String name;
  public double latitude;
  public double longitude;

    /**
     *  Constructor for testing purposes.
     */
  public Place(String id, String name, double latitude, double longitude){
     this.id = id;
     this.name = name;
     this.latitude = latitude;
     this.longitude = longitude;
  }

}
