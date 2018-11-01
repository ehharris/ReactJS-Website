package com.tripco.t10.planner;

import java.io.*;


public class MapBuilder {

    private Trip trip;
    private String[] lines;
    public String map;

    /** Creates a Map.
     *
     */

    public MapBuilder() {
    }

    /** End of svg map.
     *
     */

    public String end(){
        return "</svg>\n"+"</g>\n"+"</g>\n"+"</svg>";
    }

    private double conLat(double lat){
        return (((41 - lat)/4) * 710);
    }

    private double conLong(double longitude){
        double a = (-(-109 - longitude)/7) * 994;
        return a;
    }

    private void addline(String str, int index){
        lines[index] = str;
    }


    /** Creates lines on the map.
     *
     * @param trip Trip object
     */
    public MapBuilder(Trip trip)
    {
        this.trip = trip;
        this.lines = new String[trip.places.size()];

        if(trip.places.size() > 1) {
            //creates map lines
            for (int i = 0; i < trip.places.size() - 1; i++) {
                double bLat = conLat(this.trip.places.get(i).getLatitude());
                double bLong = conLong(trip.places.get(i).getLongitude());
                double eLat = conLat(trip.places.get(i + 1).getLatitude());
                double eLong = conLong(trip.places.get(i + 1).getLongitude());
                String str = ("<line x1='" + Math.round(bLong) +
                        "' y1='" + Math.round(bLat) +
                        "' x2='" + Math.round(eLong) +
                        "' y2='" + Math.round(eLat) +
                        "' style='stroke:blue; stroke-width:2' />\n");
                addline(str, i);
            }
            //add line to beginning place
            double bLat = conLat(trip.places.get(0).getLatitude());
            double bLong = conLong(trip.places.get(0).getLongitude());
            double eLat = conLat(trip.places.get(trip.places.size() - 1).getLatitude());
            double eLong = conLong(trip.places.get(trip.places.size() - 1).getLongitude());
            String str = ("<line x1='" + Math.round(bLong) +
                    "' y1='" + Math.round(bLat) +
                    "' x2='" + Math.round(eLong) +
                    "' y2='" + Math.round(eLat) +
                    "' style='stroke:blue; stroke-width:2' />\n");
            addline(str, lines.length - 1);
        }
        try {
            File file = new File("./Resources/CObackground.svg");
            FileInputStream in = new FileInputStream(file);
            //svg = new Scanner(file).useDelimiter("<title>Map Layer</title>").next();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < 472; i++) {
                line.append(reader.readLine());
                line.append('\n');
            }

//            FileWriter fileWriter = new FileWriter(file);
//            System.out.println(svg);
            //svg += "<line x1='0' y1='0'x2='200' y2='200' style='stroke:rgb(255,0,0);stroke-width:2' />\n" ;
            if (lines[0] != null) {
                for (int i = 0; i < lines.length; i++) {
                    line.append(lines[i]);
                }
            }
            line.append(this.end()); //+ this.end());
//            fileWriter.flush();
//            fileWriter.close();
            this.map = line.toString();

        } catch (IOException e) {
            System.out.println("Error in MapBuilder : " + e);
        }

        //this.map = "test";

    }


}
