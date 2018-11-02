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
        option = new Option("user defined",3959, "none", "miles","svg");
    }

    @Test
    public void testUnits(){
        String miles = "user defined";
        assertEquals(option.units, miles);
    }
    @Test
    public void testUnitRadius(){
        double unitRadius = 3959;
        assertTrue(option.unitRadius == unitRadius);
    }

    @Test
    public void testOptimization(){
        String optimization = "none";
        assertEquals(option.optimization, optimization);
    }

    @Test
    public void testUnitName(){
        String unitName = "miles";
        assertEquals(option.unitName, unitName);
    }
}
