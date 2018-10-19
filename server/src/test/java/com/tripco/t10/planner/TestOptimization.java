package com.tripco.t10.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/*
  This class contains tests for the Trip class.
 */
@RunWith(JUnit4.class)
public class TestOptimization {
    Optimization opt;

    @Before
    public void testInitialize(){
        opt = new Optimization("test", "testing");
    }

    @Test
    public void testVariables(){
        assertEquals("test",opt.label);
        assertEquals("testing",opt.description);
    }
}
