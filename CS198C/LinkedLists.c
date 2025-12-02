// pointers + linked lists

int main(void) {
    // 2 ways to have null pointers (pointing to nothing in memory)
    int *ptr = NULL;
    int *ptr2 = (void *) 0; // casting 0
        
    // explicit type conversion (where implicit is where compiler does lower ranked data types to higher):
    int a = 2;
    float a_to_float = (float)a;    
    

    // immutable ptr vs immutable value?
    char * const charPtr = &a; // immutable pointer to mutable char
    const char *charPtr = &a; // mutable pointers to immutable char

    // ptr arrays
    int arr[5];
    arr = &arr[0]; // which is why you can just pass the name as address
    // does NOT work same for structs. 


    // ptr arithmetic
    *ptr = &arr[0];
    ptr += 2; // if mem address is 2000 for arr[0], doing ptr +=2 doesn't get 2002 but instead 2008 (the true address of arr[2]). It does 8 since 4*2


    // malloc: dynamic memory allocation (allocates memory on the stack during runtime vs compile time)
    //      malloc allocates n number of bytes and returns a void pointer (which you type cast to desired type) to the beginning. However the contents of these will be garbage values, so use calloc to initialize it all with 0.
    //      stack is for function name info and local variables that will get deallocated after function call ends. Heap is a section of RAM used for dynamic memory allocation. 
    void *malloc(size_t size);
    int *p = (int*) malloc(4);
    free(p); // free pointer after its done, otherwise memory leak

    // linked lists as defined by a struct:
    struct node {
        int value;
        struct node *next; // pointer to next object, which is of type struct node
    };

    struct node n1;
    struct node n2;
    n1.value = 2;
    n1.next = &n2;

    int val = n2->value ; // pointer then -> then field name. Returns value of field name
    
    // can also navigate through a linked list
    // n1 to n2 to n3
    struct node n3;
    n1.value, n2.value, n3.value = 3;
    n1.next = &n2;
    n2.next = &n3;
    n3.next = NULL;

    n1.next->next->value; // n1.next = address pf n2, then -> accesses field of n2 (specifically its next field) which has value of n3's address. Then doing -> on that accesses value field of n3.
    

}