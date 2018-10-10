package com.tripco.t10.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/*
  This class contains tests for the Distance class.
 */
@RunWith(JUnit4.class)
public class TestDistance {
    Distance distance;

    @Before
    public void testInstantiate(){
        Place testDenver = new Place("dnvr", "Denver", 39.7392, -104.9903);
        Place testBoulder = new Place("bldr", "Boulder", 40.01499, -105.27055);

        distance = new Distance("distance",2, testDenver, testBoulder, "miles", 24);
    }

    @Test
    public void testGetDistance(){
        assertEquals(distance.getDistance(), 24);
    }

    @Test
    public void testSetDistance(){
        distance.setDistance(0);
        assertEquals(distance.getDistance(), 0);
    }

    @Test
    public void testGetOrigin(){
        Place testDenver = new Place("dnvr", "Denver", 39.7392, -104.9903);
        assertEquals(distance.getOrigin().id, testDenver.id);
        assertEquals(distance.getOrigin().name, testDenver.name);
        assertTrue(distance.getOrigin().latitude == testDenver.latitude);
        assertTrue(distance.getOrigin().longitude ==testDenver.longitude);
    }

    @Test
    public void testGetDestination(){
        Place testBoulder = new Place("bldr", "Boulder", 40.01499, -105.27055);
        assertEquals(distance.getDestination().id, testBoulder.id);
        assertEquals(distance.getDestination().name, testBoulder.name);
        assertTrue(distance.getDestination().latitude == testBoulder.latitude);
        assertTrue(distance.getDestination().longitude ==testBoulder.longitude);

    }

    @Test
    public void testGetUnits(){
        String testUnits = "miles";
        assertEquals(distance.getUnits(), testUnits);

    }

}
