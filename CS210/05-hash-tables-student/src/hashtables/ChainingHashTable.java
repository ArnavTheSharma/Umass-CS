package hashtables;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of HashTable.

 * This implementation uses chaining to resolve collisions. Chaining means 
 * the underlying array stores references to growable structures (like 
 * LinkedLists) that we expect to remain small in size. When there is a 
 * collision, the element is added to the end of the growable structure. It
 * must search the entire growable structure whenever checking membership
 * or removing elements.
 * 
 * This implementation maintains a capacity equal to 2^n - 1 for some positive
 * integer n. When the load factor exceeds 0.75, the next add() triggers a
 * resize by incrementing n (by one). For example, when n=3, then capacity=7.
 * When size=6, then load factor ~=0.86. The addition of the seventh item would
 * trigger a resize, increasing the capacity of the array to 15.
 */
public class ChainingHashTable<E> implements HashTable<E> {
    int n; // SelfNote: this is for our computeCapacity so we don't resort to multiplying by 2 every time for enlarging
    int capacity; 
    int size; 
    public LinkedList<E>[] table; 
    
     /* Instantiate a new hash table. The initial capacity should be 7.
     */
    
    public ChainingHashTable() {
        // TASK 1: Ah, the humble constructor. Set `n` to 3.         
        // Initialize the table with just enough room for now — think of it as the
        // cozy starter home of hash tables.
        this.n = 3; // SelfNote: this is for our computeCapacity so we don't resort to multiplying by 2 every time for enlarging
        this.size = 0;
        this.capacity = computeCapacity();
        this.table = new LinkedList[this.capacity];
    }

    /**
     * Instantiate a new hash table. The initial capacity should be 
     * at least sufficient to hold n elements, but must be one less
     * than a power of two.
     */
    public ChainingHashTable(int n) {
        while (this.capacity < n) {
            this.n += 1;
            this.capacity = computeCapacity();
        }
        this.table = new LinkedList[this.capacity];
        // TASK 2: This constructor requires some mathematical magic. Compute
        // `n` as if you were a sorcerer seeking the perfect table size.
        // The result? A capacity that’s *just right* for your element-hungry
        // table.
    }

    @Override
    public int capacity() {        
        // TASK 3: Here’s the easy one. Return the capacity like a proud parent
        // showing off their child's height chart.        
        return this.capacity;
    }

    @Override
    public int size() {
        // TASK 4: How big is your hash table? This method knows.         
        // Return the size like you're counting jellybeans in a jar — except the
        // jar is your data structure.        
        return this.size;
    }

    @Override
    public double loadFactor() {
        // TASK 5: Calculate the load factor like you're a weather forecaster,
        // predicting the chance of resizing.        
        // Hint: if it’s above 0.75, it’s going to be a resize storm!
        return (double) size() / (double) capacity();
    }

    public void enlarge() {
        this.n += 1;
        this.capacity = computeCapacity();
        LinkedList<E>[] helper = new LinkedList[this.capacity];

        for (int i = 0; i < this.table.length; i++) { // iterate through each bucket in old table
            if (this.table[i] != null) { // if bucket isn't empty
                for (E j : this.table[i]) { // iterate through each element in this unempty bucket
                    int k = j.hashCode(); // generate hashcode for each element
                    k = k % this.capacity; // this.capacity is the newly enlarged capacity
                    
                    if (helper[k] == null) { // if new bucket in helper is empty
                        helper[k] = new LinkedList<E>();
                        helper[k].add(j);
                    } else { 
                        if (!helper[k].contains(j)) { // check if element isn't already in new bucket
                            helper[k].add(j);
                        }
                    }
                }
            }
        }
        this.table = helper;
    }
    
    @Override
    public boolean add(E e) {
        // TASK 6: Add an element to the hash table. If things get too crowded
        // (load factor > 0.75), sound the alarm and call `enlarge()`.        
        // Think of it like adding chairs to a party—don't let guests (elements)
        // stand!        
        if ((this.size+1)/this.capacity > 0.75) {
            enlarge();
        } 

        int i = e.hashCode();
        i = i % this.capacity;
        if (table[i] == null) {
            table[i] = new LinkedList<E>();
            table[i].add(e);
        } else {
            if (!(this.table[i].contains(e))) { // check if element isn't already in new bucket
                this.table[i].add(e);
            } else {
                table[i].remove(e);
                table[i].add(e);
                return false;
            }
        }
        this.size += 1;
        return true;
    }

