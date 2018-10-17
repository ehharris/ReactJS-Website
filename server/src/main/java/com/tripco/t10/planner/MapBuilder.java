package com.tripco.t10.planner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapBuilder {
    private String map;


    public MapBuilder()
    {
        this.map = "<title>Map Layer</title>\n";
    }

    public String buildMap(ArrayList places)
    {

        //Use shortestPath list to create map pathGroup
//        for(int i = 0; i < shortestPath.length - 1; i++)
//        {
//            //Grab destinations and set up coordinates for SVG
//            Brewery start = shortestPath[i];
//            Brewery end = shortestPath[(i + 1) % (shortestPath.length - 1)]; //Purpose of this is so when we get to end of list, end wont be outside the array and instead will be the first brewery again
//            String sX = String.valueOf(this.convertLong(start.getLong()));
//            String sY = String.valueOf(this.convertLat(start.getLat()));
//            String eX = String.valueOf(this.convertLong(end.getLong()));
//            String eY = String.valueOf(this.convertLat(end.getLat()));
//            //Construct line tag
//            String id = String.valueOf(i);
//            String[] vals = {"#32cd32", "4", sX, sY, eX, eY, id};
//            //Add tag to pathGroup distance to total and
//            this.addToXML(this.makeLine(vals));
//        }
//        int totalDist = shortestPath[shortestPath.length - 1].getNNDist();
//        String tripLength = "Trip Length: " + String.valueOf(totalDist) + " miles.";
//        String[] distanceVals = {"525", "764", "distance", "24", "Sans-serif", "middle", tripLength};
//        this.addToXML(this.makeText(distanceVals));
//        //End pathGroup
//
//        this.addToXML("</g>\n</svg>\n");
        //Add everything to XML string and write to file
        String svg = "";
        try
        {
            File file = new File("/Resources/COBackground.svg");
            svg = new Scanner(file).useDelimiter("<title>TripCoPath</title>").next();
            //FileWriter fileWriter = new FileWriter(file);
            //fileWriter.write(svg + this.xmlString);
            //fileWriter.flush();
            //fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to generate map SVG. " + e);
        }
        return svg;
    }
}
