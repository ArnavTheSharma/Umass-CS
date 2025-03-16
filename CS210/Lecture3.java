// Questions: 
// Is the constructor a static method? 
// Why can't child classes access a parent class' static methods if it inherits everything from it?
// When you do == on primitive types, does it return True when the memory address is the same or does it do .value() because the compiler might have both the variables point to the same address since it's the same value, and so you end up comparing the memory addresses anyway


// In java, a for each loop creates copies of the values and passes it off with the variable you assign it to 
//      e.g. for(a : A) {} iterates through A array and assigns a copy of each value (if primitive) or copy of address (if object)
//      For this reason, doing 2*a inside the for loop wouldn't change the actual value of the element in A


// Java dunder methods --> 
// .equals is not the same as == 
//      == is identity, .equals is semantic equality
//      == checks memory location (for objects) and value for primitives (they'll both be same memory location)
//      .equals is whatever we define it to be (e.g. only check ID, or only check name of the object)


// OOP --> Inhericance, Information Hiding AKA Abstraction (of implementation), state of an object (values + variables), 
// println(b) defaults to println(b.toString())
//      We need to define our own toString method, e.g.:
public class Lecture3 {
    private int number;

    public String toString() {
        return (Integer.toString(number));
    }
}
// If we don't do this, it'll by default return a string consisting of the class name, followed by "@" and the object's hash code in hexadecimal format e.g. ClassName@1a2b3c4d
// The "cannot make a static reference to a non static field" is doing something like System.out.println(number) instead of object.number -- it asks us to make int number to static int number, btu we can't do that because number is an attribute that belongs to each object. 
// Theres a place in memory that keeps track of these class specific static methods (e.g. number of objects created and every bus instance knows where this is located)
//      It knows its superclass and its own classes static methods.
//      Other exampes of static: max or min of list, authority (e.g. what company owns these buses), 


// Scoping: 
private class Foo2 {
    int[] x;

    void foo(int j) {
        int[] x = new int[5];
        for (int i=0; i < x.length; i++) {
            // note that there are ; and not , in the for loop
            x[i] = j;
            // This x[] array is referring to the int[] x from line 41, not line 38 (which is the instance var)
        }
    }
}
