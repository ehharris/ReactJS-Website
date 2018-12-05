package com.tripco.t10.planner;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Trip(){}
    /**
     * Constructor for testing purposes.
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
            boolean[] visited = new boolean[places.size()];
            int[] route = new int[places.size()+1];
            int[][] allDistances = createAllDistancesArray();

            if(!this.options.optimization.equals("none")){
                TripHelper tripHelper = new TripHelper(route, visited, allDistances,this.places,this.options);
                tripHelper.optimization(route, visited, allDistances);
            }
        } else {
            this.options.optimization = "none";
        }

        this.map = svg();
        this.distances = legDistances();
    }

    /**
     * Calculates Nearest Neighbor.
     */
    void nearestNeighbor(int[] route, boolean[] visited, int[][] allDistances){
        int routeCounter = 1;
        while(routeCounter < route.length-1 ) {
            int bestNextDistance = 2000000000;
            int tempIndex = 0;

            for(int i = routeCounter ; i < route.length - 1; i++) {
                if (!visited[(route[i])] && allDistances[route[routeCounter-1]][route[i]] < bestNextDistance) {
                    bestNextDistance = allDistances[route[routeCounter-1]][route[i]];
                    tempIndex = i;
                }

            }

            visited[route[tempIndex]] = true;
            routeSwap(route, tempIndex,routeCounter);
            routeCounter++;
        }
    }

    /**
     * Returns distances for given indices.
     */
    int calcDistanceForCases(int[] route, int[] indices, int[][]allDistances){
        return (allDistances[route[indices[0]]][route[indices[1]]] + allDistances[route[indices[2]]][route[indices[3]]] + allDistances[route[indices[4]]][route[indices[5]]]);
    }

    /**
     * Swaps sets of numbers within an array.
     */
    void swap(int[] route, int i1, int i2, int j1, int j2){
        int[] copyOfRoute = Arrays.copyOfRange(route, i1, j2+1);

        int[] small;

        int x1, x2, x3, x4;

        if(i2-j1 > j2 -j1){
            x1 = j1;
            x2 = j2;
            x3 = i1;
            x4 = i2;
        }
        else{
            x1 = i1;
            x2 = i2;
            x3 = j1;
            x4 = j2;
        }

        small = Arrays.copyOfRange(route,x1,x2+1);

        int direction = leftOrRight(copyOfRoute, small.length, x1, x3);

        fillArray(copyOfRoute,route,i1,j2);

        if(direction == 0){
            fillShiftLeft(route, small, x4);
        }else{
            fillShiftRight(route,small, x1);
        }

    }

    /**
     *
     * 3-opt Swap Helper Methods
     *
     */

    /**
     * Shifts array right by given number.
     */
    void rightShift(int[] route, int numberOfShifts) {
        for(int i = route.length - 1; i > -1;i--){
            if ( i + numberOfShifts < route.length ){
                route[i + numberOfShifts] = route[i];
            }
        }
    }

    /**
     * Shifts array left by given number.
     */
    void leftShift(int[] route, int numberOfShifts) {
        for(int i = 0; i < route.length;i++){
            if (!(i+numberOfShifts > route.length - 1) ){
                route[i] = route[i + numberOfShifts];
            }
        }
    }

    /**
     * Determines which way to shift.
     */
    int leftOrRight(int[] route, int smallSize, int smallIndex, int bigIndex) {
        if (smallIndex < bigIndex) {
            leftShift(route, smallSize);
            return 0;

        } else {
            rightShift(route, smallSize);
            return 1;
        }

    }

    /**
     * Fills in duplicated numbers after a left shift.
     */
    void fillShiftLeft(int[] route, int[] small, int bigEndIndex) {
        int counter = small.length - 1;
        while(counter >= 0){
            route[bigEndIndex] = small[counter];
            counter--; bigEndIndex--;
        }
    }

    /**
     * Fills in duplicated numbers after a right shift.
     */
    void fillShiftRight(int[] route, int[] small, int bigStartIndex){
        int counter = 0;
        while(counter < small.length){
            route[bigStartIndex] = small[counter];
            counter++; bigStartIndex++;
        }
    }

    /**
     * Fills in array at certain indexes.
     */
    void fillArray(int[] oldRoute, int[] newRoute, int start, int finish){
        int count = 0;
        for(int i = start; i < finish + 1; i++){
            newRoute[i] = oldRoute[count];
            count++;
        }
    }

    /**
     * Returns an SVG containing the background and the legs of the trip.
     */
    String svg() {
        MapBuilder map = new MapBuilder(this);
        return map.map;
    }

    /**
     * Returns the distances between consecutive places,
     * including the return to the starting point to make a round trip.
     */
    ArrayList<Integer> legDistances() {

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

    /**
     *
     * HELPER METHODS FOR NEAREST NEIGHBOR AND 2-OPT
     *
     */

    /**
     * Swaps indices in a route.
     */
    void routeSwap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Calculates total distance for a route.
     */
    int calcTripDistance(int[] route, int[][] allDistances){
        int totalDistance = 0;
        for(int i = 0; i < route.length - 1; i++){
            totalDistance += allDistances[(route[i])][(route[i+1])];
        }
        return totalDistance;
    }

    /**
     * Creates 2d array of all distances for optimization.
     */
    int[][] createAllDistancesArray(){
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

}








