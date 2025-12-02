// arrays, static variables
#include <stdio.h>

int main(void) {
    const int c = 2; // = final in java (read-only var AKA immutable)
    const int arr[5] = {1, 2, 3, 4, 5}; // modifying ith element will raise error (in JS you can modify elements but can't change address of ptr)
    int x = 100;
    int a[5] = {[4] = x+1, [3] = 4, [2] = 3}; // this is called using designated initializers, where we can initialize specific indices. The rest are 0 by default again for ints[], not null. 
    // Note we CANNOT do [2] = [3] within that since [3] isn't initialized yet (we're INSIDE the initializer!), but we can do it in a later line. 

    // 2D Array: int b[3][4] = {{1,2,3,4}, {5,6,7,8}, {9,0,1,2}}; // 3 rows and 4 columns.
 
    // Note: We need to define a function before we use it (well of course) but this includes the main method! In java it's fine to put main anywhere but in c, we need to define any other functions used in main() BEFORE it.
    //      "Previous implicit declaration of function" error.
    // Note: In the same way we can just declare an int, "int a;", we can do the same with function as show:
    int calculateTs(int n);
    
    int function(void) {
        return 1 + calculateTs(2); // usually without line 15 (the function declared) this would return an error, but now it's valid.
    };

    int calculateTs(int n) {
        return n*(n^n);
    };
    



    char name[] = "Arnav"; // name[] not char[] (in Java convention is opposite, but in Java both work). Js remember it's switched for java and c.
    char name2[20] = "Arnav"; // first 5 are used, rest are null for chars. 

    // Note we can't compare strings like in Java, since strings in C are just char[]. 
    //      The format specifier for strings = char arr is %s, and chars its %c.
    //      End of the char a[] is actually a "\0" character, which is a null terminator to indicate end of string.
    //      So if we do char a[] = "hello";, it's actually 6 bytes long, not 5.
    //          We do this to denote end of the string. We dont behind the scenes js iterate through the length of the array since that's more complex and \0 is just 1 byte.
    
    // sizeof(name) returns size in bytes of array entire array. Therefore divide sizeof(name[0]) to get len, though sizeof() adds 1 extra byte for null terminator. Instead use strlen(name).
    //      Unlike in java where there's an Out Of Bounds exception, in C if we go out of bounds, it just returns a random value (this is junk value or garbage value), so be careful and use strlen(name) instead.

    char str[100] = "hello world";
    scanf("%s", str); // VALID: if we do scanf("%s", &str), it won't work since str is already a pointer to the first element of the array.
    scanf("%s", &str[0]); // VALID: str[0] is first element and we js want the ptr to first element.
    scanf("%s", &str); //  INVALID: str is already a pointer to the first element. Can't do a pointer to a pointer?

    char word1[] = "hello";
    char word2[] = {'h', 'e', 'l', 'l', 'o', '\0'}; // Note in char we need to do ' ' and not " "
    printf(word1 == word2); // compares memory addresses. To compare we need to iterate through each char
    //      Note: with this iteration, we need to check at end if word[i] != '\0' to check for out of bounds.
    //      We could also use strcmp() function from string.h library:
    printf(strcmp(word1, word2) == 0); // return a 0 or 1.



    char str[81] = "hello world";
    scanf("%s", str); // If user types 23, then it puts 23 in str and '\0' after it, therefore now str is 2 3 \0 l o w o r l d \0, not "23llo world \0"
    str[2] = 'X'; // Now if I replace that first '/o' after 23 with X, then str is now "23Xllo world". Now the old '\o' at index 2 is gone.

    printf("%s", &str[2]); // print the string from middle. So output is Xllo world \0 

    





    // static vs automatic variables
    //      Static in a Java class means the same value is for every instance of the class
    //      In c, a static var for functions will be the same for every call of the function, so updating that 1 time will update it for the rest too since it belongs to the fn, not each fn call.

    // auto just means local var. Don't need to explicitly use that keyword


    return 0;
}



