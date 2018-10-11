package com.tripco.t10.server;

import com.google.gson.Gson;
import com.tripco.t10.planner.Optimization;

import java.util.Arrays;
import java.util.List;

public class Config {

  private short version = 3;
  private String type = "config";

  private List<String> units = Arrays.asList("miles", "kilometers", "nautical miles", "user defined");
  private List<Optimization> optimization = Arrays.asList(
      new Optimization("none","The trip is not optimized."),
      new Optimization("short","Nearest neighbor."),
      new Optimization("shorter","2-opt."));


  static String getConfig() {
    Config conf = new Config();
    Gson gson = new Gson();

    return gson.toJson(conf);
  }
}
