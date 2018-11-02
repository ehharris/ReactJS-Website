package com.tripco.t10.planner;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/*
  This class contains tests for the Filters class.
 */
@RunWith(JUnit4.class)
public class TestFilters {
    Filters filters;
    @Before
    public void testInitialize(){
        filters = new Filters("name",
                  new String[]{"miles", "kilometers", "nautical miles", "user defined"});
    }

    @Test
    public void testConstructor(){
        assertEquals(filters.name, "name");
        assertArrayEquals(filters.values,new String[]{"miles", "kilometers", "nautical miles", "user defined"});

    }
}
