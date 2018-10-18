package com.tripco.t10.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t10.server.HTTP;
import spark.Request;

public class Mapper {

    private Trip trip;

    /**
     * Handles trip planning request, creating a new trip object from the trip request.
     * Does the conversion from Json to a Java class before planning the trip.
     *
     * @param request
     */
    public Mapper(Request request) {
        // first print the request
        System.out.println(HTTP.echoRequest(request));

        // extract the information from the body of the request.
        JsonParser jsonParser = new JsonParser();
        JsonElement requestBody = jsonParser.parse(request.body());
        System.out.println("[TESTING] - Request Body: " + requestBody);

        // convert the body of the request to a Java class.
        Gson gson = new Gson();
        trip = gson.fromJson(requestBody, Trip.class);

        // Map the trip.
        MapBuilder map = new MapBuilder(this.trip);
        // Set svg String
        //trip.map = map.map;
        trip.map = "Fuckoff";
    }

    public String getMap(){
        return trip.map;
    }
}
