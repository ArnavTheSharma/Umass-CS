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
//      First: after compilation compiler checks for any syntatic or semnatic errors, and AFTER those are fixed it translates each statement into lower form (assembly)
//      Second: Assembler takes each assembly statement the compiler generates and converts it into OBJECT CODE (binary format) so file.c --> file.o
//      Third: Building: Now this obj code is ready to be linked (if any other programs were used in this program, which includes imported libraries). 
//      Lastly: final form = a.out in Unix by default, and windows .exe
//          Just do a.out in terminal to run
// IDE = windows-based program that allows you to easily manage large software programs, edit files in windows, and compile, link, run, and debug your programs
//      https://learning.oreilly.com/library/view/programming-in-c/9780768689068/ape.html
// Interpreter --> line by line analyzes and immediately executes
//      Easier for debugging since oyu know where it failed, but slower
//      BASIC and Java are two programming languages in which programs are often interpreted and not compiled. Other examples include the Unix system’s shell and Python. Some vendors also offer interpreters for the C programming language.

