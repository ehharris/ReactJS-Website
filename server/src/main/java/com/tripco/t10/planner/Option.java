package com.tripco.t10.planner;

/**
 * Describes the options to apply when planning a trip in TFFI format.
 * At this point we are only using the values provided.
 */
public class Option {

  public String distance;
  public String optimization;
  
  public String units;
  public String unitName;
  public int unitRadius;

  /**
   * Constructor for testing purposes.
   */
  public Option(String units, int unitRadius){
    this.units = units;
    this.unitRadius = unitRadius;
  }

}
