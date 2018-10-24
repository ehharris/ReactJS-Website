package com.tripco.t10.planner;

/**
 * Contains the id, name, latitude, longitude for a trip in TFFI format.
 */
public class Option {
  public String units;
  public String unitName;
  public int unitRadius;
  public String optimization;

  /**
   * Constructor for testing purposes.
   */
  public Option(String units, int unitRadius, String optimization, String unitName){
    this.units = units;
    this.unitName = unitName;
    this.unitRadius = unitRadius;
    this.optimization = optimization;
  }

}
