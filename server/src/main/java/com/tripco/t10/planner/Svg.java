package com.tripco.t10.planner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Svg {

    private Trip trip;
    private String[] lines;
    private String[] circles;
    public String map;

    /**
     * Creates a Map.
     */
    public Svg(Trip trip) {
        this.trip = trip;
    }

    /**
     * End of svg map.
     */
    private String end() {
        return "</svg>\n" + "</g>\n" + "</g>\n" + "</svg>";
    }

    /**
     * Converts Latitude to SVG.
     */
    private double conLat(double latitude) {
        if (latitude < 0) {
            return ((400 * (90 + Math.abs(latitude))) / 180);
        } else {
            return ((400 * (90 - Math.abs(latitude))) / 180);
        }
    }

    /**
     * Converts Longitude to SVG.
     */
    private double conLong(double longitude) {
        if (longitude < 0) {
            return ((800 * (180 - Math.abs(longitude))) / 360);
        } else {
            return ((800 * (180 + Math.abs(longitude))) / 360);
        }
    }

    /**
     * Adds lines to array of lines.
     *
     * @param latLongs  array of lats/longs
     * @param index   Index of this.lines
     */
    private void makeAline(double[] latLongs, int index) {
        String str = ("<line x1='"
                + Math.round(latLongs[1])
                + "' y1='"
                + Math.round(latLongs[0])
                + "' x2='"
                + Math.round(latLongs[3])
                + "' y2='"
                + Math.round(latLongs[2])
                + "' style='stroke:black; stroke-width:1' />\n");
        this.lines[index] = str;
    }

    /**
     * Used for multiple places.
      */
    private void multiPlaces(){
        int first;
        int last;
        for (int index = 0; index < this.trip.places.size(); index++) {

            //Makes trip "round trip"
            if (index == (this.trip.places.size() - 1)) {
                first = 0;
                last = index;
            } else {
                first = index;
                last = index + 1;
            }
            double[] latLongs = new double[4];
            //Convert Lat/Long to svg
            latLongs[0] = conLat(this.trip.places.get(first).latitude);
            latLongs[1] = conLong(this.trip.places.get(first).longitude);
            latLongs[2] = conLat(this.trip.places.get(last).latitude);
            latLongs[3] = conLong(this.trip.places.get(last).longitude);

            //Make a line
            makeAline(latLongs, index);

            //Add Circles
            addCircle(latLongs[0], latLongs[1], first);
            addCircle(latLongs[2], latLongs[3], last);
        }
    }

    /**
     * Sets up the lines on the map.
     */
    private void addLines() {
        //Multiple places
        if (this.trip.places.size() > 1) {
            multiPlaces();
        }
        //One place
        else if (this.trip.places.size() == 1) {
            double latitude = conLat(this.trip.places.get(0).latitude);
            double longitude = conLong(this.trip.places.get(0).longitude);
            addCircle(latitude, longitude, 0);
        }
    }

    /**
     * Creates circles on the map.
     *
     * @param latitude  Converted Latitude
     * @param longitude Converted Longitude
     * @param index     The index of circles
     */
    private void addCircle(double latitude, double longitude, int index) {
        String circle = ("<circle cx='"
                + Math.round(longitude)
                + "' cy='"
                + Math.round(latitude)
                + "' r='3' fill='#1E4D2B' />\n");
        this.circles[index] = circle;
    }

    /**
     * Gets map.
     * @return InputStream of map.
     */
    private InputStream getMap(){
        return Svg.class.getResourceAsStream("/World_map_with_nations.svg");
    }

    /**
     * Draws on the map.
     *
     * @return String of svg map.
     */
    public String svgBuilder() {
        //Instantiate Variables
        this.lines = new String[this.trip.places.size()];
        this.circles = new String[this.trip.places.size()];

        //Functions to create lines/circles
        addLines();

        //Write to map
        try{
            InputStream svg = getMap();
            BufferedReader reader = new BufferedReader(new InputStreamReader(svg));
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < 421; i++) {
                line.append(reader.readLine());
                line.append('\n');
            }

            //Add Circles and lines
            for (int i = 0; i < this.lines.length; i++) {
                if (lines[i] != null) {
                    line.append(lines[i]);
                    line.append(circles[i]);
                } else if (this.trip.places.size() == 1) {
                    line.append(circles[i]);
                }
            }

            //Finish it up
            line.append(this.end());
            return line.toString();
        } catch (IOException e) {
            System.out.println("Error in SvgBuilder : " + e);
        }
        return "Error in Svg.java";
    }

}
