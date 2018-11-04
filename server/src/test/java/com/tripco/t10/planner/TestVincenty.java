package com.tripco.t10.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TestVincenty {
    Vincenty v;
    double[] coordinates = {39.7392,40.01499,-104.9903,-105.27055};

    // Setup to be done before every test in TestPlan
    @Before
    public void initialize() {
        v = new Vincenty();
    }

    //Test to prove a variable can be instantiated
    @Test
    public void testTrue() {
        // assertTrue checks if a statement is true
        assertTrue(true == true);
    }

    //Test vincenty calculator for miles (Denver to Boulder)
    @Test
    public void testVincentyMiles() {
        int milesResult = v.calculateDistance(coordinates,"miles", 3959.0);
        assertEquals(24, milesResult);

    }
    //Test vincenty calculator for kilometers (Denver to Boulder)
    @Test
    public void testVincentyKilometers() {
        int kilometersResult = v.calculateDistance(coordinates, "kilometers", 6371.0);
        assertEquals(39, kilometersResult);

    }

    //Test vincenty calculator for nautical miles (Denver to Boulder)
    @Test
    public void testVincentyNautical() {
        int nauticalResult = v.calculateDistance(coordinates, "nautical miles", 3440.0);
        assertEquals(21, nauticalResult);

    }

    //Test vincenty calculator for user defined (Denver to Boulder)
    @Test
    public void testVincentyUserDefined() {
        int metersResult = v.calculateDistance(coordinates, "user defined", 6371000.0);
        assertEquals(38889, metersResult);

    }
}
