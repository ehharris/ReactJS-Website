package com.tripco.t10.planner;

/**
 *  Class to apply filters to search.
 */
public class Filters {
    private String name;
    private String[] values;

    /**
     *  Constructor for testing.
     */
    public Filters(String name, String[] values){
        this.name = name;
        this.values = values;
    }
}
