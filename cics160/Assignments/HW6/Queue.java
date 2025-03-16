// package umass.cics160.Assignments.HW6;
import java.util.LinkedList;

public class Queue<T> {
    LinkedList<T> queue = new LinkedList<>();

    public void enqueue(T e) {
        queue.add(e);
    }

    public T dequeue() {
        return queue.pop();
    }
    
    public T first() {
        T e = queue.get(0);
        return e;
    }

    public boolean isEmpty() {
        if (queue.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public T peek() {
        return(queue.peek());
    }

    public static void main(String args[]) {
    }
}
