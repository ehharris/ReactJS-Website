package com.tripco.t10.planner;

/**
 *  Class to apply filters to search.
 */
public class Filters {
    public String name;
    public String[] values;

    /**
     *  Constructor for testing.
     */
    public Filters(String name, String[] values){
        this.name = name;
        this.values = values;
    }
}
