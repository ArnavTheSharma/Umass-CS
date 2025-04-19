public class Lecture16 {
    // 2-3-4 Trees: Each node can have 2, 3, or 4 children (NOT 2,3,4 elements), but when it reaches 4 elements it overflows since 6 children then.
    //      Note: we start at 2-3-4 not 1-2-3-4 since having 1 element means atmost 2 children
    //      If our node has elements: (1,2,3,4) then we split it into a parent node having (2) and child nodes of (1) and (3,4). Then we add the (2) to ITS parent node, and keep building up overflows so that depth isn't affected.
    //      We can also have parent be (3) and it'll work, but not (1) or (4) 
    //      If you keep building up and the root of entire tree overflows, then the root itself gets a new parent root, and thus depth += 1
    
    // height of tree in WORST case is log(n) if we assume each node has 1 chlid (if you have more children actually height decreases a little).

    // If a node doesn't have children (leaf) it points to null. If deleting an element in a node, make sure you remove a null pointer 
    // If removing an element in a node with multiple elements, then replace that element with the correct element from it's child (correct as in if you choose the wrong one, you'd have 2 children pointing say in between 2 elements, which is illegal)
    //      For this reason we do a rotation with one of the neighbors if removing an empty node. Also note we can't just delete the null pointer since it has a null child and that's losing info.
    //      Note after this "fusion operation" the parent might end up to be null (even though children isn't null), so we repeat this process with it's neighbor.

    //      Transfer operation when deleting: We find the neighbor (multiple elements), move the largest element in there up to parent, move the (technically 2nd largest now) element down to right node, and adjust the children node pointers.
    
    // If we are inserting with a top-down implementation, whenever we see 3 elements as we traverse down (as in as we are traversing to search for where to put element), we need to move the middle of the 3 elements up into parent node, and split the child into 2 nodes so that we have a legal amount of child nodes with the updated parent


    // B-tree: multi-way tree of order d where every node has atmost d children and atleast d/2 children, root node has atleast 2 children unless its a leaf, all leaves are same level, and the children and keys (AKA elements) are legal 
    //      Note: some definitions don't have the d/2 min restriction
    // B-tree has height log_d(n) --> technically d/2 instead of d 
    // The larger the order, d, the lesser height and thus easier search (but don't go too large d)
    //      Although it's true I could use a set to find the element, we need to know what child to go to as we go down, so it really is linear search 



    // Once your DB gets too large and RAM can't fit, it has to be stored on external disks (HSSs, SSDs, etc) 
    //      RAM is faster than exernal disks but RAM is expensive to buy whereas external are cheaper.
    //      This is because if we are requesting a key and the key is in external disk, we actually get returned the entire disk page, not just 1 byte. (page usually between 512 and 8192 bytes).
    //      Think about the number of pages that so many nodes in a big tree would return! O(log(height)) number of pages!
    //          Therefore, they have 1 node fits into a page of data, so when we rely on disc memory, use B-trees (since we can't just consider operation time complexity, now need space complexity).
    //      In practice, B-trees are actually maps of key value pairs
    //      B+ trees (not a typo, literally plus) are where the leaves are linked together, and the internal nodes are just used for searching. 
    //          The bottom level is a pointer from node to node of ALL the elements, we use the parent nodes of this bottom level to know where to start in this very long bottom list.
    //          If we get an element, we can quickly find it's successor.
    //     








}
