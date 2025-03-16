//
// Copyright (c) 2025 Tim Richards. All rights reserved.
//
/*
 * Copyright 2025 Tim Richards.
 */
package analyzer;

/**
 * Represents a UMass campus bus.
 */
public class Bus {
  private int busNumber;
  private String routeName;
  private int currentPassengers;
  private int maxCapacity;

  public Bus(int busNumber, String routeName, int currentPassengers, int maxCapacity) {
    // // TODO: Implement the constructor.
    // if (currentPassengers < 0 || maxCapacity < 0) {
    // throw new IllegalArgumentException(
    // "Number of passengers or max capacity cannot be negative!");
    // }
    this.busNumber = busNumber;
    this.routeName = routeName;
    this.currentPassengers = currentPassengers;
    this.maxCapacity = maxCapacity;
  }

  public int getBusNumber() {
    // TODO: Return the bus number.
    return this.busNumber;
  }

  public String getRouteName() {
    // TODO: Return the route name.
    return this.routeName;
  }

  public int getCurrentPassengers() {
    // TODO: Return the current number of passengers.
    return this.currentPassengers;
  }

  public int getMaxCapacity() {
    // TODO: Return the maximum capacity.
    return this.maxCapacity;
  }

  // TODO: Implement the equals method.

  public boolean equals(Bus obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    if (obj.getBusNumber() != this.busNumber) {
      return false;
    }
    if (obj.getRouteName() != this.routeName) {
      return false;
    }
    if (obj.getCurrentPassengers() != this.currentPassengers) {
      return false;
    }
    if (obj.getMaxCapacity() != this.maxCapacity) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    // TODO: Return a formatted string representation of the bus.
    // You must return a string that is EXACTLY 1 line long and
    // looks like this (without the double quotes):
    // "Bus 34 (Campus Shuttle Northbound) - Passengers: 50, Max Capacity: 60"

    String result = String.format("Bus %d (%s) - Passengers: %d, Max Capacity: %d", this.busNumber, this.routeName,
        this.currentPassengers, this.maxCapacity);
    return result;
  }
}