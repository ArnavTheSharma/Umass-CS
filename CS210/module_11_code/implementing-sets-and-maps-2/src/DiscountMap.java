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
