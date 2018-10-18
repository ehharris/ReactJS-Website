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
    private int limit;
    private ArrayList<Place> places;

    /** Default Constructor.
     * Necessary when one variable is missing (such as places) in POST JSON request.
     */
    public Database() {
        this.version = 0;
        this.type = "";
        this.match = "";
        this.limit = 0;
        this.places = new ArrayList<Place>();
    }

    /** Constructor used for test cases.
     *
     */
    public Database(int version, String type, String match, int limit, ArrayList<Place> places) {
        this.version = version;
        this.type = type;
        this.match = match;
        this.limit = limit;
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
        query += "SELECT * FROM airports WHERE name LIKE '%";
        query += this.match;
        query += "%' ORDER BY name";

        if(this.limit > 0) {
            query += " LIMIT " +  this.limit;
        }

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
                String name = rsQuery.getString("name");
                double latitude = Double.parseDouble(rsQuery.getString("latitude"));
                double longitude = Double.parseDouble(rsQuery.getString("longitude"));
                Place place = new Place(id, name, latitude, longitude);
                this.places.add(place);
            }
            stQuery.close();
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