    @Override
    public boolean remove(E e) {
        // TASK 7: Attempt to remove an element. If it’s not there, shrug and
        // return false. If you find it, remove it like a piece of old code that
        // no longer sparks joy.        
        int i = e.hashCode();
        i = i % this.capacity;
        if (this.table[i] == null) {
            return false;
        } else {
            boolean contains = (this.table[i].contains(e));
            if (contains) {
                this.table[i].remove(e);
                this.size--;
            }
            return contains;
        }
    }
    
    @Override
    public boolean contains(E e) {
        // TASK 8: Is your element in the table? Find out! If it's there,
        // celebrate with a triumphant return `true`. If not, return `false`
        // like a disappointed treasure hunter.
        int i = e.hashCode();
        i = i % this.capacity;

        if (this.table[i] == null) {
            return false;
        } else {
            for (E j : this.table[i]) {
                if (j.equals(e)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public E get(E e) {
        // TASK 9: Retrieve the element from your hash table—like searching for
        // a needle in a haystack, if your haystack had neat little slots. If
        // it's there, return it with a flourish. If not, return `null` with a
        // sigh.
        int i = e.hashCode();
        i = i % this.capacity;  
        if (this.table[i] == null) {
            return null;
        } else {
            for (E j : this.table[i]) {
                if (j.equals(e)) {
                    return e;
                }
            }
            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        // TASK 10: Time for some iteration magic. Hand back an iterator that
        // will bravely traverse your hash table, item by item.
        return new Iterator<E>() {
            int index = 0;
            Iterator<E> currentListIterator = null;

            public boolean hasNext() { // checks if current element is not null
                if (size == 0) {
                    return false;
                }
                
                while (this.index < table.length) {
                    if (table[this.index] == null) {
                        this.index++;
                    } else {
                        if (currentListIterator == null) {
                            currentListIterator = table[this.index].iterator();
                        }

                        if (currentListIterator.hasNext()) {
                            return true;
                        } else {
                            this.index++;
                            currentListIterator = null;
                        }
                    }
                }
                return false;
            }



            public E next() { // returns current element and moves to next
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the hash table.");
                }  
                

                if (!currentListIterator.hasNext()) { // if current bucket we're in is at it's last element
                    this.index++;
                    while (table[this.index] == null && this.index < table.length) { // Keep going till we find a non-empty bucket
                        this.index++;
                    }
                    currentListIterator = table[this.index].iterator();
                }
                
                E currentElement = currentListIterator.next();

                return currentElement;
    
            }

        };
    }
    
    /**
     * Computes the capacity of the hash table as 2^n - 1, where n is an integer
     * representing the growth factor of the table.
     * 
     * This method serves several key purposes:
     * 
     * 1. **Prime-Like Capacity**: By setting the capacity to 2^n - 1, the table
     *    avoids even capacities, reducing the likelihood of hash collisions and
     *    improving the distribution of elements across the buckets.
     * 
     * 2. **Controlled Resizing**: `computeCapacity` is used during resizing when
     *    the load factor exceeds the threshold (0.75). Each time `n` increments,
     *    the capacity approximately doubles, balancing memory usage and
     *    performance by keeping the load factor within an optimal range.
     * 
     * 3. **Improved Hashing**: The computed capacity is used as the modulus in the
     *    hash function, helping to distribute elements more evenly. Using a
     *    non-even divisor reduces clustering in buckets, especially with keys
     *    that have sequential or similar hash codes.
     * 
     * 4. **Dynamic Growth**: Setting the capacity to powers of two minus one 
     *    allows for efficient, consistent doubling of capacity, ensuring the
     *    hash table scales smoothly with the number of elements added.
     * 
     * @return the computed capacity as 2^n - 1
     */
    private int computeCapacity() {
        return (int) (Math.pow(2, n) - 1);
    }

}