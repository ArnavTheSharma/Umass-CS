export {};

// Testing: Instead of testing every possible combination of inputs induvidually, we need to only test some general cases and all special cases
//      When choosing only a few cases to test in the pool of general cases, typically choose the lower bound, the higher bound, and the middle bound of the pool
// e.g. make code that given a number, returns it ordinal representation (e.g. 1 => st)
//      Categories:
//      ends in 1 -> st
//      ends in 2 -> nd
//      ends in 3 -> th
//      top categories don't apply from 11-13 (special case) -> th
//      everything else ends in th

//      0 ends in th, and negative numbers have same cardinality as their positive counterpart


// Look in slides, a question like this will show up on the midterm