//Implements Sets using DiscountHashTable
public interface DiscountSet<E> {
    
    /**
     * Adds the element to the set.
     * @param e
     * @return true iff the set previously contained the element
     */
    boolean add(E e);


    /**
     * Returns true iff the set contains the element
     * @param e
     * @return true iff the set contains the element
     */
    boolean contains(E e);

    /**
     * Returns the number of elements in the set.
     * @return the number of elements in the set.
     */
    int size();

     /**
     * Removes the element from the set.
     * @param e
     * @return true iff the set previously contained the element
     */
    boolean remove(E e);


     /**
     * Reports all items in set. Extra method included for teaching
     */
    void ReportAll();



}
