package com.tripco.t10.planner;

/**
 * Describes the places to visit in a trip in TFFI format.
 * There may be other attributes of a place, but these are required to plan a trip.
 */
public class Place {
  public String id;
  public String name;
  public double latitude;
  public double longitude;

    /**
     *  Constructor for testing purposes
     */
  public Place(String id, String name, double latitude, double longitude){
     this.id = id;
     this.name = name;
     this.latitude = latitude;
     this.longitude = longitude;
  }

}
