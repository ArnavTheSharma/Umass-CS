public class Lecture15<E extends Comparable<E>> {
    // Note: height of a tree is -1 if it's empty, and 0 if 1 node.
    // AVL tree is just a self balancing binary tree (the cost is just the inserting/deleting it takes to rebalance)
    // balance factor = height of left child - right child
    
    // If we have an unbalancing, we first find the highest unbalanced node, and based on whether it's a RR, LL, LR, or RL case, do the appropriate rotation.
    //      Look visually for right and left rotations. For RL and LR it's just 2 rotations.
    //      Note: the rotations are just adjusting pointers etc, so it's all O(1).

    //      Remember that splicing is basically removing a node that has just 1 child 

        Node<E> root;
        int size;
    
        public int size() {
            return size;
        }
    

        // though this is expensive since you're running a log(n) operation for EVERY node.
        // Instead, assign a height instance variable to each node, and += 1 from the bottom up with parent to parent.
        // 
        public int height(Node<E> n) {
            if (n == null) {
                return -1;
            }
            return 1 + Math.max(height(n.left), height(n.right));
        }
    
        boolean isAVL(Node<E> n) {
            return Math.abs(height(n.left) - height(n.right)) <= 1;
        }
    
        public void add(E e) {
            if (root == null) {
                root = new Node<>(e);
                size++;
                return;
            }
            add(e, root);
        }
    
        void add(E e, Node<E> node) {
            if (e.equals(node.data)) {
                node.data = e;
            }
            if (e.compareTo(node.data) < 0) { //needs to go left
                if (node.left == null) {
                    node.left = new Node<>(e);
                    node.left.parent = node;
                    size++;
                    insertionCheck(node.left);
                    return;
                } else {
                    add(e, node.left);
                }
            } else { // needs to go right
                if (node.right == null) {
                    node.right = new Node<>(e);
                    node.right.parent = node;
                    size++;
                    insertionCheck(node.right);
                    return;
                } else {
                    add(e, node.right);
                }
            }
        }
    
        // This just checks the lowest unbalanced node and rotates it to fix.
        void insertionCheck(Node<E> node) {
            Node<E> n = node;
            String path = ""; // This stores a string of the path we took (either L or R) appended from the right to left. When we reach an unbalanced node, we check the first 2 chars (not last 2) to check if its LL, LR, RL, or RR.
            
            while (true) {
                if (!isAVL(n)) {
                    // fix the avl property!
                    String rot = path.substring(0, 2);
                    if (rot.equals("LL")) {
                        rotateLL(n);
                    } else if (rot.equals("LR")) {
                        rotateLR(n); // TODO!
                    } else if (rot.equals("RL")) {
                        rotateRL(n);
                    } else {
                        rotateRR(n);
                    }
                    // return; // this works for addition, but you can't stop
                            // early for removal
                } else if (n != root) {
                    // go to its parent and continue
                    if (n.parent.left == n) {
                        path = "L" + path;
                    } else {
                        path = "R" + path;
                    }
                    n = n.parent;
                } else { // it was the root!
                    break;
                }
            }
        }
        
        void rotateLL(Node<E> n) {
            Node<E> A, B, T1, T2, T3, p;
            B = n;
            p = B.parent;
            A = B.left;
            T1 = A.left;
            T2 = A.right;
            T3 = B.right;
    
            // first move A to the root of the subtree
            if (B == root) { // special case if B is root
                root = A;
                A.parent = null;
            } else {
                if (p.left == B) {
                    p.left = A;
                } else { // p.right == B
                    p.right = A;
                }
                A.parent = p;
            }
    
            // then, make B into A's right subchild
            A.right = B;
            B.parent = A;
    
            // move T2 to be B's new left child
            B.left = T2;
            if (T2 != null) {
                T2.parent = B;
            }
        }
    
        boolean contains(E e) {
            return find(e, root) != null;
        }
    
        // boolean contains(E e, Node<E> n) {
        //     if (n == null) {
        //         return false;
        //     } else if (n.data.equals(e)) {
        //         return true;
        //     } else if (e.compareTo(n.data) < 0) {
        //         return contains(e, n.left);
        //     } else {
        //         return contains(e, n.right);
        //     }
        // }
    
    
        Node<E> find(E e) {
            return find(e, root);
        }
        
        Node<E> find(E e, Node<E> n) {
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
    
            if (n == root) { // special case, deleting root
                root = subNode;
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
        }
        
        void remove(E e) {
            Node<E> n = find(e);
            if (n == null) {
                return;
            }
    
            if (n.left == null || n.right == null) {
                splice(n);
            } else {
                Node<E> successor = n.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                n.data = successor.data;
                splice(successor);
            }
            size--;
        }
    
        public static void main(String[] args) {
            BinarySearchTree<Integer> t = new BinarySearchTree<>();
            System.out.println(TreePrinter.toString(t.root));
        }
    
}
    
