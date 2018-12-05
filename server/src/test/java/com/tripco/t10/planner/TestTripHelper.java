package com.tripco.t10.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/*
  This class contains tests for the TripHelper class.
 */
@RunWith(JUnit4.class)
public class TestTripHelper {

    TripHelper tripHelper;
    Trip trip;


    Place A = new Place("a","A",  -60, -40);
    Place B = new Place("b","B",  -60, 40);
    Place C = new Place("c","C",  0, 80);
    Place D = new Place("d","D",  60, 40);
    Place E = new Place("e","E",  60, -40);
    Place F = new Place("f","F",  0, -80);


    @Before
    public void initialize() {
        ArrayList<Place> listOfPlaces = new ArrayList<>();

        listOfPlaces.add(A);
        listOfPlaces.add(B);
        listOfPlaces.add(C);
        listOfPlaces.add(D);
        listOfPlaces.add(E);
        listOfPlaces.add(F);

        Option testOption = new Option("user defined", 3959.0, "none", "miles", "svg");

        int[] route = new int[8];

        boolean[] visited = new boolean[route.length - 1];

        String testMap = "\"<svg width=\\\"1920\\\" height=\\\"960\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\"><!-- Created with SVG-edit - http://svg-edit.googlecode.com/ --> <g> <g id=\\\"svg_4\\\"> <svg id=\\\"svg_1\\\" height=\\\"960\\\" width=\\\"1920\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\"> <g id=\\\"svg_2\\\"> <title>Layer 1</title> <rect fill=\\\"rgb(119, 204, 119)\\\" stroke=\\\"black\\\" x=\\\"0\\\" y=\\\"0\\\" width=\\\"1920\\\" height=\\\"960\\\" id=\\\"svg_3\\\"/> </g> </svg> </g> <g id=\\\"svg_9\\\"> <svg id=\\\"svg_5\\\" height=\\\"480\\\" width=\\\"960\\\" y=\\\"240\\\" x=\\\"480\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\"> <g id=\\\"svg_6\\\"> <title>Layer 2</title> <polygon points=\\\"0,0 960,0 960,480 0,480\\\" stroke-width=\\\"12\\\" stroke=\\\"brown\\\" fill=\\\"none\\\" id=\\\"svg_8\\\"/> <polyline points=\\\"0,0 960,480 480,0 0,480 960,0 480,480 0,0\\\" fill=\\\"none\\\" stroke-width=\\\"4\\\" stroke=\\\"blue\\\" id=\\\"svg_7\\\"/> </g> </svg> </g> </g> </svg>\";\n" + "  }";

        ArrayList<Integer> distances = new ArrayList<>();
        distances.add(0);

        trip = new Trip(4, "trip", "Test", listOfPlaces, testOption, distances, testMap);

        int[][] allDistances = trip.createAllDistancesArray();

        tripHelper = new TripHelper(route, visited, allDistances, trip.places, testOption);
    }


    @Test
    public void testOptimization(){
        ArrayList<Place> testShort = new ArrayList<>();

        testShort.add(A);
        testShort.add(B);
        testShort.add(C);
        testShort.add(D);
        testShort.add(E);
        testShort.add(F);

        boolean[] visited = new boolean[this.trip.places.size() + 1];
        int[] tripIndices = new int[this.trip.places.size() + 1];
        tripHelper.optimization(tripIndices,visited, trip.createAllDistancesArray());

        if(trip.places.size() == testShort.size() && trip.places != null){
            for(Place place: trip.places){
                if(!testShort.contains(place)){
                    assertFalse(false);
                }
            }
        }

    }

    @Test
    public void testTwoOpt(){
        ArrayList<Place> testShorter = new ArrayList<>();

        testShorter.add(A);
        testShorter.add(B);
        testShorter.add(C);
        testShorter.add(D);
        testShorter.add(E);
        testShorter.add(F);

        int[] tripIndices = new int[this.trip.places.size() + 1];
        tripHelper.twoOpt(tripIndices,trip.createAllDistancesArray());

        if(trip.places.size() == testShorter.size() && trip.places != null){
            for(Place place: trip.places){
                if(!testShorter.contains(place)){
                    assertFalse(false);
                }
            }
        }
    }

    @Test
    public void testThreeOpt(){
        ArrayList<Place> testShorter = new ArrayList<>();

        testShorter.add(A);
        testShorter.add(B);
        testShorter.add(C);
        testShorter.add(D);

        int[] tripIndices = new int[this.trip.places.size() + 1];
        tripHelper.threeOpt(tripIndices,trip.createAllDistancesArray());

        if(trip.places.size() == testShorter.size() && trip.places != null){
            for(Place place: trip.places){
                if(!testShorter.contains(place)){
                    assertFalse(false);
                }
            }
        }
    }

    @Test
    public void testTwoOptReverse(){
        int[] route = new int[]{1,2,3};
        int[] testRoute = new int[]{2,1,3};

        tripHelper.twoOptReverse(route,0,1);
        assertArrayEquals(route,testRoute);

    }

    @Test
    public void testCreateVisited(){
        boolean[] visited = new boolean[this.trip.places.size()+1];
        tripHelper.createVisited(visited);

        boolean[] testVisited = new boolean[]{false,false,false,false,false,false,false};
        assertArrayEquals(visited, testVisited);

    }

    @Test
    public void testCreateTripIndices(){
        int[] indices = new int[this.trip.places.size()+1];
        tripHelper.createTripIndices(indices);

        int[] testIndices = new int[]{0,1,2,3,4,5,6};
        assertArrayEquals(indices, testIndices);

    }

    @Test
    public void testDecideOptimization(){
        int route[] = new int[7];
        tripHelper.decideOptimization(route, trip.createAllDistancesArray(),this.trip.options.optimization);
        assertEquals(this.trip.options.optimization, "none");
    }

    @Test
    public void testSetPlaces(){
        int[] route = new int[7];
        tripHelper.createTripIndices(route);
        tripHelper.setPlaces(route);

    }
}
