package com.tripco.t10.planner;

import java.lang.Class;
import java.lang.String;
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
    private Filters[] filters;
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
        this.filters = new Filters[0];
        this.limit = 0;
        this.found = 0;
        this.places = new ArrayList<Place>();
    }

    /** Constructor used for test cases.
     *
     */
    public Database(int version, String type, String match, Filters[] filters, int limit, int found, ArrayList<Place> places) {
        this.version = version;
        this.type = type;
        this.match = match;
        this.filters = filters;
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

        String query = "SELECT world_airports.id, world_airports.name, world_airports.latitude, world_airports.longitude, world_airports.municipality, region.name, country.name, continents.name, world_airports.type " +
                "FROM continents " +
                "INNER JOIN country ON continents.id = country.continent " +
                "INNER JOIN region ON country.id = region.iso_country " +
                "INNER JOIN world_airports ON region.id = world_airports.iso_region " +
                "WHERE (country.name LIKE '%" + this.match + "%' OR region.name LIKE '%" + this.match + "%' OR world_airports.name LIKE '%" + this.match + "%'  OR world_airports.municipality LIKE '%" + this.match + "%') ";

        if(filters.length > 0) {
            query += "AND ( ";

            for(int i = 0; i < filters.length; i++) {
                for(int j = 0; j < filters[i].values.length; j++ ) {
                    if(filters[i].name.equals("country")) {
                        query += "country.name IN ('" + filters[i].values[j] + "') ";
                    }

                    if(filters[i].name.equals("world airport")) {
                        query += "world_airports.name IN ('" + filters[i].values[j] + "') ";
                    }

                    if(filters[i].name.equals("type")) {
                        query += "world_airports.type IN ('" + filters[i].values[j] + "') ";
                    }


                    if(filters[i].name.equals("municipality")) {
                        query += "world_airports.municipality IN ('" + filters[i].values[j] + "') ";
                    }

                    if(filters[i].name.equals("region")) {
                        query += "region.name IN ('" + filters[i].values[j] + "') ";
                    }

                    if(filters[i].name.equals("continents")) {
                        query += "continents.name IN ('" + filters[i].values[j] + "') ";
                    }

                    if(j != ( filters[i].values.length - 1 ) || (j == (filters[i].values.length - 1) && i != (filters.length - 1))) {
                        query += "AND ";
                    }
                }
            }

            query += " ) ";
//            query += "AND country.name IN ('" + filters[0].values[0] + "') LIMIT 100";
        }

        //query += "ORDER BY continents.name, country.name, region.name, world_airports.municipality, world_airports.name ASC ";
//        System.out.println("Test Query: " + query);
//        System.out.println("Test String: " + filters[0].name);
        //System.out.println(query);
        return query;
    }

    /** Adds limit attribute to end of query.
     *
     */
    public String addLimitToQuery() {
        if(this.limit > 0) {
            return " LIMIT " + this.limit;
        }
        else {
            return "";
        }
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

        String query = buildQuery() + addLimitToQuery();
        String allQueries = buildQuery();

        try {
            // create mysql database connection
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, user, pass);
            // create the java statement
            Statement stQuery = conn.createStatement();
            Statement allStQuery = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rsQuery = stQuery.executeQuery(query);
            ResultSet allRsQuery = allStQuery.executeQuery(allQueries);

            while (rsQuery.next()) {
//                String id = rsQuery.getString("id");
//                String name = rsQuery.getString("name");
//                double latitude = Double.parseDouble(rsQuery.getString("latitude"));
//                double longitude = Double.parseDouble(rsQuery.getString("longitude"));
//                Place place = new Place(id, name, latitude, longitude);
                this.places.add(new Place(rsQuery.getString("id"), rsQuery.getString("name"), rsQuery.getDouble("latitude"), rsQuery.getDouble("longitude")));
            }
            stQuery.close();

            while(allRsQuery.next()) {
                this.found += 1;
            }
            allStQuery.close();
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
