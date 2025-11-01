export {};
// map and filter return a new array, but what if instead of that we only wanted to print or show each result (doesn't involve returning)

const someArray = [1,2];
someArray.forEach(console.log);
// forEach applies fn to each element and ignores value returned by the function

function forEach<T>(a: T [], f : (e: T) => any): void {
    for (let i = 0; i < a.length; ++i) f(a[i]); // value not used
}

// so forEach returns nothing -- what happens if we try to console.log it?
console.log([1,2,3].forEach(x => ++x));
//  Ans: returns undefined



let anArray = [1,2,3];
anArray.forEach(x => ++x);
console.log(anArray); // array doesn't change since doing x => ++x does indeed increment x but it returns the new value to forEach, and since forEach does nothing with it, nothing changes


console.log(anArray.includes(3)); // returns bool
console.log(anArray.indexOf(2)); // returns 2 and -1 if not found
console.log(anArray.lastIndexOf(2)); // returns last index element was found at


// Higher order function example: find
// .find searches first value (not index) that satisfies a condition (parameter is a func that returns a boolean)
//      Note the function you input must itself take an input of the same type as the array, like I can't do x => x>3 on an array of strings
//      findIndex and findLast and findLastIndex related
console.log([1,2,3,4,5].find(x => x > 3)); // returns 4. If nothing satisfies returns undefined


// function find<T>(a: T [] , f : ( e : T) => boolean): boolean {const bool:boolean; return bool;} // checks if an array has an element with a specific property
// related: like find, we have someArray.some() and someArray.every() and someArray.reduce() 
//      reduce() reduces an entire array into a single value, like sum, or max, by applying same operation to every element
//      anArray.reduce() takes an initial value parameter (if none provided it defaults first element in array)
//      LOOK AT slide#2 of https://umamherst.instructure.com/courses/28300/files/12962829?module_item_id=2487515
//      the function inside reduce could take an input of a diff type that it outputs (e.g. using reduce to find length of longest string in an array of strings) 


// implementing reduce:
function biggest(current:number, next:number):number {
    if (current > next) {
        return current;
    }
    return next;
}
let b = [5,2,7,8,1];
console.log(b.reduce(biggest, Number.NEGATIVE_INFINITY)) // negative infinity is smallest element any array can have as initial value


// clever way to find largest value using .reduce:
console.log(b.reduce((x,y) => y>x ? y : x, Number.NEGATIVE_INFINITY)); // x,y analogy is current,next from biggest() 
// however, this doesn't return anything at last iteration