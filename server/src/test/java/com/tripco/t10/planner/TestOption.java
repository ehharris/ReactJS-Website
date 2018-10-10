package com.tripco.t10.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/*
  This class contains tests for the Option class.
 */
@RunWith(JUnit4.class)
public class TestOption {
    Option option;

    @Before
    public void testInstantiate(){
        option = new Option("miles",3959);
    }

    @Test
    public void testUnits(){
        String miles = "miles";
        assertEquals(option.units, miles);
    }
    @Test
    public void testUnitRadius(){
        double unitRadius = 3959;
        assertTrue(option.unitRadius == unitRadius);
    }
}
