public class Lecture21 {

    // BFS Code implementation 
    // implement a list, "frontier" (open list) as a Queue (since we always want FIFO)
    //      also need a seen HashSet for closed list
    // do a while loop of if the frontier is empty. Break if node is goal (it will always be shortest path)
    //      else, iterate through the current node's neighbors and if the neighbor wasn't already put on open list then add it to open list. After iterating through them all, add the element at front of queue to closed list and continue
    
    // Code to find the path, we first initialize a linked list called "path". We would basically first check if we reached the goal, just add the goal to "path" and return the list (because everything before it would be correct). Else, keep track of the previous 


    // Theory:
    // BFS has no cycles because you cant go back to your original node (can only draw a tree edge to something you haven't seen)
    // A non tree edge can be at most 1 distance away (d(u,v) <= 1), so it can either be 0 or 1. 0 means it it's a tree edge, and 1 means the node it points to was already seen. It can't be 2 because the fact that there is an arrow from u to v means it directly points to it (d = 1) with nothing in between, so 2 is contradictiory
    // In theory, if we added the neighbors in a diff order (not in ascending order), we could get a diff tree but the shortest path is still the same (only the time may or may not change to get it)

    // Time complexity:
    // some vertices might have more neighbors than others, but if we add up the workdone for it all it'll be O(e) for e = number of edges. And since we have O(v) for all vertices being processed, total ocmplexity is O(V+E)
    
    // DFS:
    // DIfferent than 250 DFS since in this we use a call stack and the algorithm is iterative.
    // In this DFS, we're given the graph in "advance" -- ???
    // We basically do the same, except this time FILO
    // Also note that we don't have to process each neighbor in increasing order. Since we iterate through the neighbors for the current node we are in in our iteration, we add it in the order we are provided in.

    




}