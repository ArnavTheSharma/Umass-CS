// Questions: Null pointer vs null in Java. You set the reference variable of the linked list to be equal to null



// In python, you can't add 1 to a value in a key value pair in a dictionry. It instead uses the get and .put method
//      In Santa map example game, if we don't see the key, then we add it and initialize with 1. Else do the get and put method

// Do Java HashMaps have a default key value
//      The getOrDefault takes in a key and default value as input. If key exists, get what's there, else the default value you specified

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public static Map<Location, Integer> locationCount(String directions) {
    Map<Location, Integer> locationCount = new Map<>();
    int x = 0;
    int y = 0;
    locationCount.put(new Location(x, y), 1);
}

public class Lecture10 {
    public static void main(String[] args) {
        Location current = new Location(x, y);
        if (!locationCount.containsKey(current)) { // not in the map at all
            // if location not in m.keys():
            // python m[location] = 1
            locationCount.put(current, 1);
        } else { // in there, update the value there
            int t = locationCount.get(current);
            t++;
            locationCount.put(current, t);
            // python: m[location++]
        }
    }
}


// To recongnize if 2 words are an anagram (e.g. act and cat), we could induvidually iterate through each word and compare
//    But we could also reorder each string alphabetically and easily compare now
//    To alphabetize a string, turn it into the array and use the Java.util.Arrays' (array of chars) built in sort method to sort

// Or, we could make a hashmap of a string (identifier) which maps to a set of all the possible anagrams of that identifier and see if its within same class
//      e.g. act to {act, cat, tac, etc}
//      Identifier we define as just the string alphabetized




// Now we do Hashing

// Arrays have O(1) search given index/location
// Hashing is a magic function where you give it an item and it tells you where it should be
//      This is both for if you want to add, delete, or check if an item is there

// Let's say we initialize an array of size n with an array, boolean[]. Assume its empty and every index is False
//      If we add a number, e.g. 2, then we set index 1 (2nd element) true. Removing the value means turning it false
//      Note: since it's an array, adding 2 twice doesn't change anything.
//      We're not storing item, we're storing the boolean that identifies whether the item exxists
//      Downsides: We have a range of values from - to n, and if we deal with overflow using mod(n), we lose info

// Java initializes an empty array of ints with 0's, so sometimes it can falsely be telling you '0' is in the set when it shold be empty and not have anything

// Fix1: Instead of storing bool value, we store the value itself e.g. 17 at 2 for mod15
//      Problem: COLLISIONS: If 2 is already there, and you add 17, you get 19? Collision
//      Hash Map Collisions
//      Solution for Colisions: Chaining, we basically create a linked list of the numbers which all equal %15, and the array points to the Head.
//      Each index of array will point to a linked list, where if we add a number 19 to array of length 15, and 4 already exists, then the index 4 of array points to head of a linked list which points to 4 which then points to 19.
//          Note: Need to realize if there's no elements, it's a null pointer.

class WraparoundCollisionlessIntegerSet {
    LinkedList<Integer>[] array;
    final int n = 1024;

    public WraparoundCollisionlessIntegerSet() {
        array = (LinkedList<Integer>[])new LinkedList[n];
    }
    void add(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            if (array[i % n] == null) {
                array[i % n] = new LinkedList<>();
            }
        array[i % n].add(i);
    }
}

}


// In reality, insteaed of a linked list, they use something called a red-black tree
// We therefore create an array of type ArrayList<E> in our HashTable class. 

class SimpleHashTable<E> {
    ArrayList<E>[] array; // or LinkedList
    final int n = 1023; // size

    public SimpleHashTable() {
        array = (ArrayList<E>[]) new ArrayList[n];
    }

    void add(E e) { // If
        int i = Math.abs(e.hashCode());
        if (array[i % n] == null) { // if i%n is negative it throws an exception, cant access negative index
            array[i % n] = new ArrayList<>(); 
        }
        if (!array[i % n].contains(e)) {
            array[i % n].add(e);
        }
    }


    void remove(E e) {
        int i = Math.abs(e.hashCode());
        if (array[i % n] == null) {
            return;
        }
        array[i % n].remove(e);
    }
    
    boolean contains(E e) {
        int i = Math.abs(e.hashCode());
        if (array[i % n] == null) {
            return false;
        }
        return array[i % n].contains(e);
    }
}



