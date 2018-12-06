package com.tripco.t10.planner;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
  This class contains tests for the MapBuilder class.
 */
@RunWith(JUnit4.class)
public class TestMapBuilder {
    private MapBuilder mapBuilder;
    private Trip trip;

    private Place testDenver = new Place("dnvr", "Denver", 39.7392, -104.9903);
    private Place testBoulder = new Place("bldr", "Boulder", 40.01499, -105.27055);
    private Place testFortCollins = new Place("foco", "Fort Collins", 40.585258, -105.084419);

    @Before
    public void testInitialization(){
        mapBuilder = new MapBuilder();
    }

    @Test
    public void testMainConstructor(){
        ArrayList<Place> listOfPlaces = new ArrayList<>();

        listOfPlaces.add(testDenver);
        listOfPlaces.add(testBoulder);
        listOfPlaces.add(testFortCollins);

        Option testOption = new Option("user defined", 3959.0, "short", "miles", "svg");

        ArrayList<Integer> distances = new ArrayList<>();
        distances.add(0);
        distances.add(0);
        distances.add(0);

        String testMap = "none";
        trip = new Trip(2, "trip", "Test", listOfPlaces, testOption, distances, testMap);

        mapBuilder = new MapBuilder(trip);
    }

    @Test
    public void testGetMap(){
        ArrayList<Place> listOfPlaces = new ArrayList<>();

        listOfPlaces.add(testDenver);
        listOfPlaces.add(testBoulder);
        listOfPlaces.add(testFortCollins);

        Option testOption = new Option("user defined", 3959.0, "short", "miles", "unknown");

        ArrayList<Integer> distances = new ArrayList<>();
        distances.add(0);
        distances.add(0);
        distances.add(0);

        String testMap = "none";
        trip = new Trip(2, "trip", "Test", listOfPlaces, testOption, distances, testMap);

        mapBuilder = new MapBuilder(trip);
        assertEquals(mapBuilder.getMap(), "Unknown map type");
    }

}
