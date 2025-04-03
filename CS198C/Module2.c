// C basics
#include <stdio.h> // std standard, io input/output, .h header file = library that has fn's for input and output.

int main(void) { // specifying void means it takes no params. If we leave it as main(), it means main takes na unspecified number of arguments. In other words, main() allows arguments but doesn't enforce them.

    int a,b,c,d; // can't do this in java. 
    
    for (int i=0; i<=10; i++) {
        printf(i); // this is wrong, since printf expects a string, which is why we need Format specifiers
        printf("current index is: %d\n", i-5);
        // doing %2d will ensure whatever int (because %d) will take up 2 spaces. If 1 digit number it'll do " 4" (to the right) instead of "4". If 2 digit or 3 digit or more, it'll js print normally. To pad with "0" instead of " " i.e. to get "04" instead of " 4" just do %02d. Can only pad with either " " or "0".
    }

    int age = 20;
    printf("Hello world, my name is %s and I am %d years old. \n", name, 19); // \n just for new line. $s string, $d int. 
    char x = "f";
    printf("%c\n", x); 


    // scanf: basically like int(input("Put your message: "))
    int n = 0;
    printf("Enter a number to input:\n");
    scanf("%i", &n); // & gives memory address of variable that scanf needs to store the value into. YOU NEED & FOR SCANF. Without the &, you're just passing a copy of the variable to scanf, which doesn't help nor modify the value of n. 

    

    // if statement and switch. When we wanna make multiple if statements look cleaner, use switch!
    
    // int n = scanf("%i", &n);  // this doesn't work since a) n isn't declared yet, and b, you're not storing what scanf returns to n, scanf does the storing for you 
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



    return 0; // means program ran successfully. In modern versions it's implicitly included if you don't write it anyway. There are custom values depending on the number, which can help other programs and scripts interpret why it failed.  
}


// Multiplication of 2 binary numbers acts as a logical AND operator (since 1*1 = 1 and everything else is 0)
//      Doing + is the OR operator and for XOR, we do subtraction. For XOR if we get 0 - 1 = -1, the !!(-1) is 1 so it works out! 
// !! is called double NOT and it converts a value into a strict boolean of 0 or 1. The negation of 42 is 0, and negation of 0 is 1, so !!42 it returns 1.
// if we do:
int x = 6;
int y = 6;
int z = (x % 2) ? y + 10 : y - 5;
// The value of (x % 2) is 0 and therefore the statement is false, NOT true. If we did x%2 == 0 it would do opposite.

