// Create new java project --> ctrl shift p --> java create project --> no build tools

public class Lecture2 {

    public static double sum(double[] data) {
        double total = 0;

        // for-each loop style
        for (double val : data) {
            // data is an array of doubles. For each element in data, call it val 
            // for each works in anything that's iterable (includes things like container classes e.g. ArrayList. Every container class has it's own iterator, so no need for indices)
            total += val;
            // don't change things in the array like doing val = 3
        }
        return total;
    } 
    public static void main(String[] args) {
        // String args is basically an array of strings that stores arguments passed to a program through the command-line
        
        
    }

}

// Instance variables, AKA fields, represent data asssosiated with an object (either primitive or class type)
// Methods that doesn't change any fields are accessors or observer methods. Opposite is update or transformer methods


// Most data strcutures and algorithmns in Java's libraries are designed to work only with objects, so to get past using primitives in these we use wrapper classes
// e.g. char --> Character --> obj= new Character('Z'); --> obj.charValue(); 
int j = 8; // primitive
Integer a = new Integer(12); // wrapper class
int k = a; // When wrapper classes are called like this, it calls a.intValue()
int m = j + a; // a is automaticcally unboxed before the addition
int n = Integer.parseInt("2013");  // static method of Integer class
Integer b = new Integer("2014"); // Accepts strings



//  Signature is the methods' name, argument type, and number of arguments --- but not the return type
// public is that all classeses can access, protected is subclasses, and private is only from within the class (not within the file since that would include other classes)



//  Creating a java game e.g. tiktaktoe, need to maintain the state of the board and how to modify the state

// In java, a for each loop creates copies of the values and passes it off with the variable you assign it to 
//      e.g. for(a : A) {} iterates through A array and assigns a copy of each value (if primitive) or copy of address (if object)
//      For this reason, doing 2*a inside the for loop wouldn't change the actual value of the element in A
