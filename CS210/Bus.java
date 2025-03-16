// Questions
// how much memory does declaring an object without initializing with values in java take? 
// when we create a new object Bus b, does it technically point to 2 addresses? 1 pointer to the string b and the other the address of the values. 
//      Ans: It's basically stored like an attribute
// if I declare a default int variable inside and outside of a class, which ones are 0 (default value) and null
//      Ans: 

int i;
// finds space in memory for 4 bytes and assigns the memory address to the variable i
int j;

i = 5;
// Line 3 inserts the VALUE 12 into the memory space called 𝑖
j = 3 * i;
// this looks up the value stored in the space called 𝑖, loads it into a register in the CPU, mulIplies it by 3 and copies the resulIng value from a register into the space called 𝑗. 12 36 Memory Model (Primi3ve Data Types)


public class Bus {
    int number; //route #
    double distance; //route length
 
    public Bus(int n, double d){
        this.number = n;
        this.distance =d ;
    }
    public static void main(String args[]) {
        
        Bus b;
        // creates a reference variable (not physical address like in c) that is initialized to null and only stores a reference, so it's .
        b = new Bus(43, 5);
        // new keyword creates new instance and returns reference address, which is stored into b. 
        // b now points to the address that has these values
        // JVM implements “automaIc memory management” 

    }

}



// To create a copy of an object, we can have a **copy constructor** that takes an input of an object and assigns all it's attributes to the created object
// Java does not provide a default copy constructor like C++
public class Bus {
    int number; //route #
    double distance; //route length

    // old constructor
    public Bus(int n, double d){
        this.number = n;
        this.distance = d;
    }

    // copy constructor
    public Bus(Bus b) {
        this.number = b.number;
        this.distance = b.distance;
    }

    public static void main(String args[]) {
                
        // swap the values of the 2 objects
        Bus a = new Bus(43,5.5);
        Bus b = new Bus(10,7.2);

        // bad answer: you create a dummy object c and do c = a, a = b, b = c with each induvidual attribute
        // good answer: you swap only the reference variables
        Bus dummy = b;
        b = a; // We do b first since we alr have another reference pointing to b, so we can lose b's reference variable
        a = dummy; 
        

        int[] BusNumbers;
        // can't do int[8] BusNumbers, need to do int[] BusNumbrs = new int[8];
        // same process: BusNumbers is a reference variable that is assigned a memory that has 8*4 = 32 bytes of contiguous/consecutive memory
        // BusNumbers[2] behind the scenes find the address of the array and finds the offset (2) to the value you want (it won't be 2 but 2*4 because int = 4 bytes)
        //     This is why arrays start at 0 index: easy to multiply by 0

        Bus[] buses;
        buses = new Bus[5];
        // creates an array of size (5 * size of Bus object)
        buses[0] = new Bus(1, 1.2);
        buses[3] = new Bus(2, 2.2);
        // The array buses has the reference address to 2 bus objects in the 0th and 3rd index. It's null for the rest

        buses[1] = new Bus(buses[0]);
        // This uses the copy constructor we created

        // double arrays aka multidimensional arrays 
        int[][] matrix = new int[10][20];
        // this creates a 2d array with the first row having an array of size 10 and 2nd of size 20

        int[][][] matrix2 = new int[10][20][30];
        // 3d array

    }
}
