import assert from "assert";
import { node, empty, arrayToList, listToArray } from "../include/lists.js";
// listToArray and arrayToList are provided for your testing convenience only.
import {
  listSum,
  productsHelper,
  contains,
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

function assertArrayEquality(a: number[], b: number[]): boolean {
  return a.length === b.length && a.every((element, i) => element === b[i]);
}

describe("listSum", () => {
  it("should sum empty lists", () => {
    assert(listSum(empty()) === 0, "empty array should sum to 0");
  });

  it("should sum any list of numbers", () => {
    assert(listSum(arrayToList([1, 2, 3])) === 6, "sum of 1,2,3 = 6");
  });

  it("should sum a list of numbers if one of them is negative", () => {
    assert(listSum(arrayToList([1, 2, -3])) === 0, "sum of 1,2,-3 = 0");
  });
});

describe("productsHelper", () => {
  it("should work for a condition like x is even", () => {
    const lst = arrayToList([2, 3, 0, 4]);
    assert(assertArrayEquality(listToArray(productsHelper(lst, x => x % 2 == 0)), [2, 4]));
  });
});

describe("contains", () => {
  it("should return true if a list contains element", () => {
    assert(contains(arrayToList([1, 2, 3]), 1), "should return true since 1 is in [1,2,3]");
  });
  it("should return false if a list doesn't contain an element", () => {
    assert(!contains(arrayToList([1, 2, 3]), 20), "should return false since 20 isnt in [1,2,3]");
  });
  it("should return false if a list doesn't contain an element of a floating type", () => {
    assert(!contains(arrayToList([1, 2, 3]), 2.2), "should return false since 2.2 isnt in [1,2,3]");
  });
});

describe("insertOrdered", () => {
  // Tests for insertOrdered go here
  it("inserts an element into an empty list", () => {
    assert(assertArrayEquality(listToArray(insertOrdered(empty(), 5)), [5]));
  });
  it("should insert at index 0 of the list", () => {
    const lst = arrayToList([1, 2, 3]);
    assert(assertArrayEquality(listToArray(insertOrdered(lst, 0)), [0, 1, 2, 3]));
  });
  it("should insert a float in middle of a list of ints", () => {
    const lst = arrayToList([1, 2, 5]);
    assert(assertArrayEquality(listToArray(insertOrdered(lst, 3.3)), [1, 2, 3.3, 5]));
  });
  it("should insert an int in middle of a list of ints", () => {
    const lst = arrayToList([1, 2, 5]);
    assert(assertArrayEquality(listToArray(insertOrdered(lst, 3)), [1, 2, 3, 5]));
  });
});

describe("everyNRev", () => {
  // Tests for everyNRev go here
  it("selects every nth element in reverse", () => {
    const lst = arrayToList([1, 2, 3, 4, 5, 6]);
    assert(assertArrayEquality(listToArray(everyNRev(lst, 2)), [6, 4, 2]));
  });
});

describe("everyNCond", () => {
  // Tests for everyNCond go here
  it("selects every Nth element after filtering by the condition, not before", () => {
    const lst = arrayToList([1, 2, 3, 4, 5, 6]);
    assert(assertArrayEquality(listToArray(everyNCond(lst, 2, x => x % 2 === 0)), [4]));
  });
});

describe("keepTrendMiddles", () => {
  // Tests for keepTrendMiddles go here
  it("should return elements whose neighbors satisfy the given condition", () => {
    const lst = arrayToList([1, 2, 3, 4, 5]);
    const consecutive = (prev: number, curr: number, next: number) => curr === prev + 1 && curr === next - 1;
    assert(assertArrayEquality(listToArray(keepTrendMiddles(lst, consecutive)), [2, 3, 4]));
  });
  it("shouldnt do anything for length 0", () => {
    assert(assertArrayEquality(listToArray(keepTrendMiddles(empty(), () => true)), []));
  });
  it("shouldnt do anything for length 1", () => {
    assert(assertArrayEquality(listToArray(keepTrendMiddles(arrayToList([1]), () => true)), []));
  });
  it("shouldnt do anything for length 2", () => {
    assert(assertArrayEquality(listToArray(keepTrendMiddles(arrayToList([2, 1]), () => true)), []));
  });
});

describe("keepLocalMaxima", () => {
  // Tests for keepLocalMaxima go here
  it("should know local maximas", () => {
    const lst = arrayToList([1, 5, 3, 6, 0]);
    assert(assertArrayEquality(listToArray(keepLocalMaxima(lst)), [5, 6]));
  });
  it("shouldn't count equal neighbors as maxima", () => {
    const lst = arrayToList([3, 3, 2, 4, 4, 5]);
    assert(assertArrayEquality(listToArray(keepLocalMaxima(lst)), []));
  });
});

describe("keepLocalMinima", () => {
  // Tests for keepLocalMinima go here
  it("should know local minimas", () => {
    const lst = arrayToList([5, 3, 4, 2, 6]);
    assert(assertArrayEquality(listToArray(keepLocalMinima(lst)), [3, 2]));
  });
});

describe("keepLocalMinimaAndMaxima", () => {
  // Tests for keepLocalMinimaAndMaxima go here
  it("should keep both local minimas and maximas", () => {
    const lst = arrayToList([1, 3, 2, 5, 4, 6]);
    assert(assertArrayEquality(listToArray(keepLocalMinimaAndMaxima(lst)), [3, 2, 5, 4]));
  });
});

describe("nonNegativeProducts", () => {
  // Tests for nonNegativeProducts go here
  it("should know to multiply the positive products", () => {
    const lst = arrayToList([2, 3, -1, 0.5, 2]);
    assert(assertArrayEquality(listToArray(nonNegativeProducts(lst)), [2, 6, 0.5, 1]));
  });
  it("should handle 0 correctly", () => {
    const lst = arrayToList([1, 2, 0]);
    assert(assertArrayEquality(listToArray(nonNegativeProducts(lst)), [1, 2]));
  });
});

describe("negativeProducts", () => {
  // Tests for nonNegativeProducts go here
  it("should know to multiply the negative products", () => {
    const lst = arrayToList([-3, -6, 2, -2, -1, -2]);
    assert(assertArrayEquality(listToArray(negativeProducts(lst)), [-3, 18, -2, 2, -4]));
  });
  it("should handle 0 correctly", () => {
    const lst = arrayToList([-1, -2, 0]);
    assert(assertArrayEquality(listToArray(negativeProducts(lst)), [-1, 2]));
  });
});

describe("deleteFirst", () => {
  // Tests for deleteFirst go here
  it("should delete the first instance if it is the first element", () => {
    const lst = arrayToList([-3, -6, 2, -2, -1, -2]);
    assert(assertArrayEquality(listToArray(deleteFirst(lst, -3)), [-6, 2, -2, -1, -2]));
  });
});

describe("deleteLast", () => {
  // Tests for deleteLast go here
  it("should delete the last instance even if it is the first element", () => {
    const lst = arrayToList([-3, -6, 2, -2, -1, -2]);
    assert(assertArrayEquality(listToArray(deleteLast(lst, -3)), [-6, 2, -2, -1, -2]));
  });
});

describe("squashList", () => {
  // Tests for squashList go here
  it("should find the sum of any sublists within the big list", () => {
    const inner1 = node(2, node(3, empty()));
    const inner2 = node(10, empty());
    const lst = arrayToList([1, inner1, 2, inner2]);

    assert(assertArrayEquality(listToArray(squashList(lst)), [1, 5, 2, 10]));
  });
});
