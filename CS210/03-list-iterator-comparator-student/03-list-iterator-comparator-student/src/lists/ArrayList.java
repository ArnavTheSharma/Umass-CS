/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

import java.util.Iterator;
import java.util.Objects;

public class ArrayList<E> implements List<E> { // List<E> already extends Iterable<E> 
    // Teacher Note: do not declare any additional instance variables
    E[] array;
    int size;

    // default constructor
    @SuppressWarnings("unchecked")
    public ArrayList() {
        size = 0;
        array = (E[]) new Object[10];
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = prime * result + array[i].hashCode();
        }
        result = prime * result + size;
        return result;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) { // Everything's superclass is Object. "Class Object is the root of the class hierarchy. Every class has Object as a superclass"
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof List)) // if obj is NOT an instance of List, aka not of type List
            return false;
        List other = (List) obj; // we supressed rawtypes? I guess we don't do List<E>? 
        if (size != other.size()) 
            return false;
        // Part 1 List Data Type and Implementations
        // TASK: Ensure that each element in both lists is identical before
        // returning true. Compare corresponding elements of each list and
        // return true only if all comparisons are equal.

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(this.get(i), other.get(i))) { // Objects. because we want null safety
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        // Part 1 List Data Type and Implementations
        // TASK: Replace the return value with the correct logic to return the
        // actual number of elements in the list. 

        return this.size; // size gets incremented from add() method
        // do they expect us to use Iterator object?
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        // Part 1 List Data Type and Implementations
        // TASK: Implement the logic to return the element at the specified
        // index if it is within bounds. If not, throw an exception.
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            return array[index];
        }
    }

    // PART 1 List Data Type and Implementations
    // TASK: Implement the `enlarge` method to automatically enlarge the array
    // when it reaches capacity. Ensure you create a new array, copy all
    // elements from the old array, and update the reference.


    @Override
    @SuppressWarnings("unchecked")
    public void add(E e) {
        // Part 1 List Data Type and Implementations
        // TASK: Implement the add method to include a check for array capacity
        // and enlarge if necessary before adding a new element.
        if (size == array.length) {
            E[] newArray = (E[]) new Object[size*2];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            } 
            this.array = newArray;
        } 
        array[size] = e; // size isn't array.length, its # of current elements, so if there're 5 elements (last one at index 4), we put e on index 5, or array[size]
        size++; 
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(int index, E e) throws IndexOutOfBoundsException {
        // Part 1 List Data Type and Implementations
        // TASK: Implement the add method to insert an element at a specific
        // index. Ensure elements are correctly shifted to accommodate the new
        // element. If the index is out of bounds, throw an exception. If the
        // list is full, automatically enlarge it.
        //
        // NOTE: In class we studied a version of this `add` method that threw
        // an exception when add(size of list, e) was called. In this version,
        // we are asking you to allow add(size of list, e) to work.
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } 
        if (size == array.length) {
            System.out.println(size);
            System.out.println(array.length);
            E[] newArray = (E[]) new Object[size*2];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            } 
            this.array = newArray;
        } 
        for (int i = size; i > index; i--) { // start from size, not size-1, since we're allocating memory for 1 extra. D > index not >=
            array[i] = array[i-1];
        }
        array[index] = e;
        size++;
        }   


    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        // Part 1 List Data Type and Implementations
        // TASK: Implement the remove method to delete an element at a specified
        // index, shift elements to fill the gap, and return the removed
        // element. If the index is out of bounds, throw an exception.
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            E element = array[index];
            for (int i = index; i < size; i++) {
                array[index] = array[index+1];
            }
            size -= 1;
            return element;
        }
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        // Part 1 List Data Type and Implementations
        // TASK: Implement the set method to replace an element at a specified
        // index with a new element and return the old element. If the index is
        // out of bounds, throw an exception.
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            E element = array[index];
            array[index] = e;
            return element;
        }
    }

    @Override
    public int indexOf(E e) {
        // Part 1 List Data Type and Implementations
        // TASK: Implement the indexOf method to return the index of the first
        // occurrence of the specified element in the list, or -1 if the element
        // is not found.
        for (int i = 0; i < size; i++) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        // PART 2 Iterators
        // TASK: Implement the iterator method to return an Iterator for the
        // current collection. Ensure that the Iterator allows sequential access
        // to elements in the correct order.

        return new ArrayListIterator<E>(array, size);

    }

}