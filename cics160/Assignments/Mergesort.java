package umass.cics160.Assignments;
import java.util.LinkedList;

// Mergesort.java will implement a class called Mergesort. This class has three methods:
// ◦ main(String[] args) → void
// ◦ sort(LinkedList<Integer> ll) → LinkedList<Integer>
// ▪    returns a new linked list, with the integers in ascending order.
// ◦ makeNewList(LinkedList<Integer> original, int fromHere, int toHere) → LinkedList<Integer> newList
// ▪    creates a new list with the elements from original, from index fromHere to index toHere, inclusive
// ◦ merge(LinkedList<Integer> a, LinkedList<Integer> b) → LinkedList<Integer>
// ▪    performs the merging of two linked lists as part of the mergesort algorithm


public class Mergesort {

    public static LinkedList<Integer> sort(LinkedList<Integer> ll) {
        if (ll.size() < 2){
            return(ll);
        }
        int midpoint = ll.size() / 2;
        LinkedList<Integer> listA = makeNewList(ll, 0, midpoint-1);
        LinkedList<Integer> listB = makeNewList(ll, midpoint, ll.size()-1);

        LinkedList<Integer> newListA = sort(listA);
        LinkedList<Integer> newListB = sort(listB);
        return(merge(newListA, newListB));
    }

    public static LinkedList<Integer> makeNewList(LinkedList<Integer> original, int fromHere, int toHere) {
        LinkedList<Integer> newList = new LinkedList<>();
        for (int i = fromHere; i <= toHere; i++) {
            newList.add(original.get(i));
        }
        return(newList);
    }
    public static LinkedList<Integer> merge(LinkedList<Integer> a, LinkedList<Integer> b) {
        LinkedList<Integer> c = new LinkedList<>();
        while(a.size() > 0 && b.size() > 0) {
            if (a.get(0) > b.get(0)) {
                c.add(b.get(0));
                b.remove(0);
            }
            else {
                c.add(a.get(0));
                a.remove(0);
            }
        }
        
        c.addAll(a);
        c.addAll(b);
        return(c);
    }

    public static void main(String[] args) {
        LinkedList<Integer> test = new LinkedList<>();
        test.add(1222);
        test.add(222);
        test.add(32);
        test.add(4);
        test.add(0);
        test.add(4);
        System.out.println("Old: " + test);
        test = sort(test);
        System.out.println("New: " + test);
    }   
}
