// assembly language: enabled the programmer to work with the machine on a slightly higher level than 1's and 0's. Instead of having to specify sequences of binary numbers to carry out particular tasks, the assembly language lets us use symbolic names/english commands to do the same operations and refer to specific memory locations. 
// A special program, known as an assembler, translates the assembly language program from its symbolic english format into the specific machine instructions of the computer system
//      one-to-one correspondence between a statement and machine instruction = low-level language

// portable is if a program can still run on different processor types and OS
//      
//      processor architecture (like x86, ARM, or MIPS) - 
// FORTAN is the next one and it was slightly higher level, as 1 command could now execute multiple machine instructions versus just a 1 to 1

// OS handles the input and output operations, and manages computer systems resources and execution of programs

// Unix OS in different flavors: Mac OS X, Linux, 
//      Written primarily in C, so we don't needa worry about architecture of computer and computer system
//      Microsoft Windows = another OS/ Pentium-compatable processors - Intel's x86 architecture it's a series that supports multi-core and later hyper-threading. 
//      Modern x86-64 CPUs from Intel and AMD remain Pentium-compatible
//      Pentium = microprocessor brand 

// C compilation:
//      GCC is a comomon C compiler (part of GNU Compiler Collection)
//      vi file.c --> Unix text editor
//      gcc file.c to compile
//      First: after compilation compiler checks for any syntatic or semnatic errors (syntax is grammar, semantic is error in meaning of code, like incorrect type assignments. Semantic will run but not as intended), and AFTER those are fixed it translates each statement into lower form (assembly)
//      Second: Assembler takes each assembly statement the compiler generates and converts it into OBJECT CODE (binary format) so file.c --> file.o. 
//      Third: Building: Now this obj code is ready to be linked (if any other programs were used in this program, which includes imported libraries). 
//      Lastly: final form = a.out in Unix by default, and windows .exe
//          Just do a.out in terminal to run
// Interpreter --> line by line analyzes and immediately executes
//      Easier for debugging since you know where it failed, but slower
//      BASIC and Java are two programming languages in which programs are often interpreted and not compiled. Other examples include the Unix system’s shell and Python. Some vendors also offer interpreters for the C programming language.





// Falsy values (aka how are non-boolean data types interpreted in a boolean context)
//      0 is falsy, and any other int is truthy
//      A null pointer is falsy

// compiling code: gcc src.c -o output
//      compile the src code from src.c into a -o format with name output (so it'll generate output.exe)
//      To generate object files (so files without linking) use -c instead, and to manually link those then do -o

// Multiplication of 2 binary numbers acts as a logical AND operator (since 1*1 = 1 and everything else is 0)
//      Doing + is the OR operator and for XOR, we do subtraction. For XOR if we get 0 - 1 = -1, the !!(-1) is 1 so it works out! 
// !! is called double NOT and it converts a value into a strict boolean of 0 or 1. The negation of 42 is 0, and negation of 0 is 1, so !!42 it returns 1.

// void = absence of a data type
//      int main(void) = no params accepted. int main() = unlimited.
//      void *ptr = &x; is a pointer to any data type
// NULL pointer means ptr doesn't point to anything.