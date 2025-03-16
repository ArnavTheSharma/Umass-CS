// Questions: Is the head list a variable that points to the first element? Or is it set as the first node. To remove the first element don't we do head.next = head.next.next? 

//Lecture5 extension

public class doublyLinkedList {
    
}

public class StringList {
    public String data;
    public StringList next; // we can't access memory directly like with C, so we do the next element to be of type StringList
    
    public void StringList(String data) { // will point to null by default
        this.data = data;
    }
    
    public void StringList(String data, StringList next) {
        this.data = data;
        this.next = next;
    }
    
}

public StringLinkedList extends StringList {
    
}

// to add a node to the last, you want to iterate through the list till you next node.next = null, then set that lastNode.next to be the node you want to add
//      If size = 0, theres no node that points to null, so we just create a new node and point head to new node
//      Or you could initially ask does head point to null

// To get not just the next node but next to next node, do node.next.next


// If you add a node to the end of a list in the wrong order, you would end up making the new node point to itself whilst losing the memory address of the next node
// head node is the first node itself, not whatever points to the first node.
// To remove node, find the index of the node you want to remove, find the node right before that, and set that nodeBefore's next = nodeBefore.next.next

// Accessing an array's element given an index is a constant time method, but not for LinkedList
//      To print all the elements in a LinkedList, we actualy have to run a for loop through the length and when doing list.get(i), it actually has to iterate through the entire list from 0 to i over and over again, making it O(n^2) 

// Some linked list implementations have a tail pointer in addition to the head one, tail points to the last element


// GENERICS
// Java lets us write generic classes and methods to can operate on a variety of data types.
// We use <a, b> instead of (int a, int b) 
//      < > = angle brackets
public class Pair <a, b> {
    // A and B are types, not variable names
    A first;
    B second;

    public Pair(A a, B b) { // constructor
        first = a;
        second = b;
    }

    public A getFirst() { 
        return first; 
    }

    public B getSecond() { 
        return second;
    }

    
}

public interface List<E> {
    public void add(E e);
    public void add(int index, E e) throws IndexOutOfBoundsException;
    public E get(int index) throws IndexOutOfBoundsException;
    public E remove(int index) throws IndexOutOfBoundsException;

    public int size();
}

// bid = new Pair <> ("Orange", 12.3);    
// Note: the types within must be objects, can't be primitive 
