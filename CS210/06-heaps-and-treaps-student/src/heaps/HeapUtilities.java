// extract min: In a max-heap, to find the min, it will be in the leaf nodes, which are the last half of the array. 

/*
 * Copyright 2023 Marc Liberatore.
 */

package heaps;

import java.util.Arrays;
import java.util.Random;

public class HeapUtilities {
    /**
     * Returns true iff the subtree of a starting at index i is a max-heap.
     * 
     * @param a an array representing a mostly-complete tree, possibly a heap
     * @param i an index into that array representing a subtree rooted at i
     * @return true iff the subtree of a starting at index i is a max-heap
     */
    static boolean isHeap(double[] a, int i) {
        // TASK 1: Add your implementation here.
        // max-heap: the root is the max, so every child is less than or equal to their parent

        if (i >= a.length - 1) {
            return true; // empty tree is a heap
        }

        int left = 2*i + 1; // index of left child
        int right = 2*i + 2; // index of right child
        int n = a.length; 
        
        if (left < n && a[left] > a[i]) {
            return false;
        } else if (right < n && a[right] > a[i]) {
            return false;
        }

        return (isHeap(a, left) && isHeap(a, right));
    }

    /**
     * Swap the elements at indices i and j in the array a.
     * 
     * @param a the array
     * @param i the first index
     * @param j the second index
     */
    static void swap(double[] a, int i, int j) {
        // TASK 2: Add your implementation here.
        if (i >= a.length || j >= a.length || i < 0 || j < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
        
        // a[i] = a[i] + a[j];
        // a[j] = a[i] - a[j];
        // a[i] = a[i] - a[j];

        double n = a[i];
        a[i] = a[j];
        a[j] = n;

    }

    /**
     * Perform the heap siftdown operation on index i of the array a.
     * 
     * This method assumes the subtrees of i are already valid max-heaps.
     * 
     * This operation is bounded by n (exclusive)! In a regular heap,
     * n = a.length, but in some cases (for example, heapsort), you will
     * want to stop the sifting at a particular position in the array.
     * siftDown should stop before n, in other words, it should not
     * sift down into any index great than (n-1).
     * 
     * @param a the array being sifted
     * @param i the index of the element to sift down
     * @param n the bound on the array (that is, where to stop sifting)
     */
    static void siftDown(double[] a, int i, int n) {
        // TASK 3: Add your implementation here.
        // max heap

        if (i >= n) {
            return; // if the index is out of bounds, return
        }

        int left = 2*i + 1; // index of left child
        int right = 2*i + 2; // index of right child
        int largest = i; // largest element index
          

        if (left < n && a[left] > a[largest]) { 
            largest = left; // update index
        }         
        if (right < n && a[right] > a[largest]) {
            largest = right;
        }

        if (largest != i) { // if after all these conditions, one of children had a greater value
            swap(a, i, largest); // swap
            siftDown(a, largest, n); // call recursion on the child heap you swapped (largest holds the index of that)
        }

    }

    /**
     * Heapify the array a in-place in linear time as a max-heap.
     * 
     * @param a an array of values
     */
    static void heapify(double[] a) {
        // TASK 4: Add your implementation here.
        int n = a.length;

        for (int i = n; i >= 0; i--) {
            siftDown(a, i, n);
        }
    }

    /**
     * Heapsort the array a in-place, resulting in the elements of
     * a being in ascending order.
     * 
     * @param a
     */
    static void heapSort(double[] a) {
        // TASK 5: Add your implementation here.
        heapify(a);
        int n = a.length;
        
        for (int i = n-1; i >= 0; i--) {
            swap(a,0, i);
            siftDown(a, 0, i);
        }
    
    }

    /**
     * Main method for testing.
     */
    public static void main(String[] args) {
        Random r = new Random(0);
        int length = 15;
        double[] l = new double[length];
        for (int i = 0; i < length; i++) {
            l[i] = r.nextInt(20);
        }
        System.out.println(Arrays.toString(l));
        System.out.println(Arrays.toString(l));
        heapSort(l);
        System.out.println(Arrays.toString(l));
    }
}