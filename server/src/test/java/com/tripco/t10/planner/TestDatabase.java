//package com.tripco.t10.planner;
//
//import java.util.ArrayList;
//
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
///*
// * This class contains tests for the Search class
// */
//@RunWith(JUnit4.class)
//public class TestDatabase {
//    private Database db;
//    private String myUrlOffCampus = "";
//    private String myUrlOnCampus = "";
//
//    // Creates test database and initializes all class variables
//    @Before
//    public void intialize() {
//        ArrayList<Place> places = new ArrayList<Place>();
//        db = new Database(3, "search", "hospital", 1, places);
//
//        myUrlOffCampus = "jdbc:mysql://127.0.0.1:56247/cs314";
//        myUrlOnCampus = "jdbc:mysql://faure.cs.colostate.edu/cs314";
//    }
//
//    // tests setMyUrl() in Database class
//    @Test
//    public void testSetMyUrl() {
//        String isDevelopment = System.getenv("CS314_ENV");
//        // Note that if the environment variable isn't defined, System.getenv will return null
//        if(isDevelopment != null && isDevelopment.equals("development")) {
//            assertEquals("jdbc:mysql://127.0.0.1:56247/cs314", myUrlOffCampus);
//        }
//        else {
//            assertEquals("jdbc:mysql://faure.cs.colostate.edu/cs314", myUrlOnCampus);
//        }
//
//    }
//
//    // tests buildQuery() in Database class
//    @Test
//    public void testBuildQuery() {
//        String testQuery = "SELECT * FROM airports WHERE name LIKE '%hospital%' ORDER BY name LIMIT 1";
//        String query = db.buildQuery();
//        assertEquals(testQuery, query);
//    }
//
//    // tests searchQuery() in Database class
//    @Test
//    public void testSearchQuery() {
//        db.searchQuery();
//        ArrayList<Place> places = new ArrayList<Place>();
//        Place place = new Place("CO16", "Arapahoe Medical Park/Littleton Hospital Heliport", 39.57640075683594, -104.98699951171875);
//        places.add(place);
//        assertEquals(db.getPlaces().indexOf(0), places.indexOf(0));
//    }
//}
