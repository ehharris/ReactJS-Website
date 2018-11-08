package com.tripco.t10.planner;

import com.google.gson.Gson;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TestSearch {

    // Starts off with a clean database object
    private Database db;
    Gson dbGson = new Gson();

    // initialize the Database db for testing
    @Before
    public void initialize() {
        ArrayList<Place> places = new ArrayList<Place>();
        Filters[] filters = {new Filters("country", (new String[]{"United States"}))};
        db = new Database(4, "search", "denver", filters, 1, 0, places);
    }

    // Test for getSearch() in Search.java
    @Test
    public void testGetSearch() {
        Gson gson = new Gson();
        ArrayList<Place> places = new ArrayList<Place>();
        Filters[] filters = {new Filters("country", (new String[]{"United States"}))};
        Database db1 = new Database(4, "search", "denver", filters, 1, 0, places);
        assertEquals(gson.toJson(db1), dbGson.toJson(db));
    }
}