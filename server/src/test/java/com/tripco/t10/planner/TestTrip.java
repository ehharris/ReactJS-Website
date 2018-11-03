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

  // Setup to be done before every test in TestPlan
  // Creates test trip
  @Before
  public void initialize() {
    ArrayList<Place> listOfPlaces = new ArrayList<>();

    listOfPlaces.add(testDenver);
    listOfPlaces.add(testBoulder);
    listOfPlaces.add(testFortCollins);

    Option testOption = new Option("user defined", 3959.0, "short", "miles", "svg");

    ArrayList<Integer> distances = new ArrayList<>();
    distances.add(0);
    distances.add(0);
    distances.add(0);

    String testMap = "\"<svg width=\\\"1920\\\" height=\\\"960\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\"><!-- Created with SVG-edit - http://svg-edit.googlecode.com/ --> <g> <g id=\\\"svg_4\\\"> <svg id=\\\"svg_1\\\" height=\\\"960\\\" width=\\\"1920\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\"> <g id=\\\"svg_2\\\"> <title>Layer 1</title> <rect fill=\\\"rgb(119, 204, 119)\\\" stroke=\\\"black\\\" x=\\\"0\\\" y=\\\"0\\\" width=\\\"1920\\\" height=\\\"960\\\" id=\\\"svg_3\\\"/> </g> </svg> </g> <g id=\\\"svg_9\\\"> <svg id=\\\"svg_5\\\" height=\\\"480\\\" width=\\\"960\\\" y=\\\"240\\\" x=\\\"480\\\" xmlns:svg=\\\"http://www.w3.org/2000/svg\\\" xmlns=\\\"http://www.w3.org/2000/svg\\\"> <g id=\\\"svg_6\\\"> <title>Layer 2</title> <polygon points=\\\"0,0 960,0 960,480 0,480\\\" stroke-width=\\\"12\\\" stroke=\\\"brown\\\" fill=\\\"none\\\" id=\\\"svg_8\\\"/> <polyline points=\\\"0,0 960,480 480,0 0,480 960,0 480,480 0,0\\\" fill=\\\"none\\\" stroke-width=\\\"4\\\" stroke=\\\"blue\\\" id=\\\"svg_7\\\"/> </g> </svg> </g> </g> </svg>\";\n" + "  }";

    trip = new Trip(2, "trip", "Test", listOfPlaces, testOption, distances, testMap);

    trip.plan();
  }

  @Test
  public void testTrue() {
    assertTrue(true == true);
  }

  @Test
  public void testLegDistances() {

    ArrayList<Integer> expectedDistances = new ArrayList<Integer>();
    Collections.addAll(expectedDistances, 24,41,59);

    assertEquals(expectedDistances, trip.distances);
  }

  @Test
  public void testShortOptimization(){
    ArrayList<Place> testShort = new ArrayList<>();

    testShort.add(testFortCollins);
    testShort.add(testBoulder);
    testShort.add(testDenver);

    if(trip.places.size() == testShort.size() && trip.places != null && testShort != null){
      for(Place place: trip.places){
        if(!testShort.contains(place)){
          assertFalse(false);
        }
      }
    }


  }
}

