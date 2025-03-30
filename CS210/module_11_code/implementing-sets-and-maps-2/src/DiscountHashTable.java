import java.util.Dictionary;
import java.util.LinkedList;

public class DiscountHashTable<E> implements DiscountSet<E> {
    LinkedList<E>[] array;
    int n;
    int size;

    public DiscountHashTable() {
        n = 1023;
        array = (LinkedList<E>[])new LinkedList[n];
    }

    public DiscountHashTable(int n) {
        array = (LinkedList<E>[])new LinkedList[n];
        this.n = n;
    }

    @Override
    public boolean add(E e) {
        int i = Math.abs(e.hashCode());
        // note i is definitely non-negative now, no other guard needed

        if (array[i % n] == null) {
            array[i % n] = new LinkedList<>();
        }

        boolean added = false;

        // if we get this far, the cell contains a linked list!
        if (!array[i % n].contains(e)) {
            added = array[i % n].add(e);
            size++;
        }

        return added;
    }

    @Override
    public boolean contains(E e) {
        int i = Math.abs(e.hashCode());

        if (array[i % n] == null) {
            return false;
        }
        return array[i % n].contains(e);
    }

    @Override
    public boolean remove(E e) {
        int i = Math.abs(e.hashCode());

        boolean removed = false;
        if (array[i % n] != null) {
            removed = array[i % n].remove(e);
            if (removed)
                size--;
        }
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    // public E get(E e) {
    //     int i = Math.abs(e.hashCode());
    //     for (E element: array[i % n]) {
    //         if (e.equals(element)) {
    //             return element; // NOT e! Very important for HashMap!
    //         }
    //     }
    //     return null;
    // }


    public E get(E e) {
        int i = Math.abs(e.hashCode());
        if (array[i%n] != null){
            for (E element: array[i % n]) {
                if (e.equals(element)) {
                    return element; // NOT e! Very important for HashMap!
                }
            }
        }
        return null;
    }

    public void ReportAll(){
        for(LinkedList<E> L:array){
            if (L !=null){
                for (E element: L){
                    System.out.println(element);
                }
            }
        }
    }
}

