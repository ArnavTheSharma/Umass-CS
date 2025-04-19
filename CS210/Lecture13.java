
class Node<E extends Comparable<E>> { // ensures that E is a type that can be compared to other E types using the compareTo()
    E data;
    Node<E> left;
    Node<E> right;

    public Node(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class Lecture13<E extends Comparable<E>> { // cant do just E, need the extends Comparable too
    // to search the tree need to traverse through nodes from root and compare node.data
    // base case: if n (not n.data) is null, then e just isn't in the tree (since empty tree)
    

    public Node<E> find(E e, Node<E> n) { // called as t.find(data we're looking for, root node)
        if (n == null) {
        return null;
        } else if (n.data.equals(e)) {
        return n;
        } else if (e.compareTo(n.data) < 0) {
        return find(e, n.left);
        } else {
        return find(e, n.right);
        }
    }
}


// For AVL trees and Red-Black trees, you need to keep track of the height of each node.
//      height = O(log(size)), so a search for a million elements is about 20 comparisons!! 
// This is because you need to know how to rotate the tree to keep it balanced. Rotate means to change the structure of the tree by moving nodes around. For example, if you have a left-heavy tree, you might need to rotate it to the right to balance it out. This is done by changing the parent-child relationships between nodes. The height of a node is the number of edges from that node to the deepest leaf node in its subtree. You can calculate the height of a node by recursively finding the height of its left and right children and taking the maximum of those two heights plus one (for the current node). The height of an empty tree is -1, and the height of a tree with one node is 0.



// printing binary tree with pre-order doesn't sort it in sorted order (it js does left child nodes before right). Same w/ postorder, but in-order does this
//   O(n) time because you visit every node once (once going down and once going up but 2n goes to n)

// if tree doesn't have node you're inserting, create a new binary tree with only that node as the head
// for insert first check if current node you're at is less than or greater than whats inserting. If greater check if node.right is null and if it is place there. Else do add(e, n.right)
//      Search time: O(height) = O(log(n)) for balanced trees, O(n) for unbalanced trees.
//      What if we put elements in randomly (so we dont know if balanced of unbalanced). Ave search time is O(log(n)) 
//    Worst case is O(n) if the tree is unbalanced (like a linked list).

// searching in a BST is js like binary search in an array! In array it's O(log(n)), but in BST 

// To make a BST from a given array, start from center of array as head, then recursively do the same for left and right halves of the array




// Deleting: If deleting a leaf, just set ptr that was pointing to it = null
//      If it was a child node and if whatever you were cutting out is .left of something which was also.left, just "pass that child up". Lets call this splicing.
//      But now what if the node we're removing has 2 children (each of which are big BSTs too)
//          1. Find either the greatest predecessor or smallest sucessor. If using latter, since everything else is greater than the current node and its smallest sucessor, everything will be valid. Same with greatest predecessor.
//              Note if what you need to move now has 1 child, js splice that. 
//          2. To find either this greatest predecessor or smallest sucessor, go to node removing, 
//              To find greatest predecessor, go to left child and then keep going right until you can't anymore.
//                  Note the node you're replacing with can't have 2 children because that implies you can actually go further for this. 


// For splicing, we hold on to a reference of the parent of current node, and c for x's child. (Note child not children since we're just splicing, which prereqs 1 child)
// If we wanna remove root (technically a leaf) js set root = null.
//      If 1 root + 1 child, set root = child.


void splice(Node<E> n) {
    if (n.left != null && n.right != null) {
        throw new IllegalArgumentException();
    }

    Node<E> subNode, parent;
        if (n.left != null) {
            subNode = n.left;
        } else {
            subNode = n.right; // OR NULL
        }
        if (n == root) { // special case, deleting root root = subNode;
            parent = null;
        } else { // otherwise, more general case
            parent = n.parent;
            // slide up the subnode into the correct child of the parent
            if (parent.left == n) {
                parent.left = subNode;
            } else {
                parent.right = subNode;
            }
        }
        if (subNode != null) {
            subNode.parent = parent;
        }

    size--;
}


// Could also do this iteratively. Next lec we'll look at AVL trees and that. 