public class Lecture14 {
    // AVL trees is one of the schemes to balance a binary tree.
    //    We find the height of both children subtrees of every node and ensure the diff in each's height is atmost 1. If it's not then 

    // height of a node is the distance form the node to the bottom (not including itself)

    // the rotating intuitively makes sense since you're really just rearanging the subtrees around the node when needing to balance an unbalanced AVL.
    //      Look at 210 slide "basic rebalancing Tool: Rotations". 
    // When inserting a node thst causes an unbalance, there are 4 cases to consider: 
    // Left Left case, Left right case, right right case, right left case, which each have different responses. 
    //      Note we don't have left case or right case alone because the change in height is 1 which is legal.
    //      Note we also don't consider left left left case because it would've been balanced the moment the change in height is 2, so it can't get to 3

    //      Let 


}