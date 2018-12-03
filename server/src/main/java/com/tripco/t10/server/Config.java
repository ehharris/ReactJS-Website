package com.tripco.t10.server;

import com.google.gson.Gson;
import com.tripco.t10.planner.Filters;
import com.tripco.t10.planner.Optimization;
import java.util.Arrays;
import java.util.List;

public class Config {

  private short version = 4;
  
  private String type = "config";

  private List<String> units = Arrays.asList("miles", "kilometers",
                                             "nautical miles", "user defined");

  private List<Optimization> optimization = Arrays.asList(
      new Optimization("none","The trip is not optimized."),
      new Optimization("short","Nearest neighbor."),
      new Optimization("shorter","2-opt."),
      new Optimization("shortest","3-opt."));

  private List<String> attributes = Arrays.asList("name", "id", "latitude","longitude");

  private Filters[] filters = {
    new Filters("type", (new String[]{"heliport", "small_airport", "seaplane_base", "closed", "balloonport", "medium_airport", "large_airport"})),
    new Filters("country", (new String[]{"Canada", "United States"})),
    new Filters("continents", (new String[]{"Africa", "Antarctica", "Asia", "Europe", "North America", "Oceania", "South America"}))
  };

  private List<String> maps = Arrays.asList("svg", "kml");


  static String getConfig() {
    Config conf = new Config();
    Gson gson = new Gson();

    return gson.toJson(conf);
  }
}
