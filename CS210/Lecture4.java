// Questons:


import java.util.LinkedList;

public class Lecture4 {

    public int[] array = new int[5];

    
    public void add(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i > array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    public String remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i > array.length) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    public static void main(String[] args){
        // If we want to reverse the elements in a java array, it would be a pain to write the for loop, however with linked lists, we have a del and put method, so we can do for example
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // [1, 2, 3, 4]
        // We can do list.put(list.del(list.getLength), 0)
        //      Not exact syntax but just to get general idea. We basically take the lsat element and put it before the first element. 
        //      List.del deletes and returns the element we choose it to delete. 
        //      The first iteration returns [4, 1, 2, 3]



        // If we declare an array (String[] array = new String[3]) we need a method that returns size (not length, since length tells the max capacity, not current number of elements)
        // Java doesn't have size by default (with arrays, not ArrayList), so we need to implement it using a size variable that increments by 1 every time we .add()
        //      If we have a size method, since there's also no formal .add method (we instead need to say sm like list[i] = 2), we can just write list[size] = 2
        //  We can't increase the array size, we instead find the JVM to give us a new memory location with double the size and copy the values onto there
        

        // appending is ammortized to O(1) not O(2n) when overflowing --> every once you double the memory, however on average since that is less common it ammorties to O(1)

        // When appending to a certain index, we have to check if the index is in bounds and if the array is at capacity

    }
    
}

// == for java.util.Arrays compares memory address, wheread .equals() 