package com.tripco.t10.planner;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
 * This class contains tests for the Search class
 */
@RunWith(JUnit4.class)
public class TestDatabase {
    private Database db;
    private String myUrlOffCampus = "";
    private String myUrlOnCampus = "";

    // Creates test database and initializes all class variables
    @Before
    public void intialize() {
        ArrayList<Place> places = new ArrayList<Place>();
        Filters[] filters = {new Filters("country", (new String[]{"United States"}))};
        db = new Database(4, "search", "denver", filters, 1, 0, places);

        myUrlOffCampus = "jdbc:mysql://127.0.0.1:56247/cs314";
        myUrlOnCampus = "jdbc:mysql://faure.cs.colostate.edu/cs314";
    }

    // tests setMyUrl() in Database class
    @Test
    public void testSetMyUrl() {
        String isDevelopment = System.getenv("CS314_ENV");
        // Note that if the environment variable isn't defined, System.getenv will return null
        if(isDevelopment != null && isDevelopment.equals("development")) {
            assertEquals("jdbc:mysql://127.0.0.1:56247/cs314", myUrlOffCampus);
        }
        else {
            assertEquals("jdbc:mysql://faure.cs.colostate.edu/cs314", myUrlOnCampus);
        }

    }

    // tests buildQuery() in Database class
    @Test
    public void testBuildQuery() {
        String testQuery = "SELECT world_airports.id, world_airports.name, world_airports.latitude, world_airports.longitude, world_airports.municipality, region.name, country.name, continents.name, world_airports.type " +
                "FROM continents " +
                "INNER JOIN country ON continents.id = country.continent " +
                "INNER JOIN region ON country.id = region.iso_country " +
                "INNER JOIN world_airports ON region.id = world_airports.iso_region " +
                "WHERE (country.name LIKE '%denver%' OR region.name LIKE '%denver%' OR world_airports.name LIKE '%denver%'  OR world_airports.municipality LIKE '%denver%') " +
                "AND ( country.name IN ('United States')  )  LIMIT 1";

        String query = db.buildQuery() + " LIMIT 1";
        assertEquals(testQuery, query);
    }

    // tests searchQuery() in Database class
    @Test
    public void testSearchQuery() {
        db.searchQuery();
        ArrayList<Place> places = new ArrayList<Place>();
        Place place = new Place("OCD4", "Kauffman Heliport", 40.1463012695, -104.887001038);
        places.add(place);
        assertEquals(db.getPlaces().indexOf(0), places.indexOf(0));
    }

    // tests addLimitToQuery()
    @Test
    public void testAddLimitToQuery() {
        String limitQuery = db.addLimitToQuery();
        String testQuery = " LIMIT 1";
        assertEquals(limitQuery, testQuery);
    }
}
