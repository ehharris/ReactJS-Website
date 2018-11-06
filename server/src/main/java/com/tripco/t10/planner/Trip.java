package com.tripco.t10.planner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The Trip class supports TFFI so it can easily be converted to/from Json by Gson.
 *
 */
@SuppressWarnings("unchecked")
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
            boolean[] visited = new boolean[places.size()];
            int[] route = new int[places.size()+1];
            int[][] allDistances = createAllDistancesArray();
            if(this.options.optimization.equals("short")){
                nearestNeighbor(route,visited,allDistances);
            }
            else if (this.options.optimization.equals("shorter")){
                shorterOptimization(visited, allDistances);

            }

        } else {
            this.options.optimization = "none";
        }
        this.map = svg();
        this.distances = legDistances();
    }

//    /**
//     * Algorithm for nearest neighbor.
//     */
//    void nearestNeighbor(boolean[] visited, int[][] allDistances){
//        int currentTotalDistance[] = new int[places.size()];
//        int currentBestRoute[] = new int[places.size()];
//        int overallBestRouteDistance = 2000000000;
//
//        int[] route = new int[places.size()+1];
//
//        for(int startCity = 0; startCity < places.size(); startCity++) {
//            createVisited(visited);
//
//            route[0] = startCity;
//            route[places.size()] = startCity;
//            visited[startCity] = true;
//
//            int routeCounter = 1;
//            int currentCity = startCity;
//            while(routeCounter < places.size() ) {
//
//                int min = 2000000000;
//                int tempIndex = 0;
//                for(int i = 0; i < allDistances[currentCity].length;i++){
//                    if(allDistances[currentCity][i] <= min && !visited[i]){
//                        min = allDistances[currentCity][i];
//                        tempIndex = i;
//
//                    }
//                }
//                currentCity = tempIndex;
//                visited[currentCity] = true;
//
//                route[routeCounter] = currentCity;
//
//                routeCounter++;
//
//            }
//
//            currentTotalDistance[startCity] = calcTripDistance(route,allDistances);
//
//            if(currentTotalDistance[startCity] < overallBestRouteDistance){
//                currentBestRoute = Arrays.copyOf(route,places.size());
//                //currentBestRoute = route.clone();
//                overallBestRouteDistance = currentTotalDistance[startCity];
//            }
//
//        }
//
//        ArrayList<Place> optimalNearestNeighbor = new ArrayList<>();
//        for(int i = 0; i < places.size(); i++){
//            optimalNearestNeighbor.add(places.get(currentBestRoute[i]));
//        }
//        System.out.println("BESTIES: "+overallBestRouteDistance);
//        Collections.copy(this.places, optimalNearestNeighbor);
//    }

    /**
     * Algorithm for nearest neighbor.
     */
    void nearestNeighbor(int[] route, boolean[] visited, int[][] allDistances){
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

            while(routeCounter < places.size() ) {

                int bestNextDistance = 2000000000;
                int tempIndex = 0;

                for(int i = routeCounter ; i < places.size(); i++) {
                    if (!visited[(route[i])] && allDistances[route[routeCounter-1]][route[i]] < bestNextDistance) {
                        bestNextDistance = allDistances[route[routeCounter-1]][route[i]];
                        tempIndex = i;
                    }

                }

                visited[route[tempIndex]] = true;
                routeSwap(route, tempIndex,routeCounter);
                routeCounter++;


            }

            currentTotalDistance[startCity] = calcTripDistance(route,allDistances);

            if(currentTotalDistance[startCity] < overallBestRouteDistance){
                currentBestRoute = route.clone();
                overallBestRouteDistance = currentTotalDistance[startCity];
            }

        }

        ArrayList<Place> optimalNearestNeighbor = new ArrayList<>();
        for(int i = 0; i < places.size(); i++){
            optimalNearestNeighbor.add(places.get(currentBestRoute[i]));
        }
        System.out.println(calcTripDistance(currentBestRoute,allDistances));
        Collections.copy(this.places, optimalNearestNeighbor);
    }


    /**
     * Helper method for 2opt.
     */
    void twoOptReverse(int[] route, int i1, int k){
        while(i1 < k){
            int temp = route[i1];
            route[i1] = route[k];
            route[k] = temp;
            i1++; k--;

        }

    }

    /**
     * Algorithm for 2opt.
     */
    void shorterOptimization(boolean[] visited, int[][] allDistances){
        int currentTotalDistance[] = new int[places.size()];
        int currentBestRoute[] = new int[places.size()];
        int overallBestRouteDistance = 2000000000;

        int[] route = new int[places.size()+1];


        for(int startCity = 0; startCity < places.size(); startCity++) {
            createVisited(visited);

            route[0] = startCity;
            route[places.size()] = startCity;
            visited[startCity] = true;

            int routeCounter = 1;
            int currentCity = startCity;
            while(routeCounter < places.size()) {

                int min = 2000000000;
                int tempIndex = 0;
                for(int i = 0; i < allDistances[currentCity].length;i++){
                    if(allDistances[currentCity][i] <= min && !visited[i]){
                        min = allDistances[currentCity][i];
                        tempIndex = i;

                    }
                }
                currentCity = tempIndex;
                visited[currentCity] = true;

                route[routeCounter] = currentCity;

                routeCounter++;

            }

            boolean improvement = true;
            int count =0;
            while(improvement){
                improvement = false;
                for(int i = 0; i <= route.length - 3; i++){
                    for(int k = i+2; k < route.length -1; k++){

                        int x1 = allDistances[route[i]][route[i+1]];
                        int x2 = allDistances[route[k]][route[k+1]];
                        int x3 = allDistances[route[i]][route[k]];
                        int x4 = allDistances[route[i+1]][route[k+1]];

                        int conditional = - x1 - x2 + x3 + x4;
                        if(conditional < 0){
                            twoOptReverse(route,i+1,k);
                            improvement = true;
                        }
                    }
                    count++;

                }

            }

            currentTotalDistance[startCity] = calcTripDistance(route,allDistances);

            if(currentTotalDistance[startCity] < overallBestRouteDistance){
                currentBestRoute = route.clone();
                overallBestRouteDistance = currentTotalDistance[startCity];
            }

        }

        ArrayList<Place> optimalNearestNeighbor = new ArrayList<>();
        for(int i = 0; i < places.size(); i++){
            optimalNearestNeighbor.add(places.get(currentBestRoute[i]));
        }
        System.out.println(calcTripDistance(currentBestRoute,allDistances));
        Collections.copy(this.places, optimalNearestNeighbor);

    }

    /**
     * Returns an SVG containing the background and the legs of the trip.
     * @return
     */
    String svg() {
        MapBuilder map = new MapBuilder(this);
        return map.map;
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
     * Creates an boolean visited array.
     */
    boolean[] createVisited(boolean[] visited) {
        for(int i = 0; i < places.size();i++) {
            visited[i] = false;
        }
        return visited;
    }

    /**
     * Creates an default trip based on places.
     */
    int[] createTripIndices(int[] tripIndices) {
        for (int i = 0; i < places.size() + 1; i++) {
            tripIndices[i] = i;
        }
        return tripIndices;
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

}


