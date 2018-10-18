package com.tripco.t10.planner;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MapBuilder {

    public Trip trip;
    public ArrayList<Place> places;
    public String mapHead;
    public StringBuffer mapBod;
    public String map;

    /** Creates a Map.
     *
     */

    public MapBuilder()
    {

    }

    /** End of svg map.
     *
     */

    public String end(){
        return "</svg>\n"+"</g>\n"+"</g>\n"+"</svg>";
    }

    /** Creates lines on the map.
     *
     * @param trip Trip object
     */

    public MapBuilder(Trip trip)
    {
        this.trip = trip;
        this.mapHead ="<title>Map Layer</title>\n";
        this.places = trip.places;

        try
        {
            FileInputStream in = new FileInputStream("./Resources/CObackground.svg");
            //svg = new Scanner(file).useDelimiter("<title>Map Layer</title>").next();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
//            while (line != null){
//                this.mapBod.append(line);
//                if(line.equals(this.mapHead)){
//                    this.mapBod.append(this.mapHead);
//                    line = null;
//                }
//                else {
//                    line = reader.readLine();
//                }
//
//            }
            //System.out.println(svg);
            //FileWriter fileWriter = new FileWriter(svg);
//            System.out.println(svg);
            //svg += "<line x1='0' y1='0'x2='200' y2='200' style='stroke:rgb(255,0,0);stroke-width:2' />\n" ;

            //fileWriter.write(svg); //+ this.end());
//            fileWriter.flush();
//            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error in MapBuilder : " + e);
        }
        this.map = "test";

    }
}
