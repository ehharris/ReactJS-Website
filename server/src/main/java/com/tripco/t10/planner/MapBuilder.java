package com.tripco.t10.planner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapBuilder {

    private Trip trip;
    private String[] lines;
    private String[] circles;
    private int size;
    public String map;

    /** Creates a Map.
     *
     */
    public MapBuilder() {
    }

    /** End of svg map.
     *
     */
    public String end() {
        return "</svg>\n"+"</g>\n"+"</g>\n"+"</svg>";
    }

    /** Converts Latitude to SVG.
     *
     */
    private double conLat(double latitude){
        if(latitude < 0){
            return ((400 * (90 + Math.abs(latitude)))/180);
        }
        else {
            return ((400 * (90 - Math.abs(latitude)))/180);
        }
    }

    /** Converts Longitude to SVG.
     *
     */
    private double conLong(double longitude){
        if (longitude < 0){
            return ((800 * (180 - Math.abs(longitude)))/360);
        }
        else {
            return ((800 * (180 + Math.abs(longitude)))/360);
        }
    }

    /** Adds lines to array of lines
     *
     * @param begLat Beginning Latitude
     * @param begLong Beginning Longitude
     * @param endLat Ending Latitude
     * @param endLong Ending Longitude
     * @param index Index of this.lines
     */
    private void makeAline(double begLat, double begLong, double endLat, double endLong, int index){
        String str = ("<line x1='"
                + Math.round(begLong)
                + "' y1='"
                + Math.round(begLat)
                + "' x2='"
                + Math.round(endLong)
                + "' y2='"
                + Math.round(endLat)
                + "' style='stroke:black; stroke-width:1' />\n");
        this.lines[index] = str;
    }

    /** Sets up the lines on the map.
     *
     */
    private void addLines() {
        //Multiple places
        if (this.size > 1) {
            int first;
            int last;
            for (int index = 0; index < this.size; index++) {

                //Makes trip "round trip"
                if (index == (this.size - 1)) {
                    first = 0;
                    last = index;
                } else {
                    first = index;
                    last = index + 1;
                }

                //Convert Lat/Long to svg
                double begLat = conLat(this.trip.places.get(first).getLatitude());
                double begLong = conLong(this.trip.places.get(first).getLongitude());
                double endLat = conLat(this.trip.places.get(last).getLatitude());
                double endLong = conLong(this.trip.places.get(last).getLongitude());

                //Make a line
                makeAline(begLat,begLong,endLat,endLong,index);

                //Add Circles
                addCircle(begLat, begLong, index);
            }
        }
        //One place
        else if(this.size == 1){
            addCircle(conLat(this.trip.places.get(0).getLatitude()), conLong(this.trip.places.get(0).getLongitude()), 0);
        }
    }

    /** Creates circles on the map.
     *
     * @param latitude Converted Latitude
     * @param longitude Converted Longitude
     * @param index The index of circles
     */
    private void addCircle(double latitude, double longitude, int index){
        String circle = ("<circle cx='"
                + Math.round(longitude)
                + "' cy='"
                + Math.round(latitude)
                + "' r='3' fill='#32CD32' />\n");
        this.circles[index] = circle;
    }


    /** Creates lines on the map.
     *
     * @param trip Trip object
     */
    public MapBuilder(Trip trip) {
        //Instantiate Variables
        this.trip = trip;
        this.size = trip.places.size();
        this.lines = new String[this.size];
        this.circles = new String[this.size];

        //Functions to create lines/circles
        addLines();

        //Write to map
        try {
            InputStream svg = MapBuilder.class.getResourceAsStream("/World_map_with_nations.svg");
            BufferedReader reader = new BufferedReader(new InputStreamReader(svg));
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < 421; i++) {
                line.append(reader.readLine());
                line.append('\n');
            }

            //Add Circles and lines
            for(int i = 0; i < this.lines.length; i++) {
                if (lines[i] != null) {
                    line.append(lines[i]);
                    line.append(circles[i]);
                }
            }

            //Finish it up
            line.append(this.end());
            this.map = line.toString();

        } catch (IOException e) {
            System.out.println("Error in MapBuilder : " + e);
        }
    }
}


