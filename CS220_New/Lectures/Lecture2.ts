export {};
// Typescript --> everything is a reference type and an object, which is why they use undefined so much. 

class Thing {
    a: number;
    b: number;
    constructor() { // default constructor
        this.a = 1;
        this.b = 2;
    }
}
let thing = new Thing();

// if you try to console.log(thing.c), it doesn't return an error but instead "undefined"



// Java: String spam = null; is legal, but comparing spam to any string "hi" raises a null exception pointer, which
//      In Java, objects by default with no assignment point to null, but primitives have their own default values e.g. int is 0
//      Can be dangerous, how do we deal with this in TypeScript?

let spam: string | null = null;

// Logging a value out of bounds of an array's index gives you "undefined", e.g.
let values:number[] = [1, 2, 3];
let num:number = values[5];
console.log(num); // undefined, but num is of type number? 


function map(inputT:number, outputT:String): outputT[] {
    data: inputT[];
    
}