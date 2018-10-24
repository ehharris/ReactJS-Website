package com.tripco.t10.planner;

import com.google.gson.Gson;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
  This class contains tests for the Trip class.
 */
@RunWith(JUnit4.class)
public class TestCalculate {
    private Calculate calc;
    private Gson calcGson = new Gson();

    @Before
    public void initialize() {
        ArrayList<Place> places = new ArrayList<Place>();
        calc = new Calculate(places);

    }

    @Test
    public void testGetSearch() {
        Gson gson = new Gson();
        ArrayList<Place> places = new ArrayList<Place>();
        Calculate calculateTest = new Calculate(places);
        assertEquals(gson.toJson(calculateTest), calcGson.toJson(calc));
    }
}
