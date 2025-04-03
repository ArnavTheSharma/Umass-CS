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
    a1.color = {'r', 'e', 'd', '\0'};
    a1.size = 5;
    a1.weight = 10;

    printf("This apple's color is of color %s, of size %d, and of weight %d \n", a1.color, a1.size, a1.weight);

    struct apple a2 = {"green", 4, 8}; // initialize a struct.
    struct apple a3 = {.size = 3, .weight = 5}; // can also initialize specific fields 
    //      Note: Since we didn't specify the char array, the default will depend on scopy where it was declared. If declared outsided function all values initialized to '\0', and if inside as a local variable, all are just junk values.
    
    // We can also have an array of structs:
    struct apple a[5]; // Note we still need to put "struct apple" before it all.
    // to initialize the first apple, do:
    strcpy(a[0].color, "blue"); 
    a[0].size = 5;
    a[0].weight = 10;

    a[3].size = 4;
    a[3].weight = 8;
    // Note a[3]'s color and int values are junk value because we declare a[5] inside the function. Though, making it initialized with static would initialize it as if it was initialized outside function
    static struct apple a1[5]; 

    // in theory, we could use junk values for randomness, though it's not statistically pure randomness because the memory locations could be influenced by specific values, creating a bias.
    // If I pass a struct to a function by value, it copies the entire struct and puts it somewhere in memory. In jsva though, objects are always pass by reference.
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
    today1 = (struct date) {9, 10, 2023};
    today2 = (struct data) {.day = 1, .month = 2, .year = 2023};


    struct dateAndTime {
        struct date d;
        struct time t;
    };
    event (struct dateAndTime) = {{1, 2, 3}, {4, 5, 6}};



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



}