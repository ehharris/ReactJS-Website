package com.tripco.t10.planner;

/**
 * Describes the options to apply when planning a trip in TFFI format.
 * At this point we are only using the values provided.
 */
public class Option {
  
  public String units;
  public String unitName;
  public int unitRadius;
  public String optimization;

  /**
   * Constructor for testing purposes.
   */
  public Option(String units, int unitRadius, String optimization){
    this.units = units;
    this.unitRadius = unitRadius;
    this.optimization = optimization;
  }

}
