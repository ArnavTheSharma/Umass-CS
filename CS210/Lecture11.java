// doing && is the AND logic gate, but & is a bitwise operator, which doing & for each of the respective digits of each binary number, e.g. 110 & 100 --> 100, so 6 & 4 = 4. For strings t

import java.util.Dictionary;
import java.util.LinkedList;


public class Lecture11 {
    public interface DiscountMap<K, V> {
        //Returns the value associated with the given key, or null
        V get(K k);

        //Updates the key in the map to be associated with the value v
        //return the previous value associated with the key, or null
        V put(K k, V v);

        //Removes the key from the map (if it was present).
        //return the previously value associated with the key or null
        V remove(K k);

        //Returns true iff the key is in the map.
        boolean containsKey(K k);

        int size();
    }
    

    class DiscountHashTable<E> implements DiscountSet<E> {
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
                        return element; // NOT e, but the element that's already there! Very important for HashMap! we do .equals for semantic equality, so hash could be diff bc diff pointers
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




    public interface DiscountMap<K, V> {
        //Returns value associated with given key, or null
        V get(K k);
    
        //Updates the key in the map to be associated with the given value v
        //return the previous value of key, or null
        V put(K k, V v);
    
        //Removes the key from the map (if it was present).
        //return the previously value associated with the key or null
        V remove(K k);
    
        //Returns true iff the key is in the map.
        boolean containsKey(K k);
    
        int size();
    }

    


    // Needed by  DiscountHashMap
public class KeyValuePair<K, V> {
    final K k;
    final V v;

    public KeyValuePair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        // ONLY DEPENDS ON Key, not value
        result = prime * result + ((k == null) ? 0 : k.hashCode()); 
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KeyValuePair other = (KeyValuePair) obj;

        // ONLY DEPENDS ON K
        if (k == null) {
            if (other.k != null)
                return false;
        } else if (!k.equals(other.k))
            return false;
        return true;
    }
}







public class DiscountHashMap<K, V> implements DiscountMap<K, V> {
    DiscountHashTable<KeyValuePair<K, V>> table = new DiscountHashTable<KeyValuePair<K, V>>(); // shouldn't this be in the main method?

    @Override
    public V get(K k) {
        KeyValuePair<K, V> probe = new KeyValuePair<K, V>(k, null); // probe is our created object so we can search using the hashCode. It then uses table.get() method which uses .equals to get to the index that the obj would be in and searches there 
        KeyValuePair<K, V> kvp = table.get(probe);
        if (kvp == null) {
            return null;
        }
        return kvp.v;
    }

    @Override
    public V put(K k, V v) {
        V previous = remove(k); // since duplicates not allowed 
        table.add(new KeyValuePair<K, V>(k, v)); 
        return previous;
    }

    @Override
    public V remove(K k) {
        V previous = get(k);
        if (previous != null) {
            table.remove(new KeyValuePair<K, V>(k, null));
        }
        return previous;
    }

    @Override
    public boolean containsKey(K k) {
        KeyValuePair<K, V> probe = new KeyValuePair<K, V>(k, null);
        return table.contains(probe);
    }

    @Override
    public int size() {
        return table.size();
    }
}





    //  Hashtables are integer-indexed arrays
    // We should be able to use them to store non-integer valued objects, which we do using e.hashCode() to transform objects into integers
    // Shold be quick lookup, otherwise what's the point since the find method is very annoying to do
    //      If the built in primitive type we are hashing has size of 4 or less bytes, then treat the bytes as an int nd that's the hash
    //      For 8 bytes (long, double), then use XOR on first 4 bytes (NOT BITS) and last 4 bytes
    //          XOR of 2 binary numbers, it does the XOR of each induvidual index (e.g. 1010 XOR 0111 --> 1101)
    
    // For containers: strings or arrays, rolling polynomial hash code
    
    // Dumb way: O(n^2)
    char s[ ];
    public int hashCode() {
        int h = 0, i, n=s.length;
        for(i=0; i<n; i++) {
            h += s[i] * ((int) pow(31,n-i-1)); // pow!!
        }
    }

    // Smart way: O(n)
    char s[];
    public int hashCode1() {
        int h = 0, i;
        for (i=0; i<s.length; i++) {
            h = 31 * h + s[i];
        }
    }


    // hashCode for rolling polynomial already built in for Strings and arrays
    // Java created hashCode's for objects since they alr have hash's for primitives. 
    // 
}