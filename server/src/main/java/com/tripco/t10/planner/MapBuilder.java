package com.tripco.t10.planner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapBuilder {

    private String mapType;
    public String map;

    /** Creates a Map.
     *
     */
    public MapBuilder() {
    }

    /**Getter for map.
     *
     * @return String of map.
     */
    public String getMap(){
        return this.map;
    }

    /** Creates lines on the map.
     *
     * @param trip Trip object
     */
    public MapBuilder(Trip trip) {
        if(trip.options.map == null || trip.options.map.equals("svg")){
            this.mapType = "svg";
            Svg svg = new Svg(trip);
            this.map = svg.svgBuilder();
        }
        else if(trip.options.map.equals("kml")){
            this.mapType = trip.options.map;
            Kml kml = new Kml(trip);
            this.map = kml.kmlBuilder();
        }
        else {
            this.map = "Unknown map type";
        }
    }
}


