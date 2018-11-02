package com.tripco.t10.planner;

/**
 *  Options to apply when planning a trip in TFFI format.
 */
public class Option {
  
  public String units;
  public String unitName;
  public int unitRadius;
  public String optimization;
  public String map;

  /**
   * Constructor for testing purposes.
   */
  public Option(String units, int unitRadius, String optimization, String unitName, String map){
    this.units = units;
    this.unitName = unitName;
    this.unitRadius = unitRadius;
    this.optimization = optimization;
    this.map = map;
  }


}
