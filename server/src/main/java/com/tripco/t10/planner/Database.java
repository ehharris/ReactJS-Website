package com.tripco.t10.planner;

import java.lang.Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    // db configuration information
    private static final String myDriver = "com.mysql.jdbc.Driver";
    private static String myUrl = "";
    //private final static String myUrl = "jdbc:mysql://localhost:31410";
    private static final String user="cs314-db";
    private static final String pass="eiK5liet1uej";
    // fill in SQL queries to count the number of records and to retrieve the data
    private static final String count = "SELECT count(*) FROM airports";
    private static final String search = "SELECT * FROM airports";
    // Arguments contain the username and password for the database

    /**
     * The main method is where a connection to the database is made to print out the contents.
     */
    public static void main(String[] args) {
        myUrl = "jdbc:mysql://127.0.0.1:56247/cs314";
        try {
            Class.forName(myDriver);
            // connect to the database and query
            try (Connection conn = DriverManager.getConnection(myUrl, user, pass);
                 Statement stCount = conn.createStatement();
                 Statement stQuery = conn.createStatement();
                 ResultSet rsCount = stCount.executeQuery(count);
                 ResultSet rsQuery = stQuery.executeQuery(search);
            ) {
                printJson(rsCount, rsQuery);
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    private static void printJson(ResultSet count, ResultSet query) throws SQLException {
        System.out.printf("\n{\n");
        System.out.printf("\"type\": \"find\",\n");
        System.out.printf("\"title\": \"%s\",\n",search);
        System.out.printf("\"places\": [\n");
        // determine the number of results that match the query
        count.next();
        int results = count.getInt(1);
        // iterate through query results and print out the airport codes
        while (query.next()) {
            System.out.printf(" \"%s\"", query.getString(3));
            if (--results == 0) {
                System.out.printf("\n");
            }
            else {
                System.out.printf(",\n");
            }
        }
        System.out.printf(" ]\n}\n");
    }

    /**
     * setMyUrl method checks to see if the server is running on localhost or black-bottle.
     * Changes the database URL as needed in order to connect to faure.
     */
    public void setMyUrl() {
        String isDevelopment = System.getenv("CS314_ENV");
        // Note that if the environment variable isn't defined, System.getenv will return null
        if(isDevelopment != null && isDevelopment.equals("development")) {
            myUrl = "jdbc:mysql://127.0.0.1:56247/cs314";
        }
        else {
            myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
        }
    }
}
