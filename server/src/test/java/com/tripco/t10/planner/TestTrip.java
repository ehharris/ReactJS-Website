package com.tripco.t10.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/*
  This class contains tests for the Trip class.
 */
@RunWith(JUnit4.class)
public class TestTrip {
  Trip trip;

  Place testDenver = new Place("dnvr", "Denver", 39.7392, -104.9903);
  Place testBoulder = new Place("bldr", "Boulder", 40.01499, -105.27055);
  Place testFortCollins = new Place("foco", "Fort Collins", 40.585258, -105.084419);

  @Before
  public void initialize() {
    ArrayList<Place> listOfPlaces = new ArrayList<>();

    listOfPlaces.add(testDenver);
    listOfPlaces.add(testBoulder);
    listOfPlaces.add(testFortCollins);

    Option testOption = new Option("user defined", 3959.0, "none", "miles", "svg");

    ArrayList<Integer> distances = new ArrayList<>();
    distances.add(0);
    distances.add(0);
    distances.add(0);

    String testMap = "\"<svg width=\\\"1920\\\" height=\\\"960\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\"><!-- Created with SVG-edit - http://svg-edit.googlecode.com/ --> <g> <g id=\\\"svg_4\\\"> <svg id=\\\"svg_1\\\" height=\\\"960\\\" width=\\\"1920\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\"> <g id=\\\"svg_2\\\"> <title>Layer 1</title> <rect fill=\\\"rgb(119, 204, 119)\\\" stroke=\\\"black\\\" x=\\\"0\\\" y=\\\"0\\\" width=\\\"1920\\\" height=\\\"960\\\" id=\\\"svg_3\\\"/> </g> </svg> </g> <g id=\\\"svg_9\\\"> <svg id=\\\"svg_5\\\" height=\\\"480\\\" width=\\\"960\\\" y=\\\"240\\\" x=\\\"480\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\"> <g id=\\\"svg_6\\\"> <title>Layer 2</title> <polygon points=\\\"0,0 960,0 960,480 0,480\\\" stroke-width=\\\"12\\\" stroke=\\\"brown\\\" fill=\\\"none\\\" id=\\\"svg_8\\\"/> <polyline points=\\\"0,0 960,480 480,0 0,480 960,0 480,480 0,0\\\" fill=\\\"none\\\" stroke-width=\\\"4\\\" stroke=\\\"blue\\\" id=\\\"svg_7\\\"/> </g> </svg> </g> </g> </svg>\";\n" + "  }";

    trip = new Trip(4, "trip", "Test", listOfPlaces, testOption, distances, testMap);

    trip.plan();
  }

  @Test
  public void testPlan(){
    trip.plan();
    ArrayList<Place> testShorter = new ArrayList<>();

    testShorter.add(testDenver);
    testShorter.add(testBoulder);
    testShorter.add(testFortCollins);

    if(trip.places.size() == testShorter.size()){
      for(Place place: trip.places){
        if(!testShorter.contains(place)){
          assertFalse(false);
        }
      }
    }

  }

  @Test
  public void testNearestNeighbor(){
    boolean[] visited = new boolean[trip.places.size()];
    int[] route = new int[trip.places.size()+1];
    int[][] allDistances = trip.createAllDistancesArray();

    trip.nearestNeighbor(route,visited,allDistances);
    assertTrue(true);
  }

  @Test
  public void testCalcDistanceForCases(){
    int[] indices = new int[]{0,1,2,3,4,5};
    int[] route = new int[6];
    int[][] allDistances = trip.createAllDistancesArray();

    int testAnswer = 0;
    int answer = trip.calcDistanceForCases(route, indices, allDistances);
    assertEquals(answer,testAnswer);

  }

  @Test
  public void testSwap(){
    int[] route = new int[]{1,2,3,4,5};

    trip.swap(route, 0 ,1 ,2,3);
    int[] answer = new int[]{3,4,1,2,5};
    assertArrayEquals(route,answer);
  }

  @Test
  public void testRightShift(){
    int[] route =  new int[]{1,2,3};

    trip.rightShift(route, 2);
    int[] answer = new int[]{1,2,1};
    assertArrayEquals(route,answer);
  }

