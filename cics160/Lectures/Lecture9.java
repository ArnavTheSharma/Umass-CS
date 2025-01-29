import java.util.ArrayList;
import java.util.Arrays;

class MyMainProgram {
    @SuppressWarnings("removal")
    public static void main(String[] args) {
        ArrayList<Integer> newList = new ArrayList<>();
        newList.add(new Integer(14));
        newList.add(14); // auto turns 14 into object - AKA autoboxing - it turns a primitive data type into a boxed class 
        // (Java arrays take only objects of the, no primitive datatypes) - boxes to the Integer class because we declared <Integer> before, NOT int class
        System.out.println(newList);
    }
}


// why use a linked list if accessing element needs O(n)? Appending is always O(1), which esp imp if you append alot

// append() in ArrayList is O(1) most of time when theres more space, but sometimes O(n) in case you dont have space
// However it's amortized to O(1) because in long run (when n gets rly rly big) 

