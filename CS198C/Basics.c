// C basics
#include <stdio.h> // std standard, io input/output, .h header file = library that has fn's for input and output. Installing gcc also comes with header files like this
    //  header file .h declare signature of functions (fn name, param types, # params, return type?) 
    // The header files only have the declarations of functions, consts, macros, structs. These tell the linker it exists, and the linker will find the actual implementation in library files
    // To write a custom library, declare the functions in the header file, write the implementation in src.c, compile it into obj and using that obj code create a library file. Then you can do #include "custom.h"

// int main(void) { // specifying void means it takes no params. If we leave it as main(), it means main takes na unspecified number of arguments. In other words, main() allows arguments but doesn't enforce them.
// though if you need to process command-line arguments, define int main(argc)
int main(int argc, char *argv[]) { // argc arg count tells # of characters, argv = arg vectors = address of string address pointers.
    // argv[0] = name of program ./myprogram (when you compile to done.exe, you need to write ./myprogram first to run it...). 
    // Then argv[] will contain an arr of string addresses that user types and the length of it will go to argc. 
    // *argv[] = array of pointers. 

    // passing array as parameter, either arr[] or arr[5] to specify sizes that'll be accepted
    //      Or do *arr (pointer to a), since arr[] itself already treated as a pointer to first element
    //      *arr =/= *arr[] since 2nd is an array of pointers. First is pointer to 1 array. 

    int a,b,c,d; // can't do this in java. 
    
    for (int i=0; i<=10; i++) {
        printf(i); // this is wrong, since printf expects a string, which is why we need Format specifiers
        printf("current index is: %d\n", i-5);
        // doing %2d will ensure whatever int (because %d) will take up 2 spaces. If 1 digit number it'll do " 4" (to the right) instead of "4". If 2 digit or 3 digit or more, it'll js print normally. To pad with "0" instead of " " i.e. to get "04" instead of " 4" just do %02d. Can only pad with either " " or "0".
    }

    int age = 20;
    char name[] = "arnav"; // string = char array. Last element always \0 null char to avoid reading junk value
    printf("Hello world, my name is %s and I am %d years old. \n", name, 19); // \n just for new line. $s string, $d int. 
    char x = "f";
    printf("%c\n", x); 


    // scanf: basically like int(input("Put your message: "))
    int n = 0;
    printf("Enter a number to input:\n");
    scanf("%i", &n); // & gives memory address of variable that scanf needs to store the value into. YOU NEED & FOR SCANF. Without the &, you're just passing a copy of the variable to scanf, which doesn't help nor modify the value of n. 

    

    // if statement and switch. When we wanna make multiple if statements look cleaner, use switch!
    
    // int n = scanf("%i", &n);  // WRONG FOR 2 REASONS: 1) n isn't declared yet, and 2) scanf is a fn that returns whether it executes correctly. Scanf itself stores value into n, so don't do n = scanf(). Just scanf("", &n); 
    int n;
    int remainder;
    printf("Enter 1 number below:\n");     
        scanf("%d", &n); // Note if scanf takes 2 numbers, it does scanf("%d %d", &a, &b); and user input in format of "1 2", not "1, 2"
    switch (n % 3 == 0) { // switch (expression)
        case 0: // the value of the expression = 0
            printf("n is divisible by 3\n");
            remainder = 0;
            break; // exit switch statement, otherwise it'll keep going to next cases/
        
        case 1: 
            printf("n/3 has remainder 1\n");
            remainder = 1;
            break;
        
        case 2:
            printf("n/2 has remainder 2\n");
            remainder = 2;
            break;
        
        default: // if none of cases are met 
            printf("n isn't divisible by 3 at all\n");
            break; // last statement ever so technically don't need break, but good practice.
    }



    // ternary operator also in c: (condition) ? (true) : (false);
    // with for loop, remember that after initialization, it does a V from the condition to what's inside loop to the iteration i.e. i++.



    // easy ways to have strings (compiler does null char at end for you)
    char word[] = "Hello";
    // For variable string length need dynamic memory allocation (malloc, calloc)

    return 0; // means program ran successfully. In modern versions it's implicitly included if you don't write it anyway. There are custom values depending on the number, which can help other programs and scripts interpret why it failed.  
}


// Multiplication of 2 binary numbers acts as a logical AND operator (since 1*1 = 1 and everything else is 0)
//      Doing + is the OR operator and for XOR, we do subtraction. For XOR if we get 0 - 1 = -1, the !!(-1) is 1 so it works out! 
// !! is called double NOT and it converts a value into a strict boolean of 0 or 1. The negation of 42 is 0, and negation of 0 is 1, so !!42 it returns 1.


