public class Lecture20 {
    // quicksort: basically we have an unsorted array. Start with last element as "pivot value". Iterate through the array. If element is less than pivot, then color it grey. If greater than, color it dark grey. If you see a light grey after a dark grey, you need to insert the new light grey right before the first dark grey. After doing this, you'll end up with 2 partitions: first is (unsorted) values less than pivot and 2nd is others. This will reuslt in a fully colored light grey rectangle followed by a dark grey rectangle. After this, insert the pivot in between these and use recursion on the 2 partitions you made.
    //      for coding, has alot of annoying little details, esp for indexes
    //      Note if we quicksorted something that's already sorted, this will end in O(n^2) (to iterate through list from right to left and since for each of those iterations we need to iterate through the light grey subset list). 
    //          n^2 since (n) + (n-1) + ... + 1 --> n^2 --> worst case
    //      Algorithm has same complexity if we did it reverse order (instead of choosing from end, we do beginning)
    //      This works really well with random data, NOT sorted data
    
    //      To get average case, instead of starting at smallest or largest element in set, choose a random item, and then do this random choice to the left and right etc etc. This is because there'll be less variance when choosing something towards the middle (since if we're in the middle of our algorithm larger items will be concentrated more to right)
    //          However the tradeoff is that finding the element at the exact middle takes time itself, so there's a tradeoff.
    //          What they did is they hard-coded the implementation for 2, 3, 4, and 5 items in an element, since this sortnig method doesnt work well for that
    //      Unix has a highly engineered version of quick sort


    // Graph theory: in facebook implementation, nodes are the people and edge is a friend relationship (social network)
    //      Convention: n denotes number of nodes = |V| if V is set of nodes, m denotes number of edges = |E| if E is set of edges
    //          (1,2) $\in$ E means there's an edge from 1 to 2/
    //      Degree of a vertex tells the number of neighbors. If we add the degeree of all vertices, it equals twice the number of vertices. This is because every edge connects only 2 vertices and therefore we can atmost double count.
    //          indegree is number of edges that are entering vertex. Outdegree is number of edges leaving the vertex.
    // Undirected graph = complete if there's an edge between ALL vertices (so n(n+1)/2 edges)
    // Tree = undirected graph with no cycles. Forest is a collection of trees. So first check if there aren't cycles in an undirected graph. Every connected component (that's seperated) themselves are trees. A lone tree counts as a forest.
    //      In 210 so far we've been talking about rooted trees.
    
    // Sometimes we can't represent the entire graph (either because it's too big of a state space, e.g. chess, or because it constantly changes, like the internet graph). Therefore we generate a partial view of the state space.
    // 2 ways of storing graphs: Adjacency list (for each vertex, store list of neighbors).
    //      Another way: Adjacency matrix: Where we have a nxn matrix, where a data value is a boolean of whether an edge between the column value and row value exist in the undirected graph.
    //          Could also store the edge length instead if you have that data

    // Symmetric matrix is if it's symmetric along the top left to bottom right line. aka (u,v) is the same as (v,u)
    // 

}

