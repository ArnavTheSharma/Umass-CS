// Search up Racket language - bascially accepts most language's syntax -- but for e.g. java it doens't compile same way with bytecode, so it's compatible with other Rocket files written w/ python syntax
// godel machine
// Lists are identified by their head, which is just a node.


import java.util.LinkedList;

import org.w3c.dom.Node;

public class Lecture8 {
    // you can search throuhg a list in O(1) time with a hash map
    LinkedList<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    int a = list.indexOf(3);
    // 


    // binary search with 1000 elements is log_2(n) = log_2(1000) = 10 because 2^10 = 1024 and we round up
    // Writing binary search algorithm: we make a low and high variable to get the range of values of indices it could be then take the mid of that range and that's the next question
    // If element isn't there then high = mid = low so then we'd know when to stop
    int binarySearch(int[] a, int x) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) { // keep going
            int mid = (low + high) / 2;
            if (a[mid] == x) {
               return mid;
            } else if (a[mid] < x) {
                low = mid + 1;
            } else { // a[mid] > x
                high = mid - 1;
            }
        }
        return -1;
        }



        // Stack: Push, pop, peek (peek usually not there since oyu can just pop and push right after but oh well)
        //      Pop will remove and return the VALUE ITSELF, NOT the stack itself 
        // Push(3, S) will push 3 to the 
        // LIFO Last in First Out - 
        // Stack usually implemented as an Array or ArrayList where top of stack is js last item
        //      Using LinkedList, the head of the array is the top of list and bottom points to null. When pushing we simply insert it into 1st index


        // Queue: Add to back of queue (to decrease priority) and remove from the front of the queue, REMOVE not POP
        //      Queue usually a double linked list. Back is usually in the head and front in tail
        // We don't use an array because if we add to the back (leftmost) of queue, then we need to copy every element over
        //      To deal with this you can use an array but start with head at the nth element (for array of length n) but when it overflows, it adds elements and we now have to rely on circular array (e.g. if we add 5 elements) to add more elements to the very end, so we needa know what index the head is at
        
        
        // Recursion good with binary tree search since algorithmn traversing a tree is resursive
        static int factorial(int n){
            if (n == 0) {
                return 1;
            } else {
                return n * factorial(n - 1);
            }
        }
        // The parameter values are passed along on the call stack


        // To sum items in a list, we usually iterate thru list, but what if it's a linked list?
        int sum(Node<Integer> head) { // input of the head, so we can start anywhere on the list
            int s = 0;
            for (Node<Integer> current = head; current != null; current = current.next){ 
                s += current.data; 
            }
            return s;
        }

        // Recursively, we can basically use splicing -- in python, its return n + list[0] + sum(list[1:]) 
        // This is a recursive function for summing the integers from 0 to 𝑛 up using the recursive formula
        static int sumtoN(int n){
            if (n==0){
                return 0;
            }

            else{
               return n + sumtoN(n-1); // decreases n by 1 until it gets to 0 (e.g. 10, 9, 8, 7, ... 0), for LinkedList we use null instead of 0
            }
        }

        // Or if we define sumHelper through a LinkedList, we could take 2 parameters, 1 is 
        int sumR(int[] a) {
            return sumHelper(a, 0);
        }

        int sumHelper(int[] a, int i) {
            if (i >= a.length) { // got to the end
                return 0;
            } else {
               return a[i] + sumHelper(a, i + 1);
            }
        }
        
        // Sumhelper(a,0) = a[0] + sumHelper(a,1)
            // = a[0] + a[1] + sumHelper(a,2)
            // = a[0] + a[1] + a[2] + sumHelper(a,3)
            // = a[0] + a[1] + a[2] + a[3] + sumHelper(a,4)
            // = a[0] + a[1] + a[2] + a[3] + a[4] +sumHelper(a,5)
            // = a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + sumHelper(a,6)
            // = a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + sumHelper(a,7)
            // = a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + sumHelper(a,8)
            // = a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + a[8] + sumHelper(a,9)
            // = a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + a[8] + a[9] + sumHelper(a,10)
            // = a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + a[8] + a[9] + 0



            // How you would normally copy LinkedList code to a new LinkedList
            static <E> Node<E> copyIterative(Node<E> head) {
                if (head == null) {
                    return null;
                }
                Node<E> newHead = new Node<>();
                newHead.data = head.data;
                Node<E> oldRef = head.next;
                Node<E> newRef = newHead;
               
                while (oldRef != null) {
                    Node<E> n = new Node<>();
                    n.data = oldRef.data;
                    newRef.next = n;
                    newRef = newRef.next;
                    oldRef = oldRef.next;
                }
               
                return newHead;
                }

        // Lets try it recursively
        
                
}