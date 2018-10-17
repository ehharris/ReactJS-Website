package com.tripco.t10.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t10.server.HTTP;
import spark.Request;
import java.util.ArrayList;

public class Search {

    // Starts off with a clean database object
    private Database db;

    /** Handles database search request, creating new db object from search request.
     * Does the conversion from Json to a Java class before searching the database.
     * @param request
     */
    public Search(Request request) {
        // first print the request
        System.out.println(HTTP.echoRequest(request));

        // extract the information from the body of the request (Will be phrase or word)
        JsonParser jsonParser = new JsonParser();
        JsonElement requestBody = jsonParser.parse(request.body());
        System.out.println("[TESTING] - Request Body: " + requestBody);

        // convert the body of the request to a Database object
        Gson gson = new Gson();
        db = gson.fromJson(requestBody, Database.class);

        // sets the places variable in the database object depending on keyword search
        db.searchQuery();
    }

    /** Handles the response for the Database object.
     * Does the conversion from a Java class to a Json string.
     * @return
     */
    public String getSearch() {
        Gson gson = new Gson();
        return gson.toJson(db);
    }

}
