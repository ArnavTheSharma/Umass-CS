import { arrayToList, listToArray, node, empty } from "../include/lists.js";
import {
  insertOrdered,
  everyNRev,
  everyNCond,
  keepTrendMiddles,
  keepLocalMaxima,
  keepLocalMinima,
  keepLocalMinimaAndMaxima,
  nonNegativeProducts,
  negativeProducts,
  deleteFirst,
  deleteLast,
  squashList,
} from "./lists.js";

// Small test utility
function test<T>(name: string, lst: any) {
  console.log(`${name}:`, JSON.stringify(lst));
}

// insertOrdered
test("insertOrdered", listToArray(insertOrdered(arrayToList([1, 3, 4, 5]), 4)));

// everyNRev
test("everyNRev", listToArray(everyNRev(arrayToList([1, 2, 3, 4, 5, 6]), 2)));

// everyNCond
test("everyNCond", listToArray(everyNCond(arrayToList([1, 2, 3, 4, 5, 6]), 2, x => x % 2 === 0)));

// maxima/minima
test("keepLocalMaxima", listToArray(keepLocalMaxima(arrayToList([1, 3, 2, 5, 4]))));
test("keepLocalMinima", listToArray(keepLocalMinima(arrayToList([5, 3, 4, 2, 6]))));
test("keepLocalMinimaAndMaxima", listToArray(keepLocalMinimaAndMaxima(arrayToList([1, 3, 2, 5, 4, 6]))));

// products
test("nonNegativeProducts", listToArray(nonNegativeProducts(arrayToList([2, 3, -1, 0.5, 2]))));
test("negativeProducts", listToArray(negativeProducts(arrayToList([-3, -6, 2, -2, -1, -2]))));

// delete
test("deleteFirst", listToArray(deleteFirst(arrayToList([1, 2, 3, 2, 4]), 2)));
test("deleteLast", listToArray(deleteLast(arrayToList([1, 2, 3, 2, 4]), 2)));

// squashList
const inner1 = node(2, node(3, empty<number>()));
const inner2 = node(10, empty<number>());
test("squashList", listToArray(squashList(arrayToList([1, inner1, 4, inner2]))));

// Example helper functions
const increasingByOne = (prev: number, curr: number, next: number) => curr === prev + 1 && next === curr + 1;

const oddSum = (prev: number, curr: number, next: number) => (prev + curr + next) % 2 !== 0;

// Test 1: increasing sequence by 1
const list1 = arrayToList([1, 2, 3, 4, 5]);
test("keepTrendMiddles - increasingByOne", listToArray(keepTrendMiddles(list1, increasingByOne)));
// Expected: [2,3] (2 -> 2,3,4 ; 3 -> 3,4,5)

// Test 2: sum of triple is odd
const list2 = arrayToList([1, 2, 1, 2, 3]);
test("keepTrendMiddles - oddSum", listToArray(keepTrendMiddles(list2, oddSum)));
// Expected: [2,1,2] (middle elements of triples that sum to odd)

// Test 3: edge case - fewer than 3 elements
const list3 = arrayToList([5, 6, 7]);
test("keepTrendMiddles - short list", listToArray(keepTrendMiddles(list3, increasingByOne)));
// Expected: [] (not enough elements for a triple)