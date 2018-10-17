package com.tripco.t10.planner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapBuilder {
    private String map;

    /** Creates a Map.
     *
     */
    public MapBuilder()
    {
        this.map = "<title>Map Layer</title>\n";
    }
    /** Creates lines on the map.
     *
     * @param places List of places to map
     */
    public void buildMap(ArrayList places)
    {

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
        //return svg;
    }
}
