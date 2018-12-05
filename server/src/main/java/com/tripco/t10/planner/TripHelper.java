package com.tripco.t10.planner;

import java.util.ArrayList;
import java.util.Collections;

public class TripHelper extends Trip {
    int[] route;
    boolean[] visited;
    int[][] allDistances;
    private ArrayList<Place> places;
    Option options;

    public TripHelper(int[] route, boolean[] visited, int[][] allDistances,ArrayList<Place> places ,Option options){
        this.route = route; this.visited = visited;
        this.allDistances = allDistances; this.places = places;
        this.options = options;

    }
    /**
     * Method for all optimizations.
     */
    void optimization(int[] route, boolean[] visited, int[][] allDistances){
        int currentTotalDistance[] = new int[route.length - 1];
        int currentBestRoute[] = new int[route.length];
        int overallBestRouteDistance = 2000000000;

        for(int startCity = 0; startCity < route.length - 1; startCity++) {
            createVisited(visited);
            createTripIndices(route);

            routeSwap(route, startCity,0);
            route[route.length - 1] = startCity;

            visited[startCity] = true;

            nearestNeighbor(route,visited,allDistances);

            decideOptimization(route, allDistances, this.options.optimization);

            currentTotalDistance[startCity] = calcTripDistance(route,allDistances);

            if(currentTotalDistance[startCity] < overallBestRouteDistance){
                currentBestRoute = route.clone();
                overallBestRouteDistance = currentTotalDistance[startCity];
            }
        }

        setPlaces(currentBestRoute);
    }

    /**
     * Helper method for 2opt and 3opt.
     */
    void twoOptReverse(int[] route, int i1, int k){
        while(i1 < k) {
            int temp = route[i1];
            route[i1] = route[k];
            route[k] = temp;
            i1++; k--;
        }
    }

    /**
     * Algorithm for 2opt.
     */
    void twoOpt(int[] route, int[][] allDistances) {
        boolean improvement = true;
        while (improvement) {
            improvement = false;
            for (int i = 0; i <= route.length - 3; i++) {
                for (int k = i + 2; k < route.length - 1; k++) {

                    int x1 = allDistances[route[i]][route[i + 1]];
                    int x2 = allDistances[route[k]][route[k + 1]];
                    int x3 = allDistances[route[i]][route[k]];
                    int x4 = allDistances[route[i + 1]][route[k + 1]];

                    int conditional = -x1 - x2 + x3 + x4;
                    if (conditional < 0) {
                        twoOptReverse(route, i + 1, k);
                        improvement = true;
                    }
                }
            }
        }
    }

    /**
     * Algorithm for 3opt.
     */
    void threeOpt(int[] route, int[][] allDistances){
        boolean improvement = true;

        int n = route.length-1;
        while(improvement){
            improvement = false;
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 1; j < n - 1; j++) {
                    for (int k = j + 1; k < n; k++) {

                        //Cases for distance call
                        int[] case0 = {i,i+1,j,j+1,k,k+1};
                        int[] case1 = {i,k,j+1,j,i+1,k+1};
                        int[] case2 = {i,j,i+1,j+1,k,k+1};
                        int[] case3 = {i,i+1,j,k,j+1,k+1};
                        int[] case4 = {i,j,i+1,k,j+1,k+1};
                        int[] case5 = {i,k,j+1,i+1,j,k+1};
                        int[] case6 = {i,j+1,k,j,i+1,k+1};
                        int[] case7 = {i,j+1,k,i+1,j,k+1};

                        //Case 0
                        int currentDistance = calcDistanceForCases(route, case0, allDistances);

                        //Case 5
                        if(calcDistanceForCases(route,case5,allDistances) < currentDistance){
                            twoOptReverse(route, j+1, k);
                            swap(route, i+1, j, j+1, k);

                            improvement = true;
                        }

                        //Case 6
                        else if(calcDistanceForCases(route,case6, allDistances) < currentDistance){
                            twoOptReverse(route, i+1,j);
                            swap(route, i+1, j, j+1, k);

                            improvement = true;
                        }

                        //Case 7
                        else if(calcDistanceForCases(route,case7,allDistances) < currentDistance){
                            swap(route, i+1, j, j+1, k);

                            improvement = true;
                        }

                        //Case 1
                        else if (calcDistanceForCases(route,case1, allDistances) < currentDistance) {
                            twoOptReverse(route, i+1, k);
                            improvement = true;
                        }

                        //Case 2
                        else if(calcDistanceForCases(route,case2, allDistances)< currentDistance){
                            twoOptReverse(route, i+1, j);
                            improvement = true;
                        }

                        //Case 3
                        else if(calcDistanceForCases(route,case3, allDistances)< currentDistance){
                            twoOptReverse(route, j+1, k);
                            improvement = true;
                        }

                        //Case 4
                        else if(calcDistanceForCases(route,case4,allDistances) < currentDistance){
                            twoOptReverse(route, i+1,j);
                            twoOptReverse(route, j+1, k);
                            improvement = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Decide which optimization to run.
     */
    void decideOptimization(int[] route, int[][] allDistances, String optimization){
        if(optimization.equals("shorter")){
            twoOpt(route,allDistances);
        }

        if(optimization.equals("shortest")){
            threeOpt(route,allDistances);
        }
    }

    /**
     * Creates an boolean visited array.
     */
    boolean[] createVisited(boolean[] visited) {
        for(int i = 0; i < route.length-1;i++) {
            visited[i] = false;
        }
        return visited;
    }

    /**
     * Creates an default trip based on places.
     */
    int[] createTripIndices(int[] tripIndices) {
        for (int i = 0; i < route.length-1; i++) {
            tripIndices[i] = i;
        }
        return tripIndices;
    }

    /**
     * Sets the places array given a int array of indices.
     */
    void setPlaces(int[] currentBestRoute){
        ArrayList<Place> optimalNearestNeighbor = new ArrayList<>();
        for(int i = 0; i < currentBestRoute.length -1; i++){
            optimalNearestNeighbor.add(places.get(currentBestRoute[i]));
        }
        Collections.copy(places, optimalNearestNeighbor);
    }

}

