package com.tripco.t10.planner;

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

      //Make sure optimization attribute was acknowledged.
      if(!(this.options.optimization == null)) {
          if(this.options.optimization.equals("short")) {
              shortOptimization();
          }
      } else {
          this.options.optimization = "none";
      }

      //Return svg if nothing was specified.
      if(this.options.map == null) {
          this.options.map = "svg";
      }

      //TODO: Make call to new map function which then does svg or kml depending on options.
      this.map = svg();
      this.distances = legDistances();
  }

    /**
     * Reorders the Places in places array depending on required optimization.
     * @return
     */
    private void shortOptimization() {
        ArrayList<Place> shortOptOrder = new ArrayList<>();
        shortOptOrder.add(this.places.get(0));

        shortOptOrder = nearestNeighbor(shortOptOrder, 0, -1);
        Collections.copy(this.places, shortOptOrder);

    }

    /**
     * Algorithm for nearest neighbor.
     * @return
     */
    private ArrayList<Place> nearestNeighbor(ArrayList<Place> visited, int startCity, int bestNextCity){
        int shortestDistance = 100000000;

        //Repeat all steps until places have been rearranged.
        while(visited.size() != this.places.size() ){
            for(int nextCity = 0; nextCity < places.size(); nextCity++){
                if(!visited.contains(this.places.get(nextCity))){

                    double[] coordinates = {this.places.get(startCity).latitude,
                            this.places.get(nextCity).latitude,this.places.get(startCity).longitude,
                            this.places.get(nextCity).longitude};
                    int distance = calculateDistance(coordinates, this.options.units, this.options.unitRadius);

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

        return visited;
    }

  /**
   * Returns an SVG containing the background and the legs of the trip.
   * @return
   */
  private String svg() {
   MapBuilder map = new MapBuilder(this);
   return map.map;
}

  /**
   * Returns the distances between consecutive places,
   * including the return to the starting point to make a round trip.
   * @return
   */
  private ArrayList<Integer> legDistances() {

	ArrayList<Integer> dist = new ArrayList<>();

	//Calculate distance between each element.
	for(int i = 0; i < this.places.size()-1; i++){

	  double[] coordinates = {this.places.get(i).latitude,
              this.places.get(i + 1).latitude,
              this.places.get(i).longitude,
              this.places.get(i + 1).longitude};

	  dist.add(calculateDistance(coordinates, this.options.units, this.options.unitRadius));
	}

	//Calculate round trip distance.
	double[] coordinates = {this.places.get(this.places.size()-1).latitude,
                            this.places.get(0).latitude,
                            this.places.get(this.places.size()-1).longitude,
                            this.places.get(0).longitude};

	dist.add(calculateDistance(coordinates, this.options.units, this.options.unitRadius));
	  
	return dist;

  }

}
