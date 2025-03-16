// Questions: 

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

// Accessing an array's element given an index is a co