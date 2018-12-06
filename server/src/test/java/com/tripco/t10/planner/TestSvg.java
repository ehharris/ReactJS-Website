package com.tripco.t10.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

/**
 * Tests for svg.java.
 */

@RunWith(JUnit4.class)
public class TestSvg {
    private Svg svg;
    private Trip trip;

    private Place testDenver = new Place("dnvr", "Denver", 39.7392, -104.9903);
    private Place testBoulder = new Place("bldr", "Boulder", 40.01499, -105.27055);
    private Place testFortCollins = new Place("foco", "Fort Collins", 40.585258, -105.084419);

    @Before
    public void testInitialization(){
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

        svg = new Svg(trip);
    }

    @Test
    public void testSvgBuilder(){
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

        svg = new Svg(trip);
        svg.svgBuilder();
    }
}
