// structs
#include <stdio.h>
#include <string.h> // for strcpy
// in java, you can have a class with fields of instance variables and instance methods, but in c there aren't classes, only structs.
//      struct is a type, like how an int is a type, but a struct is a collection of different types.
//      array is a struct of all the same types. 

struct apple {
    char color[15]; // can have arrays in structs too!
    int size;
    int weight;
}; 

int main(void) {
    struct apple a1;
    // a1.color = "red"; // remember this is illegal, we cant assign a string to char array. Use strcpy (string copy)
    // a1.color = {'r', 'e', 'd', '\0'};  // close, but also illegal. can only do this format in declaration time like with a2 in line 24 later
    strcpy(a1.color, "red"); // a1.color is a char[] (look at type sig in declaration), so a1.color is a ptr
    a1.size = 5;
    a1.weight = 10;

    printf("This apple's color is of color %s, of size %d, and of weight %d \n", a1.color, a1.size, a1.weight);

    struct apple a2 = {"green", 4, 8}; // initialize a struct.
    struct apple a3 = {.size = 3, .weight = 5}; // can also initialize specific fields 
    
    // We can also have an array of structs:
    struct apple a[5]; // "struct apple" is the type. a is the name of arr
    // to initialize the first apple, do:
    strcpy(a[0].color, "blue"); 
    a[0].size = 5;
    a[0].weight = 10;

    a[3].size = 4;
    a[3].weight = 8;
    // Note a[3]'s color value are junk value because we declare a[5] inside the function. Though, making it initialized with static would initialize it as if it was initialized outside function
    static struct apple a1[5]; 

    // in theory, we could use junk values for randomness, though it's not statistically pure randomness because the memory locations could be influenced by specific values, creating a bias.
    // If I pass a struct to a function as a param, it copies the entire struct and puts it somewhere in memory. In jsva though, objects are always pass by reference.
    //      Passing an array to a function is always pass by reference, because the array name is rly just a ptr to the first element like in Java for objects.
    
    // I can also have a struct that has multiple other structs (e.g. data struct and time struct combined to form a dateAndTime struct)
    struct date {
        int day;
        int month;
        int year;
    };

    struct time {
        int hour;
        int minute;
        int second;
    };
    struct date today1 = (struct date) {9, 10, 2023};
    struct date today2 = (struct date) {.day = 1, .month = 2, .year = 2023};


    struct dateAndTime {
        struct date d;
        struct time t;
    };
    struct dateAndTime e = (struct dateAndTime) = {{1, 2, 3}, {4, 5, 6}};



    // Enum: easy way to assosiate diff constants together into a type
    enum Day {
        Sunday, // 0
        Monday, // 1
        Tuesday, // 2
        Wednesday, // 3
        Thursday, // 4
        Friday, // 5
        Saturday // 6
    }; 
    // Note the first value of the enum is assigned 0 by default. Don't needa increment by 1.
    enum Day today = Wednesday; // today is an int,  now.







    // ptr: 
    int value = 2;
    int *ptr = &value; // int *ptr to indicate it points to an int
    int **ptrs_ptr = &ptr; // ** to indicate it's a ptr to a ptr

    int v = *ptr; // dereferences ptr and returns value it points to
    int v2 = **ptrs_ptr; // need to dereference twice

    // pointer to a struct is pointer to the first data type. 
    struct date today = {9, 25, 2004}; 
    struct date *datePtr; // initialize a pointer that'll point to a date struct
    datePtr = &today; // assign value to today's address
    (*datePtr).day = 21; // dereference pointer first to get the value, then you can do .day
    datePtr->day = 21; // traditional syntax for pointer to struct to the member name instead of previous line


    // for x86 32-bit archetecture, need 4 bytes to represent an address. 
    // for x86-64, or 64-bit architecture (including the intel core series i3 i5 i7 and pentium) need 8 bytes to represent an addres.


    // when passing structs to a function, a copy of its values is passed, so you can change the value of the member field by doing s.member = 3;


    struct weather {
        int temp;
        float precipitation;
    };

    // Getter function for the temperature field
    int get_temp(struct weather *w) {
        return w->temp; 
    };

    return 0;
}