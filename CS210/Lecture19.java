import java.util.List;
import java.util.LinkedList;

public class Lecture19 {
    // Treaps are BSTS by value and Heaps by priority
    //      We can first take all the elements and sort it by priority and make a BST of the values with that
    // Insertions in treaps: We just keep going up if priority
    // Deletions in treaps: We assign the node's priority to be 0 and do the operations we've used to remove it

    // Efficient way to ensure a BSTs height is log(n) instead of n (from worst case scenario in strictly ascending order) is to randomize the order you put it in. So it will be 1 of the n! diff ways you can insert it
    //      We can do this by assigning priority of each value to be a randon num between 0 and 1 then making the treap (after sorting them by priority)
    // Application of treap: Without priority, if we already have 1000 existing items and want to insert it, on worst case it could be going only to the right like worst case and thus not being efficient
    //      Instead w/ treaps, we assign the new item a new randomized priority and insert based on that, which will ensure it's still O(log(n))
    
    // They say for all this, it's AVERAGE DELETION TIME O(logn), but average over what
    //      If I'm your adversary and want to stress test your algorithm for tree (e.g. finding just 1 case where your algorithm takes a very long time) they would have a hard time with this randomized algorithm (unpredictable), which we use to avoid worst cases
    
    // If a specific value is accessed alot, we want to give it a higher priority, which we do by generating a new randon num for the value and assigning the priority to be the max of the old random num and new random num


    // Treap Bulk operations: Join and Split:
    // Join: If we want to join 2 treaps, we don't just add every element in 1 into other (worst case would be n of Tree2 times log(m) of Tree1)
    //      Instead we create a new node with priority 0 (both children of it are the 2 treaps) and delete that node.
    //          Deleting a node, we basically keep doing rotations on the tree (based on the priority number, NOT value) and go until the deleting node is at leaf, and simply remove it.
    // Split: To split a big treap into 2 sub treaps. We insert a value with a priority greater than the highest priority value as a leaf and bubble it up, then once thats the new root we have the 2 children as 2 diff treaps
    
    
    // Comparable based sorting:    
    // mergesort: If we have 2 sorted lists in descending order and want to merge, we take the max of the top of the stack and add it to the new list
    //      If we have 2 sub arrays, we simply keep track of 2 ints, i and j, which represent the current index
    //          This is pretty much how iterator object works
    //      This doesn't depend on if it implements Iterable, only if it implements Comparable
    public static <E extends Comparable<E>> List<E> merge(List<E> left, List<E> right) {
        List<E> m = new LinkedList<>(); // with linkedList the remove operation is O(1) and ArrayList is O(n) because we have to shift every element by 1 after deleting
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.get(0).compareTo(right.get(0)) <= 0) {
                m.add(left.remove(0)); //remove smaller of the first elements of left/right
            } else { // and place it at the end of 𝑚
                m.add(right.remove(0));
            }
        }
        m.addAll(left); // move remaining elements to end of 𝑚
        m.addAll(right);
        return m;
    }


    // mergesort: Divide list into 2 sublists and run mergesort on those left and right. At base case of len 1 return the list. After we do mergesort on left() and right() then we run the operation above onto the 2 sorted lists
    //      int half = len/2
    // interesting idea: doing mergesort using only 1 list

    
    // time complexit of Merge Sort:
    // T(n) is amount of "work" needed to run mergesort on n items. T(1) = 1 --> not 0 because constant time operation is always O(1)
    //      Merging 2 sublists of size n/2, the amount of work T(n) = T(n/2) + T(n+2) + n = 2T(n/2) + n. We do +n after running the merge algorithm we made already (the one with checking the head of 2 stacks) which is n total operations
    //          Note: This kinda assumes n = 2^k for some int k, math gets hairy otherwise
    // Proof by Induction that T(2^k) = (k+1)*2^k
    // I.S. is T(2^{k+1}) = 2*T(2^{k+1} / 2) + 2^{k+1} =  2*T(2^k) + 2^{k+1} 

    

}
