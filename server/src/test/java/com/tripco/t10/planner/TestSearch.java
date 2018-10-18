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

    @Before
    public void initialize() {
        ArrayList<Place> places = new ArrayList<Place>();
        db = new Database(3, "search", "hospital", 1, places);

    }

    @Test
    public void testGetSearch() {
        Gson gson = new Gson();
        ArrayList<Place> places = new ArrayList<Place>();
        Database db1 = new Database(3, "search", "hospital", 1, places);
        assertEquals(gson.toJson(db1), dbGson.toJson(db));
    }
}