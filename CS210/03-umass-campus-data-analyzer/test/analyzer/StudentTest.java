//
// Copyright (c) 2025 Tim Richards. All rights reserved.
//
package analyzer;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit test class for FaultyCampusAnalyzer.
 * 
 * Students must complete the TODO sections by writing
 * appropriate test cases.
 */
public class StudentTest {

  /**
   * Test findShortestName() method.
   */
  @Test
  public void testFindShortestName() {

    // TODO: implement tests to check the correctness of the method.
    // METHOD: FaultyCampusAnalyzer.findShortestName(String[] buildings)

    String[] buildings = { "Library", "Engineering Lab", "Dormitory" };
    String[] buildingsNull = { "", "" };
    String[] buildingsAllSameLength = { "Library", "Library", "Library", "Library" };
    String[] buildingSingleElement = { "Library" };

    assertEquals("Normal implementation is incorrect", "Library", FaultyCampusAnalyzer.findShortestName(buildings));
    assertEquals("Empty array of buildings dont work", "", FaultyCampusAnalyzer.findShortestName(buildingsNull));
    assertEquals("Null input building doesn't work", "", FaultyCampusAnalyzer.findShortestName(null));
    assertEquals("Building with all same elements doesn't work", "Library",
        FaultyCampusAnalyzer.findShortestName(buildingsAllSameLength));
    assertEquals("Building with single element doesn't work", "Library",
        FaultyCampusAnalyzer.findShortestName(buildingSingleElement));
  }

  /**
   * Test findMedianDistance() method.
   */
  @Test
  public void testFindMedianDistance() {
    double[] distancesNormal = { 1.0, 2.0, 3.0 };
    assertEquals("Normal implementation with odd length doesn't work", 2.0,
        FaultyCampusAnalyzer.findMedianDistance(distancesNormal), 0.001);

    double[] distances2 = { 1.0, 2.0 };
    assertEquals("Normal implementation with even length doesn't work", 1.5,
        FaultyCampusAnalyzer.findMedianDistance(distances2), 0.001);

    // Note: for null and empty arrays implementation of code already throws an
    // exception error

    double[] distances3 = { 1.0 };
    assertEquals("Array of length 1 doesn't work", 1.0, FaultyCampusAnalyzer.findMedianDistance(distances3), 0.001);

    double[] distances4 = { 4.0, 3.0, 2.0, 1.0 };
    assertEquals("Unsorted array implementation does not work", 2.5,
        FaultyCampusAnalyzer.findMedianDistance(distances4), 0.001);

    double[] distances5 = { -4.0, -3.0, 0.0, 2.0, 1.0 };
    assertEquals("Unsorted array with negative values implementation does not work", 0.0,
        FaultyCampusAnalyzer.findMedianDistance(distances5), 0.001);

    // TODO: implement tests to check the correctness of the method.
    // METHOD: FaultyCampusAnalyzer.findMedianDistance(double[] distances)
  }

  /**
   * Test isAnyBusFull() method.
   */
  @Test
  public void testIsAnyBusFull() {
    // TODO: implement tests to check the correctness of the method.
    // METHOD: FaultyCampusAnalyzer.isAnyBusFull(Bus[] buses)

    assertFalse("Doesn't work for null bus arrays", FaultyCampusAnalyzer.isAnyBusFull(null));

    Bus[] emptyBuses = {};
    assertFalse("Doesn't work for empty bus arrays", FaultyCampusAnalyzer.isAnyBusFull(emptyBuses));

    Bus[] busesNormal = {
        new Bus(10, "Route A", 30, 50),
        new Bus(20, "Route B", 45, 60),
        new Bus(30, "Route C", 25, 40)
    };
    assertFalse("Doesn't work for normal bus array", FaultyCampusAnalyzer.isAnyBusFull(busesNormal));

    Bus[] busesOverMadCapacity = {
        new Bus(10, "Route A", 300, 50),
        new Bus(20, "Route B", 450, 60),
        new Bus(30, "Route C", 250, 40)
    };
    assertFalse("Doesn't work for bus array with all elements over max capacity",
        FaultyCampusAnalyzer.isAnyBusFull(busesOverMadCapacity));

    Bus[] allBusesAtMax = {
        new Bus(10, "Route A", 30, 30),
        new Bus(20, "Route B", 60, 60),
        new Bus(30, "Route C", 40, 40)
    };
    assertTrue("Doesn't work for bus array with all elements at max capacity",
        FaultyCampusAnalyzer.isAnyBusFull(allBusesAtMax));

    Bus[] oneBusAtMax = {
        new Bus(10, "Route A", 50, 50),
        new Bus(20, "Route B", 45, 60),
        new Bus(30, "Route C", 25, 40)
    };
    assertTrue("Doesn't work for bus array with 1 element at max capacity",
        FaultyCampusAnalyzer.isAnyBusFull(oneBusAtMax));
  }

  /**
   * Test countUnderloadedBuses() method.
   */
  @Test
  public void testCountUnderloadedBuses() {

    // TODO: implement tests to check the correctness of the method.
    // METHOD: FaultyCampusAnalyzer.countUnderloadedBuses(Bus[] buses)
    assertEquals("Doesn't work for null bus arrays", 0, FaultyCampusAnalyzer.countUnderloadedBuses(null));

    Bus[] emptyBuses = {};
    assertEquals("Doesn't work for empty bus arrays", 0, FaultyCampusAnalyzer.countUnderloadedBuses(emptyBuses));

    Bus[] noUnderLoadedBuses = {
        new Bus(10, "Route A", 40, 50),
        new Bus(20, "Route B", 45, 60),
        new Bus(30, "Route C", 35, 40)
    };
    assertEquals("Doesn't work for bus arrays with no underloaded buses", 0,
        FaultyCampusAnalyzer.countUnderloadedBuses(noUnderLoadedBuses));

    Bus[] busesUnderloaded = {
        new Bus(10, "Route A", 10, 50),
        new Bus(20, "Route B", 45, 60),
        new Bus(30, "Route C", 15, 40)
    };
    assertEquals("Doesn't work correctly for some underloaded buses", 2,
        FaultyCampusAnalyzer.countUnderloadedBuses(busesUnderloaded));

  }
}