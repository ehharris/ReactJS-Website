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
public class TestPlace {
  Place place;

  // Setup to be done before every test in TestPlan
  @Before
  public void initialize() {
    place = new Place("dnvr","Denver",13.0129,29.1138);
  }

  @Test 
  public void testPlace() {
    String id="dnvr";
    String name="Denver";
    assertEquals(place.id, id);
    assertEquals(place.name,name);
  }

  @Test
  public void testLat() {
    double test=13.0129;
      assertTrue(place.latitude==test);
  }

  @Test
  public void testLong() {
      double test=29.1138;
      assertTrue(place.longitude==test);
  }
  
}

