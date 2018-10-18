package com.tripco.t10.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t10.server.HTTP;
import spark.Request;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Trip class supports TFFI so it can easily be converted to/from Json by Gson.
 *
 */
public class Trip extends Vincenty {
  // The variables in this class should reflect TFFI.
  public int version;
  public String type;
  public String title;
  public ArrayList<Place> places;
  public Option options;
  public ArrayList<Integer> distances;
  public String map;

    /**
     * Constructor for testing purposes.
     *
     */
  public Trip(int version, String type, String title, ArrayList<Place> places, 
	      Option options, ArrayList<Integer> distances, String map){
	  
      this.version = version;
      this.type = type;
      this.title = title;
      this.places = places;
      this.options = options;
      this.distances = distances;
      this.map = map;
  }

  /** The top level method that does planning.
   * At this point it just adds the map and distances for the places in order.
   * It might need to reorder the places in the future.
   */
  public void plan() {
      if(this.options.optimization.equals("short")) {
          shortOptimization();
      }
      this.map = svg();
      this.distances = legDistances();
  }

    /**
     * Reorders the Places in places array depending on required optimization.
     * @return
     */
    private void shortOptimization() {

        int startCity = 0;
        int nextCity;
        int shortestDistance = 100000000;
        int bestNextCity = -1;
        ArrayList<Place> visited = new ArrayList<>();

        visited.add(this.places.get(startCity));

        //Repeat all steps until places have been rearranged.
        while(visited.size() != this.places.size() ){
            for(nextCity = 0; nextCity < places.size(); nextCity++){
                if(!visited.contains(this.places.get(nextCity))){

                    int distance = calculateDistance(this.places.get(startCity).latitude, this.places.get(nextCity).latitude,
                            this.places.get(startCity).longitude, this.places.get(nextCity).longitude,
                            this.options.units, this.options.unitRadius);

                    if(distance < shortestDistance && distance != 0){
                        //Set new shortest distance and nearestNeighbor
                        shortestDistance = distance;
                        bestNextCity = nextCity;
                    }

                }

            }
            startCity = bestNextCity;
            shortestDistance = 100000;
            visited.add(this.places.get(bestNextCity));

        }

//        for(int i = 0; i < visited.size(); i++){
//            System.out.println("VISITED ARRAY:" + i + " - " + visited.get(i).name);
//        }

        Collections.copy(this.places, visited);

    }

  /**
   * Returns an SVG containing the background and the legs of the trip.
   * @return
   */
  private String svg() {

	// hardcoded example
	return "<svg width=\"1920\" height=\"960\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\"><!-- Created with SVG-edit - http://svg-edit.googlecode.com/ --> <g> <g id=\"svg_4\"> <svg id=\"svg_1\" height=\"960\" width=\"1920\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\"> <g id=\"svg_2\"> <title>Layer 1</title> <rect fill=\"rgb(119, 204, 119)\" stroke=\"black\" x=\"0\" y=\"0\" width=\"1920\" height=\"960\" id=\"svg_3\"/> </g> </svg> </g> <g id=\"svg_9\"> <svg id=\"svg_5\" height=\"480\" width=\"960\" y=\"240\" x=\"480\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\"> <g id=\"svg_6\"> <title>Layer 2</title> <polygon points=\"0,0 960,0 960,480 0,480\" stroke-width=\"12\" stroke=\"brown\" fill=\"none\" id=\"svg_8\"/> <polyline points=\"0,0 960,480 480,0 0,480 960,0 480,480 0,0\" fill=\"none\" stroke-width=\"4\" stroke=\"blue\" id=\"svg_7\"/> </g> </svg> </g> </g> </svg>";
  }

  /**
   * Returns the distances between consecutive places,
   * including the return to the starting point to make a round trip.
   * @return
   */
  private ArrayList<Integer> legDistances() {

	ArrayList<Integer> dist = new ArrayList<Integer>();

	//Calculate distance between each element.
	for(int i = 0; i < this.places.size()-1; i++){

	  double x1 = this.places.get(i).latitude;
	  double x2 = this.places.get(i + 1).latitude;
	  double y1 = this.places.get(i).longitude;
	  double y2 = this.places.get(i + 1).longitude;

	  dist.add(calculateDistance(x1, x2, y1, y2, this.options.units, this.options.unitRadius));
	}

	//Calculate round trip distance.
	double x1 = this.places.get(this.places.size()-1).latitude;
	double x2 = this.places.get(0).latitude;
	double y1 = this.places.get(this.places.size()-1).longitude;
	double y2 = this.places.get(0).longitude;

	dist.add(calculateDistance(x1, x2, y1, y2, this.options.units, this.options.unitRadius));
	  
	return dist;

  }

}
