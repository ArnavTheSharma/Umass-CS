//
// Copyright (c) 2025 Tim Richards. All rights reserved.
//
/*
 * Copyright 2025 Tim Richards.
 */
package analyzer;

import static org.junit.Assert.assertArrayEquals;

/**
 * UMass Campus Data Analyzer
 * This program analyzes campus building names, distances, and bus data.
 * 
 * Students will complete the TODO sections.
 */
public class CampusAnalyzer {

  /**
   * Finds the longest building name in the array.
   * 
   * @param buildings An array of building names.
   * @return The longest building name.
   */
  public static String findLongestName(String[] buildings) {
    // TODO: Implement a loop to find the longest building name.

    String longest = buildings[0];

    for (String building : buildings) {
      if (building.length() >= longest.length()) {
        longest = building;
      }
    }
    return longest;

  }

  /**
   * Reverses each building name and stores them in a new array.
   * 
   * @param buildings An array of building names.
   * @return A new array containing reversed names.
   */
  public static String[] reverseNames(String[] buildings) {
    // TODO: Loop through each name, reverse it, and store it in a new array.

    String[] result = new String[buildings.length];

    // Loops through input array, buildings
    for (int i = 0; i < buildings.length; i++) {
      StringBuilder reversedElement = new StringBuilder();

      String building = buildings[i];
      for (int j = building.length() - 1; j > -1; j--) {
        reversedElement.append(building.charAt(j));
      }

      result[i] = reversedElement.toString();
    }

    return result;
  }

  /**
   * Counts how often each letter (A-Z) appears across all building names.
   * 
   * @param buildings An array of building names.
   * @return An array of 26 integers, each representing letter frequency.
   */
  public static int[] countLetters(String[] buildings) {
    // TODO: Iterate over each name and count occurrences of each letter.
    int[] result = new int[26];

    for (String building : buildings) {
      building = building.toLowerCase();
      char[] buildingArray = building.toCharArray();

      for (char c : buildingArray) {
        result[c - 'a']++;
      }
    }

    return result;
  }

  /**
   * Finds the closest building (smallest distance).
   * 
   * @param distances An array of distances from the Campus Center.
   * @return The smallest distance value.
   */
  public static double findMinDistance(double[] distances) {
    // TODO: Implement a loop to find the smallest distance.

    if (distances.length == 0) {
      throw new IllegalArgumentException("Array length cannot be 0!");
    }

    double min = distances[0];
    for (double distance : distances) {
      if (distance < min) {
        min = distance;
      }
    }
    return min;
  }

  /**
   * Finds the farthest building (largest distance).
   * 
   * @param distances An array of distances from the Campus Center.
   * @return The largest distance value.
   */
  public static double findMaxDistance(double[] distances) {
    // TODO: Implement a loop to find the largest distance.
    if (distances.length == 0) {
      throw new IllegalArgumentException("Array length cannot be 0!");
    }

    double max = distances[0];
    for (double distance : distances) {
      if (distance > max) {
        max = distance;
      }
    }
    return max;

  }

  /**
   * Computes the average distance.
   * 
   * @param distances An array of distances from the Campus Center.
   * @return The average distance.
   */
  public static double findAverageDistance(double[] distances) {
    // TODO: Compute the sum of distances and divide by the count.
    if (distances.length == 0) {
      throw new IllegalArgumentException("Array length cannot be 0!");
    }

    double sum = 0;
    for (double distance : distances) {
      sum += distance;
    }
    return (sum / distances.length);

  }

  /**
   * Sorts an array of distances using Bubble Sort.
   * 
   * @param distances The array to be sorted.
   */
  public static void sortDistances(double[] distances) {
    // TODO: Implement Bubble Sort to sort distances in ascending order.
    boolean sorted = false;

    while (sorted == false) {
      sorted = true;
      for (int i = 0; i < distances.length - 1; i++) {
        if (distances[i] > distances[i + 1]) {
          sorted = false;
          // swaps values
          double temporary = distances[i];
          distances[i] = distances[i + 1];
          distances[i + 1] = temporary;
        }
      }
    }
  }

  /**
   * Finds the most crowded bus.
   * 
   * @param buses An array of Bus objects.
   * @return The bus with the highest passenger count.
   */
  public static Bus findMostCrowdedBus(Bus[] buses) {
    // TODO: Implement a loop to find the bus with the most passengers.
    if (buses.length == 0) {
      throw new IllegalArgumentException("List of buses can't be empty!");
    }

    int max = buses[0].getCurrentPassengers();
    Bus result = buses[0];
    for (Bus bus : buses) {
      if (bus.getCurrentPassengers() > max) {
        result = bus;
      }
    }

    return result;
  }

  /**
   * Finds buses that are overloaded.
   * 
   * @param buses An array of Bus objects.
   * @return An array of overloaded buses.
   */
  public static Bus[] findOverloadedBuses(Bus[] buses) {
    // TODO: Identify buses where currentPassengers > maxCapacity.
    if (buses.length == 0 || buses == null) {
      return new Bus[0];
    }

    int resultLength = 0;
    for (Bus bus : buses) {
      if (bus.getCurrentPassengers() > bus.getMaxCapacity()) {
        resultLength += 1;
      }
    }

    Bus[] result = new Bus[resultLength];
    int index = 0;

    for (Bus bus : buses) {
      if (bus.getCurrentPassengers() > bus.getMaxCapacity()) {
        result[index] = bus;
        index += 1;
      }
    }

    return result;
  }

  /**
   * Sorts buses by passenger count using Bubble Sort.
   * 
   * @param buses The array to be sorted.
   */
  public static void sortBusesByPassengers(Bus[] buses) {
    // TODO: Implement Bubble Sort to sort buses by passengers.

    for (int i = 0; i < buses.length - 2; i++) {
      for (int j = 0; j < buses.length - 1; j++) {
        if (buses[j].getCurrentPassengers() > buses[j + 1].getCurrentPassengers()) {
          Bus temp = buses[j];
          buses[j] = buses[j + 1];
          buses[j + 1] = temp;
        }
      }
    }
  }
}