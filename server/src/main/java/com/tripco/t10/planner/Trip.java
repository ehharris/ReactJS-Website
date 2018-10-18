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
   MapBuilder map = new MapBuilder();
   return map.buildMap(this.places);
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
