package com.tripco.t10.planner;

import java.lang.Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    private int version;
    private String type;
    private String match;
    private Filters filters;
    private int limit;
    private int found;
    private ArrayList<Place> places;

    /** Default Constructor.
     * Necessary when one variable is missing (such as places) in POST JSON request.
     */
    public Database() {
        this.version = 4;
        this.type = "search";
        this.match = "";
        this.limit = 0;
        this.found = 0;
        this.places = new ArrayList<Place>();
    }

    /** Constructor used for test cases.
     *
     */
    public Database(int version, String type, String match, int limit, int found, ArrayList<Place> places) {
        this.version = version;
        this.type = type;
        this.match = match;
        this.limit = limit;
        this.found = found;
        this.places = places;
    }

    /** Getter method used for test cases.
     *
     */
    public ArrayList<Place> getPlaces() {
        return this.places;
    }

    /** setMyUrl method checks to see if the server is running on localhost or black-bottle.
     * Changes the database URL as needed in order to connect to faure.
     */
    public String setMyUrl() {
        String isDevelopment = System.getenv("CS314_ENV");
        // Note that if the environment variable isn't defined, System.getenv will return null
        if(isDevelopment != null && isDevelopment.equals("development")) {
            return "jdbc:mysql://127.0.0.1:56247/cs314";
        }
        else {
            return "jdbc:mysql://faure.cs.colostate.edu/cs314";
        }
    }

    /** Builds the query for use in searchQuery().
     *
     */
    public String buildQuery() {
        String query = "";
        query += "SELECT world_airports.name, world_airports.latitude, world_airports.longitude, world_airports.municipality, region.name, country.name, continents.name " +
                "FROM continents " +
                "INNER JOIN country ON continents.id = country.continent " +
                "INNER JOIN region ON country.id = region.iso_country " +
                "INNER JOIN world_airports ON region.id = world_airports.iso_region " +
                "WHERE country.name LIKE '%" + this.match + "%' " +
                "OR region.name LIKE '%" + this.match + "%' " +
                "OR world_airports.name LIKE '%" + this.match + "%' " +
                "OR world_airports.municipality LIKE '%" + this.match + "%' ";

        //String query = "SELECT world_airports.name, world_airports.municipality, region.name, country.name, continents.name FROM continents INNER JOIN country ON continents.id = country.continent INNER JOIN region ON country.id = region.iso_country INNER JOIN world_airports ON region.id = world_airports.iso_region WHERE (country.name LIKE \"%" + this.match + "%\" OR region.name LIKE \"%" + this.match + "%\" OR world_airports.name LIKE \"%" + this.match + "%\" OR world_airports.municipality LIKE \"%" + this.match + "%\") AND country.name IN (\"United States\") LIMIT 100;";

        if(filters.name == "country") {
            for(int i = 0; i < filters.values.length; i++) {
                query += " AND country.name IN ('" + filters.values[i] + "') ";
            }
        }

        if(filters.name == "continents") {
            for(int i = 0; i < filters.values.length; i++) {
                query += " AND continents.name IN ('" + filters.values[i] + "') ";
            }
        }

        if(filters.name == "region") {
            for(int i = 0; i < filters.values.length; i++) {
                query += " AND region.name IN ('" + filters.values[i] + "') ";
            }
        }

        if(filters.name == "world_airports") {
            for(int i = 0; i < filters.values.length; i++) {
                query += " AND world_airports.name IN ('" + filters.values[i] + "') ";
            }
        }

        //query += "ORDER BY continents.name, country.name, region.name, world_airports.municipality, world_airports.name ASC ";
        query += "LIMIT 100;";
        return query;
    }

    /** Finds places from database depending on match value.
     *
     */
    public void searchQuery() {
        // db configuration information
        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = setMyUrl();
        String user = "cs314-db";
        String pass = "eiK5liet1uej";

        String query = buildQuery();

        try {
            // create mysql database connection
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, user, pass);
            // create the java statement
            Statement stQuery = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rsQuery = stQuery.executeQuery(query);

            while (rsQuery.next()) {
                String id = rsQuery.getString("id");
                String name = rsQuery.getString(2);
                double latitude = Double.parseDouble(rsQuery.getString("latitude"));
                double longitude = Double.parseDouble(rsQuery.getString("longitude"));
                Place place = new Place(id, name, latitude, longitude);
                this.places.add(place);
                this.found += 1;
            }
            stQuery.close();
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
