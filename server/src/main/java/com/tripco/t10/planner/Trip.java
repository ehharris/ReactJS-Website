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
        this.map = svg();
        this.distances = legDistances();
    }

    /**
     * Reorders the Places in places array depending on required optimization.
     * @return
     */
    private void shortOptimization() {
        boolean[] visited = new boolean[places.size()];
        int[] tripIndices = new int[places.size() + 1];

        nearestNeighbor(createTripIndices(tripIndices),createVisited(visited),createAllDistancesArray());
    }

    /**
     * Algorithm for nearest neighbor.
     * @return
     */
    private void nearestNeighbor(int[] route, boolean[] visited, int[][] allDistances){
        int currentTotalDistance[] = new int[places.size()];
        int currentBestRoute[] = new int[places.size() + 1];
        int overallBestRouteDistance = 2000000000;

        for(int startCity = 0; startCity < places.size(); startCity++) {
            createVisited(visited);
            createTripIndices(route);

            routeSwap(route, startCity,0);
            route[places.size()] = startCity;

            visited[route[startCity]] = true;

            int routeCounter = 1;

            while(routeCounter < places.size() - 1 ) {

                int bestNextDistance = 2000000000;
                int tempIndex = 0;

                for(int i = routeCounter ; i < places.size(); i++) {
                    if (!visited[(route[i])] && allDistances[route[routeCounter-1]][route[i]] < bestNextDistance) {
                        if (allDistances[route[routeCounter-1]][route[i]] != 0) {
                            bestNextDistance = allDistances[route[routeCounter-1]][route[i]];
                            tempIndex = i;

                        }
                    }

                }

                visited[route[tempIndex]] = true;
                routeSwap(route, tempIndex,routeCounter);
                routeCounter++;

                currentTotalDistance[startCity] += bestNextDistance;

            }

            currentTotalDistance[startCity] += allDistances[route[places.size() - 1]][route[startCity]];

            if(currentTotalDistance[startCity] < overallBestRouteDistance){
                currentBestRoute = route.clone();
                overallBestRouteDistance = currentTotalDistance[startCity];
            }

        }

        ArrayList<Place> optimalNearestNeighbor = new ArrayList<>();
        for(int i = 0; i < places.size(); i++){
            optimalNearestNeighbor.add(places.get(currentBestRoute[i]));
        }

        Collections.copy(this.places, optimalNearestNeighbor);
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
     *
     * HELPER METHODS FOR NEAREST NEIGHBOR AND 2-OPT
     *
     */


    /**
     * Creates an array that has the indices fot the places list.
     */
    private int[] createTripIndices(int[] tripIndices){
        for(int i = 0; i < places.size() + 1;i++) {
            tripIndices[i] = i;

        }
        tripIndices[places.size()] = 0;
        return tripIndices;
    }

    /**
     * Creates an boolean visited array.
     */
    private boolean[] createVisited(boolean[] visited) {
        for(int i = 0; i < places.size();i++) {
            visited[i] = false;
        }
        return visited;
    }

    /**
     * Creates 2d array of all distances for future optimization
     */
    private int[][] createAllDistancesArray(){
        int[][] allDistances = new int [places.size()][places.size()];
        for(int i = 0; i < places.size(); i++){
            for(int j = 0; j < places.size(); j++) {
                double[] coordinates = {places.get(i).latitude,
                    places.get(j).latitude,
                    places.get(i).longitude,
                    places.get(j).longitude};

                allDistances[i][j] = calculateDistance(coordinates, this.options.units, this.options.unitRadius);
            }
        }
        return allDistances;
    }

    /**
     * Swaps indices
     */
    void routeSwap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }



    /**
     * Returns the distances between consecutive places,
     * including the return to the starting point to make a round trip.
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

