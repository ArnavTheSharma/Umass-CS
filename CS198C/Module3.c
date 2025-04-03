// #include <stdio.h>

int main(void) {
    int counters[5] = {1,2,3,4}; // the 5th elemennt is 0, NOT VOID, by default.
    int x = 100;
    int a[5] = {[4] = x+1, [3] = 4, [2] = 3}; // this is called using designated initializers, where we can initialize specific indices. The rest are 0 by default again, not null. 
    // Note we CANNOT do [2] = [3] within that since [3] isn't initialized yet (we're INSIDE the initializer!), but we can do it in a later line. 

    // 2D Array: int b[3][4] = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}}; // 3 rows and 4 columns.
    
    // Note: We need to define a function before we use it (well of course) but this includes the  main method! In java it's fine to put main anywhere but in c, we need to define any other functions used in main() BEFORE it.
    //      "Previous implicit declaration of function" error.
    // Note: In the same way we can just declare an int, "int a;", we can do the same with function as show:
    int calculateTs(int n);
    
    int function(void) {
        return 1 + calculateTs(2); // usually without line 14 (the function declared) this would return an error, but now it's valid.
    }

    int calculateTs(int n) {
        return n*(n^n);
    }
    



    char name[] = "Arnav"; // name[] not char[] (in Java convention is opposite, but in Java both work). Js remember it's switched for java and c.
    char name2[20] = "Arnav"; // first 5 are used, rest are null. 

    // Note we can't compare strings like in Java, since strings in C are just char[]. 
    //      The format specifier for strings is %s, and chars its %c.
    //      End of the char a[] is actually a "\0" character, which is a null terminator to indicate end of string.
    //      So if we do char a[] = "hello";, it's actually 6 characters long, not 5.
    //          We do this to denote end of the string. We dont behind the scenes js iterate through the length of the array since that's more complex and \0 is just 1 byte.



    return 0;
}