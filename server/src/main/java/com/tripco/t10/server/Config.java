package com.tripco.t10.server;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class Config {

  private short version = 1;
  private String type = "config";

  private List<String> units = Arrays.asList("miles", "kilometers", "nautical miles");
  //temporary array
  private List<String> ports = Arrays.asList("31410", "31407", "31409");

  static String getConfig() {
    Config conf = new Config();
    Gson gson = new Gson();

    return gson.toJson(conf);
  }
}
