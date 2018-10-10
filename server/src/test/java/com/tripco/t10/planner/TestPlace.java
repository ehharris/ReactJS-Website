package com.tripco.t10.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/*
  This class contains tests for the Trip class.
 */
@RunWith(JUnit4.class)
public class TestPlace {
  Place place;

  @Before
  public void initialize() {
    place = new Place("dnvr","Denver",13.0129,29.1138);
  }

  @Test 
  public void testPlace() {
    String id = "dnvr";
    String name = "Denver";
    double testLatitude = 13.0129;
    double testLongitude = 29.1138;

    assertEquals(place.id, id);
    assertEquals(place.name,name);
    assertTrue(place.latitude == testLatitude);
    assertTrue(place.longitude == testLongitude);
  }

}

