// Hashing on next 210 quiz

public class Lecture12 {
    // Linear probing: Instead of solving hashing collisions with chaining, lets do this:
    //      Linear probing a form of open addressing
    //      In this, we do the usual mod, except this time if there's alr an element in the value we wanna place (for mod 18 e.g.) instead of chaining, we just insert element into the next empty slot we find. To retrieve/search this, we simply just go to where it would be, and if it's not that element, we search next element and next and next until we reach an empty slot (where it would've been placed) to find. But what if after inserting I delete the element right after it? That'd cause a problem since our break function would end too early, so we inesrt a tombstone in place of that to know to skip it when searching.
    //      Problem of tombstones is that they cause array to fill up --> clustering issue. 
    //      other open addressing Hashing schemes, e.g., Robin-Hood Hashing and Cuckoo Hashing
    // load factor alpha = m/n = number of elements/capacity
    // probing = finding/searching through the array basically. 
    // Probing through list with (n + a) % b will search every element if a and b are rela. prime



    // Trees: root is at top, not bottom, by convenction
    // Level = depth. Some do root as depth = 0, others do 1.
    // Nodes without children = leaves. Others are just internal nodes

    // base case isn't a leaf being depth=0 but a null being depth=-1
    // height(X) for a tree X = 1 + max(ℎ𝑒𝑖𝑔ℎ𝑡(X. left), ℎ𝑒𝑖𝑔ℎ𝑡 𝑋. right )
    
    // old calculators required you to write operation tree in postorder format, which it was easier to evaluate by using a stack
    //       Reverse Polish Notation (RPN) was used by calculators and programming languages e.g. Forth and Postscript (predecessor to PDF)
     