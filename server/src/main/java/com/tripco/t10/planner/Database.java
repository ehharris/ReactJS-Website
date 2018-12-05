package com.tripco.t10.planner;

import java.lang.Class;
import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
        this.filters = null;
        this.limit = 0;
        this.found = 0;
        this.places = new ArrayList<Place>();
    }

    /** Constructor used for test cases.
     *
     */
    public Database(int version, String type, String match,
                    Filters[] filters, int limit, int found, ArrayList<Place> places) {
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

    /** Returns starting query for buildQuery().
     *
     *
     */
    private String getQuery(){
        return "SELECT world_airports.id, "
                + "world_airports.name, "
                + "world_airports.latitude, "
                + "world_airports.longitude, "
                +  "world_airports.municipality, "
                + "region.name, "
                + "country.name, "
                + "continents.name, "
                + "world_airports.type "
                + "FROM continents "
                + "INNER JOIN country ON continents.id = country.continent "
                + "INNER JOIN region ON country.id = region.iso_country "
                + "INNER JOIN world_airports ON region.id = world_airports.iso_region "
                + "WHERE (country.name LIKE '%"
                + this.match
                + "%' OR region.name LIKE '%"
                + this.match
                + "%' OR world_airports.name LIKE '%"
                + this.match
                + "%'  OR world_airports.municipality LIKE '%"
                + this.match
                + "%') ";
    }

    /** Function to search through filters.
     *
     * @param query initial query.
     * @return fully formed query.
     */
    private String filterSearch (String query){
        query += "AND ( ";

        for (int i = 0; i < this.filters.length; i++) {
            query = filterSearchHelper(query, i);
        }

        query += " ) ";
        return query;
    }

    /** Called by filterSearch to reduce complexity.
     *
     * @param query string.
     * @param index of filter being searched.
     * @return more complete query.
     */
    private String filterSearchHelper (String query, int index){

        for (int j = 0; j < this.filters[index].values.length; j++) {
            String filterName = this.filters[index].name;
            query = testFilter(query, filterName, index, j);
        }

        return query;
    }

    /** Tests what kind of filter is produced in filterSearchHelper.
     *
     * @param query string.
     * @param filterName filter being tested.
     * @param index of filter being searched.
     * @param j second index of filter searched.
     * @return more complete query.
     */
    private String testFilter (String query, String filterName, int index, int j){
        if (filterName.equals("world airport")) {
            query += "world_airports.name IN ('"
                    + this.filters[index].values[j] + "') ";
        }
        if (filterName.equals("type") || filterName.equals("municipality")) {
            query += "world_airports." + filterName
                    + " IN ('" + this.filters[index].values[j] + "') ";
        }
        else if (filterName.equals("country") || filterName.equals("region")
                || filterName.equals("continents")) {
            query += filterName + ".name IN ('" + this.filters[index].values[j] + "') ";
        }
        if (j != (this.filters[index].values.length - 1)
                || (j == (this.filters[index].values.length - 1)
                && index != (this.filters.length - 1))) {
            query += "OR ";
        }
        return query;
    }

    /** Builds the query for use in searchQuery(), uses filterSearch -> filterSearchHelper -> testFilter.
     *
     */
    public String buildQuery() {

        String query = getQuery();

        if(this.filters != null ) {
            if (this.filters.length > 0) {
                query = filterSearch(query);
            }
        }

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

    /** Adds sorting to query.
     *
     */
    public String addSortToQuery() {
        return "ORDER BY world_airports.name ASC ";
        //return "ORDER BY continents.name,
        // + country.name, region.name, world_airports.municipality,
        // + world_airports.name, world_airports.type ASC ";
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
        String query = buildQuery() + addSortToQuery() + addLimitToQuery();
        String allQueries = buildQuery();
        //System.out.println(query);
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
                this.places.add(new Place(rsQuery.getString("id"), rsQuery.getString("name"),
                        rsQuery.getDouble("latitude"), rsQuery.getDouble("longitude")));
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
