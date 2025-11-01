#include <stdio.h>

void main(int argc, char *argv[]) {
    printf("number of args: %d", argc);
    for (int i = 0; i<argc; i++) {
        printf("\n argument # %d, = %s", i, argv[i]);
    }
}