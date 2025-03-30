public class DiscountHashMap<K, V> implements DiscountMap<K, V> {
    DiscountHashTable<KeyValuePair<K, V>> table = new DiscountHashTable<KeyValuePair<K, V>>();

    @Override
    public V get(K k) {
        KeyValuePair<K, V> probe = new KeyValuePair<K, V>(k, null);
        KeyValuePair<K, V> kvp = table.get(probe);
        if (kvp == null) {
            return null;
        }
        return kvp.v;
    }

    @Override
    public V put(K k, V v) {
        V previous = remove(k);
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
