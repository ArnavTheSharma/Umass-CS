import { List, node, empty } from "../include/lists.js";

// Using lists examples

// First, lets create the list 1 -> 2 -> 3 -> empty
const threeList = node(1, node(2, node(3, empty())));

// Now, lets print these out.
console.log(threeList.head()); // Prints 1
console.log(threeList.tail().head()); // Prints 2
console.log(threeList.tail().tail().head()); // Prints 3
// console.log(threeList.tail().tail().tail().head()); This fails because the empty node has no head.

// Next lets write a function that will create a List of n nodes starting at n and decreasing to 1.
function decrByOne(n: number): List<number> {
  if (n === 0) {
    return empty();
  }
  return node(n, decrByOne(n - 1));
}

const tenToOne = decrByOne(10);

// Finally we'll write a function that converts a list to a string
function listToString<T>(lst: List<T>): string {
  if (lst.isEmpty()) {
    // We always need to check if a list is empty when working recursively with lists
    return "empty";
  } else {
    return `${lst.head()} -> ${listToString(lst.tail())}`;
  }
}

console.log(listToString(tenToOne));

// TS allows us destructure arrays:
let a, b, rest;
[a, b] = [1, 2];

console.log(a);
console.log(b);

// What does ... do?
const c = [1, 2, 3];
const d = [4, 5, 6];
const e = [...c, ...d];

console.log(e);

[a, b, ...rest] = ["a", "b", "c", "d", "e"];

console.log(a);
console.log(b);
console.log(rest);

// In Class Exercises

// Exercise 1
export function merge(l1: List<number>, l2: List<number>): List<number> {
  // TODO: complete this function
  return empty();
}

// Exercise 2
export function sumPositivesAndNegatives(arr: number[]): [number, number] {
  // TODO: complete this function
  return [0, 0];
}

// Bonus Exercise 3
export function reverseFilter<T>(list: List<T>, filterF: (x: T) => boolean): List<T> {
  // TODO: complete this function
  return empty();
}