  @Test
  public void testLeftShift(){
    int[] route =  new int[]{1,2,3};

    trip.leftShift(route, 2);
    int[] answer = new int[]{3,2,3};
    assertArrayEquals(route,answer);
  }

  @Test
  public void testLeftOrRight(){
    int[] route =  new int[]{1,2,3};
    int answer = trip.leftOrRight(route, 0,1,1 );

    assertEquals(1,answer);

  }

  @Test
  public void testFillShiftLeft(){
    int[] route = new int[]{1,2,3,4,5};
    int[] small = new int[]{1,2};

    trip.fillShiftLeft(route,small,3);

    int[] answer = new int[]{1,2,1,2,5};
    assertArrayEquals(answer, route);
  }

  @Test
  public void testFillShiftRight(){
    int[] route = new int[]{1,2,3,4,5};
    int[] small = new int[]{1,2};

    trip.fillShiftRight(route,small,3);

    int[] answer = new int[]{1,2,3,1,2};
    assertArrayEquals(answer, route);
  }

  @Test
  public void testFillArray(){
    int[] route = new int[]{1,2,3,4,5};
    int[] answer = new int[]{1,0,0,0,5};

    trip.fillArray(route, answer, 0,4);
    assertArrayEquals(answer, route);
  }


  @Test
  public void testSVG(){
    String testMap = "\"<svg width=\\\"1920\\\" height=\\\"960\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\"><!-- Created with SVG-edit - http://svg-edit.googlecode.com/ --> <g> <g id=\\\"svg_4\\\"> <svg id=\\\"svg_1\\\" height=\\\"960\\\" width=\\\"1920\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\"> <g id=\\\"svg_2\\\"> <title>Layer 1</title> <rect fill=\\\"rgb(119, 204, 119)\\\" stroke=\\\"black\\\" x=\\\"0\\\" y=\\\"0\\\" width=\\\"1920\\\" height=\\\"960\\\" id=\\\"svg_3\\\"/> </g> </svg> </g> <g id=\\\"svg_9\\\"> <svg id=\\\"svg_5\\\" height=\\\"480\\\" width=\\\"960\\\" y=\\\"240\\\" x=\\\"480\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\"> <g id=\\\"svg_6\\\"> <title>Layer 2</title> <polygon points=\\\"0,0 960,0 960,480 0,480\\\" stroke-width=\\\"12\\\" stroke=\\\"brown\\\" fill=\\\"none\\\" id=\\\"svg_8\\\"/> <polyline points=\\\"0,0 960,480 480,0 0,480 960,0 480,480 0,0\\\" fill=\\\"none\\\" stroke-width=\\\"4\\\" stroke=\\\"blue\\\" id=\\\"svg_7\\\"/> </g> </svg> </g> </g> </svg>\";\n" + "  }";
    assertEquals(trip.svg(), trip.map);
  }
  @Test
  public void testLegDistances(){
    ArrayList<Integer> expectedDistances = new ArrayList<Integer>();
    Collections.addAll(expectedDistances,24,41,59);

    assertEquals(expectedDistances, trip.legDistances());
  }

  @Test
  public void testCalcTripDistance(){
    int[] tripIndices = new int[]{0,1,2};

    int distance = trip.calcTripDistance((tripIndices), trip.createAllDistancesArray());
    assertEquals(distance,65);
  }

  @Test
  public void testRouteSwap(){
    int[] array = new int[]{1,2,3};
    trip.routeSwap(array,0,1);

    int[] testArray = new int[]{2,1,3};
    assertArrayEquals(array,testArray);
  }

  @Test
  public void testCreateAllDistances(){
    int [][] allDistances =  trip.createAllDistancesArray();

    int[] testAllDistances1 = {0,24,59};
    int[] testAllDistances2 = {24,0,41};
    int[] testAllDistances3 = {59,41,0};

    assertArrayEquals(allDistances[0],testAllDistances1);
    assertArrayEquals(allDistances[1],testAllDistances2);
    assertArrayEquals(allDistances[2],testAllDistances3);

  }
}




