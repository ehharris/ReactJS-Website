package com.tripco.t10.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t10.server.HTTP;
import spark.Request;
import java.util.ArrayList;

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

	this.map = svg();
	this.distances = legDistances();

  }

  /**
   * Returns an SVG containing the background and the legs of the trip.
   * @return
   */
  private String svg() {

   MapBuilder map = new MapBuilder();
   return map.map;
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
