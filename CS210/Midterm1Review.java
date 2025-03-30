import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Midterm1Review {

    // int sum; Var can't be outside scope of a static method

    public static int sumAll(int[] array) {
        int sum = 0; // Always need to initialize variables inside method. int's default value is 0 if it's an instance variable or 
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }


    public static Boolean isPalindrome(int[] a, int[]b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Error, arrays must be of same size");
        } else {
            for (int i = 0; i < a.length; i++) {
                int j = b.length;
                if (a[i] != b[j-i]) {
                    return false;
                } 
                j++;
            }
        return true;
        }
    }


    public static void main(String args[]) {
        int[] testArray = new int[3];
        testArray[0] = 1;
        testArray[1] = 2;
        testArray[2] = 3;
        System.out.println(sumAll(testArray));  // This only works if the method is static

        // ArrayPractice array = new ArrayPractice();
        // System.out.println(array.sumAll(testArray));
        // If I make a non static method, then the method belongs to an INSTANCE of the class, not the class itself.
    

    // i++ returns the value of i, you do whatever operation with it, THEN it adds 1 to it.
    // ++i returns the value of i + 1
    // so if i = 10, and you do int j = ++i + i++, it does 11 + 11, NOT 11 + 12
    
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            
            // The order of this is i=0, then it checks if i<10, then it prints i, then it does i++.
            // Order it goes is like a V
        }
    }

    // Java iterator lets you traverse collection of elements witoht exposing structure of it. It has a hasNext(), next() and remove()
    //      import java.util.Iterator;
    //      class must implement the Iterable interface
    
        public class LinkedList<E> implements Iterable<E> {
            private Node<E> head;
            // Method to return an iterator
            @Override
            public Iterator<E> iterator() {
            return new LinkedListIterator<>(head);
            }
        }

        public class LinkedListIterator<E> implements Iterator<E> {
            private Node<E> currentNode;
            public LinkedListIterator(Node<E> node) {
            this.currentNode = node;
            }
            @Override
            public boolean hasNext() {
            return currentNode != null;
            }
            @Override
            public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E data = currentNode.data;
            currentNode = currentNode.next;
            return data;
            }
            }
    //      if we have an ArrayList<Integer> array, we can do Iterator<Integer> iter = array.iterator();
    //      Iterator is pass by value, so it's in a way an immutable object
    //      it.next() returns the current element, THEN iterates, so its like i++
    //      it.remove() removes it.next() 
    


}
