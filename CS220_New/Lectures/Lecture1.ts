export {};
let a = 2; // kind of declaring but not quite
let b = "Hi";
let c = [0,1,2,3]; 
// as you can see there's no strict type enforcement, called dynamic typing (type checking is done at runtime, not compile-time like Java -- JS is interpretted)
// Also make sure you don't reasign a variables value to a diff type after declaring

const pi = 3.141592; // cant change a constant later
console.log('The value of pi is ' + pi.toString()); // no difference between "" and ''

const d = [0,1,2,3]; // even though the array is a const, you can still change the internal value after?

function factorial(n:number) {}


// === and !== in JS
// undefined in JS is like null in Java -- if a function doesn't return a value it returns undefined
// TypeScript is a syntatic superset of JS (any JS program is valid in TS)
//      TS is compiled (transpiled) to JS, then JS code is executed
//      converts using command tsc, then JS executed with node

function concatN(s: string, n: number): string{
    return "";
}


let res = []; 
for (let i = 0; i < 5; ++i) {
    res.push(i); 
}


// the map function takes 2 arguments: an array and a function to apply to each element, then returns the new array (doesnt modify original)
// our own map function
function map(a: number[], f: (x: number) => number): number[] {
    let result = [];
    for (let i = 0; i < a.length; ++i) {
        result.push(f(a[i]));
    }
    return result;
}
// Map is an example of an HOF (higher order function), which just takes a function as a parameter\

// We wrote our own map above, but this already exists with myArray.map(myFunction)
function milesToKilometers(n:number) : number{
    return (n*1.6);
}
let distanceInMiles = [2.2, 6.2, 7, 5.1];
let distanceInKilometers = distanceInMiles.map(milesToKilometers);

