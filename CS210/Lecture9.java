import java.util.HashSet;
import java.util.Set;

public class Lecture9 {
    // Look into making a reversed copy of a linked list using recursion - Slide deck 8
    // Midterm goes from Slides 1 to 8 (ends at Recursion)
    
    
    // Tower of Hanoi
    // We can write a function, Hanoi, that takes an argument of the starting peg, desired ending peg, and helper peg, which returns the fastest way 

//     MoveTower(𝑛, peg1, peg2, peg3):
//     if 𝑛 = 1 then
//         move the only disc from peg1 to peg3
//         return
//     else
//         MoveTower(𝑛 − 1, peg1, peg3, peg2)
//         move the only disc from peg1 to peg3
//     MoveTower(𝑛 − 1, peg2, peg1, peg3)



// Lecture 9
// 2 sets are equal if all elements in set are same --> {1,2,3} = {3,2,1} 
//      Order in lists matter though
// Sets don't have duplicates
//      If i have 2 elements that are .equal to each other, only 1 of the elements will appear in the set (make sure you define your own .equals method!)
//      Adding a duplicate to a set doesn't throw a duplicate, it's just ignored
//  Sets like lists are unbounded in size, and unordered (because theres no order, there's no index! They don't maintain the order as they are added)

// You cant output S U T (where U is the union), but instead can assign S = S U T and print that in Java -- maybe in Python
// NOTE: Changing items after inserting them into a set should NOT be done -- dont use mutable objects as set elements

// set1.addAll(set2) in Java is union
// .removeAll is subtraction
// retainAll is intersection

// Note Set is an interface in java, we have to implement it, which we usuallly do using a HashSet or TreeSet
//      HashSet provides an iterator, which permits using for-each loops, but understanding how it works and its running time depends upon knowing a lot more about the implementation than we’re getting into now

    public static void main(String[] args) throws Exception {
        Set<Integer> s = new HashSet<Integer>();
        s.add(1);
        s.add(2);
        System.out.println("s = " +s); // like lists, you can print them
        
        Set<Integer> t = new HashSet<Integer>();
        t.add(2);
        t.add(3);
        t.add(4);

        for (Integer i : t) {
            System.out.println(i); // like lists, you can iterate through them
        }
    }



    // Set is a Generic Interface.
    Set<Integer> set = new HashSet<Integer>();
    
    // hashCode is why .equals works for us
    //      hashCode reeturns an integer, and we know if 2 objects are diff if their hashCodes aren't equal
    
    // if we have 2 Strings, 
    String s = new String("x");
    String t = new String("x");

    // the == will be diff because different memory addresses, and the String class has a built in .equals method already
    // s.hashCode = t.hashCode because they are .equals

    // This weird integer reuslt results in fast (O(1)) membership lookups since you can use it to calculate an index into an array.
    //      Hash tables are arays that store objects based on their has code. To insert an element into it you figure out where it would fit based on it's hash code.
    //      To see if an element is in an array, you can figure out the elements hash code and jump to the right spot in the array. 
    
    //      However in the real world hash codes aren't always unique per object
    //      Hash code is a really big number, but we take the modulo of the size of the array




}



    // Advent of code gives you problems day by day. https://adventofcode.com/2015/day/3
    //     Santa problem: Make a set of locations (coordinates) and if the length of the set doesn't change after adding (which only happens if duplicate coor is added), then we know there was an extra duplicate. 
private class Location {
    public final int x;
    public final int y;

    public Location (int x, int y) {
        this.x = x;
        this.y = y
    }

    // To generate hashCode() and .equals() method using VSCode, right click from within class, click Source Action, click Generate hashCode() and equals()
    //      And it will automatically add the below: 
    // could also use it to generate toString() method
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Location [x=" + x + ", y=" + y + "]";
    }


    // Now we have the tools to solve the problem

    public static void main(String args[]) {
        Set<Location> visited = new HashSet<>();

        // starting location
        int x=0;
        int y=0;

        // Initializes with origin/starting location
        visited.add(new Location(x,y));


        // For directions, we can do
        for (int i=0; i < directions.length(); i++){
            char c = directions.charAt(i);
            if (c=='^'){
                y+=1;
            }
            else if (c=='v'){
                y-=1;
            }
            else if (c=='<'){
                x-=1;
            }
            else if (c=='>'){
                x+=1;
            }
            else{
                throw new IllegalArgumentException("Huh");
            }
            visited.add(new Location(x, y));
            }
            return visited;
        }


    // In python, to keep track of how many times each location was visited, we use dictionraies
    // In java, we need to make an object of generic type Map <K, V>  
    //      The keys form a Set since can't have repeat keys. In 250 terms, the key-value pairs form a function

    }
}
