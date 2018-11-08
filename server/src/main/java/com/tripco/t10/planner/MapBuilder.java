package com.tripco.t10.planner;

import java.io.*;
import java.util.ArrayList;


public class MapBuilder {

    private Trip trip;
    private String[] lines;
    private String[] circles;
    private int circleCount;
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

    /** Converts Latitude to SVG
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

    /** Converts Longitude to SVG
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

    /** Creates the lines on the map
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
                String str = ("<line x1='"
                        + Math.round(begLong)
                        + "' y1='"
                        + Math.round(begLat)
                        + "' x2='"
                        + Math.round(endLong)
                        + "' y2='"
                        + Math.round(endLat)
                        + "' style='stroke:black; stroke-width:1' />\n");
                lines[index] = str;

                //Add Circles
                addCircle(begLat, begLong, index);
            }
        }
        //One place
        else if(this.size == 1){
            double Lat = conLat(this.trip.places.get(0).getLatitude());
            double Long = conLong(this.trip.places.get(0).getLongitude());
            addCircle(Lat, Long, 0);
        }

    }

    /** Creates circles on the map.
     *
     * @param Lat Converted Latitude
     * @param Long Converted Longitude
     * @param index The index of circles
     */
    private void addCircle(double Lat, double Long, int index){
        String circle = ("<circle cx='"
                + Math.round(Long)
                + "' cy='"
                + Math.round(Lat)
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
        this.circleCount = 0;

        //Functions to create lines/circles
        addLines();

        //Write to map
        try {
            File file = new File("./Resources/World_map_with_nations.svg");
            FileInputStream in = new FileInputStream(file);
            //svg = new Scanner(file).useDelimiter("<title>Map Layer</title>").next();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < 421; i++) {
                line.append(reader.readLine());
                line.append('\n');
            }

            //Add lines
            for(int i = 0; i < this.lines.length; i++) {
                if (lines[i] != null) {
                    line.append(lines[i]);
                }
            }

            //Add circles
            for(int i = 0; i < this.circles.length; i++){
                if (circles[i] != null) {
                    line.append(circles[i]);
                }
            }

            //Finish it up
            line.append(this.end());
            this.map = line.toString();

        } catch (IOException e) {
            //System.out.println("Error in MapBuilder : " + e);
        }
    }
}
