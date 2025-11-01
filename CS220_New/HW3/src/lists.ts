import { List, node, empty, reverseList } from "../include/lists.js";

/**
 * Helper function that returns the sum of a list of numbers with recursion
 * @param l list
 * @returns sum of every element in list
 */
export function listSum(l: List<number>): number {
  if (l.isEmpty()) {
    return 0;
  } else {
    return l.head() + listSum(l.tail());
  }
}

/**
 * Helper for nonNegativeProducts and negativeProducts
 * reverse list of our .reduce() since our.reduce() impelmentation actually adds each element to the start of the list, not the end
 * checks if the condition of the current element we are iterating through is true. If it is, then add the product of the element with the localProduce, else dont do anything and reset the localProduct to 1
 * @param lst
 * @param cond either x > 0 or x < 0 for the 2 functions we're helping
 * @returns
 */
export function productsHelper(lst: List<number>, cond: (n: number) => boolean): List<number> {
  let localProduct = 1;
  let returnList = empty<number>();

  lst.reduce((_acc, e) => {
    if (cond(e) && e !== 0) {
      localProduct *= e;
      returnList = node(localProduct, returnList);
    } else {
      localProduct = 1;
    }
    return returnList;
  }, empty<number>());

  return reverseList(returnList);
}

/**
 * if the list is empty, new list with just el
 * else check if el is less than the head (since were going ascending order). if it is, then insert the element and have it point to the list
 * if its not, then return the head pointed to the next recursive step of insertOrdered
 * @param lst list
 * @param el element to insert
 * @returns new list
 */
export function insertOrdered(lst: List<number>, el: number): List<number> {
  if (lst.isEmpty()) {
    return node(el, empty<number>());
  }
  if (el < lst.head()) {
    return node(el, lst);
  } else {
    return node(lst.head(), insertOrdered(lst.tail(), el));
  }
}

/**
 * helper for deleteLast, which just does a loop and checks if an element is in a list
 * @param lst
 * @param el
 * @returns
 */
export function contains<T>(lst: List<T>, el: T): boolean {
  while (!lst.isEmpty()) {
    if (lst.head() === el) {
      return true;
    }
    lst = lst.tail();
  }
  return false;
}

/**
 *
 * @param lst
 * @param n
 * @returns
 */
export function everyNRev<T>(lst: List<T>, n: number): List<T> {
  let i = 0;
  return lst.reduce((acc: List<T>, e: T) => {
    i++;
    return i % n == 0 ? node(e, acc) : acc;
  }, empty<T>());
}

export function everyNCond<T>(lst: List<T>, n: number, cond: (e: T) => boolean): List<T> {
  return reverseList(everyNRev(lst.filter(cond), n));
}

/**
 * This first checks if the list is long enough (if it's of length 0, 1, or 2 then its not)
 * then it sets the first node of the list as prev (we initialize curr as 2nd element since the first element has no prev)
 * then it recursively calls this function on the rest of the list, list.tail(), and if our current element satisfies allSatisfy, then add that element to the recursive call of restOfList
 * @param lst list
 * @param allSatisfy arbitrary function that checks if an element and its neighbors satisfy a property
 * @returns a list of elements that satisfy allSatisfy
 */
export function keepTrendMiddles(
  lst: List<number>,
  allSatisfy: (prev: number, curr: number, next: number) => boolean
): List<number> {
  if (lst.isEmpty() || lst.tail().isEmpty() || lst.tail().tail().isEmpty()) {
    return empty();
  }

  const prev = lst.head();
  const curr = lst.tail().head();
  const next = lst.tail().tail().head();

  const restOfList = keepTrendMiddles(lst.tail(), allSatisfy);
  return allSatisfy(prev, curr, next) ? node(curr, restOfList) : restOfList;
}

export function keepLocalMaxima(lst: List<number>): List<number> {
  return keepTrendMiddles(lst, (prev: number, curr: number, next: number): boolean => {
    return prev < curr && next < curr;
  });
}

export function keepLocalMinima(lst: List<number>): List<number> {
  return keepTrendMiddles(lst, (prev: number, curr: number, next: number): boolean => {
    return prev > curr && next > curr;
  });
}

export function keepLocalMinimaAndMaxima(lst: List<number>): List<number> {
  return keepTrendMiddles(lst, (prev: number, curr: number, next: number): boolean => {
    return (prev < curr && next < curr) || (prev > curr && next > curr);
  });
}

export function nonNegativeProducts(lst: List<number>): List<number> {
  return productsHelper(lst, x => x > 0);
}

export function negativeProducts(lst: List<number>): List<number> {
  return productsHelper(lst, x => x < 0);
}

/**
 * It checks each node from the front, and if the head matches el, it skips that node by returning the tail.
 * Otherwise, it keeps the head and recurses on the tail until the first match is removed.
 * @param lst
 * @param el
 * @returns
 */
export function deleteFirst<T>(lst: List<T>, el: T): List<T> {
  if (lst.isEmpty()) {
    return empty();
  }

  if (lst.head() === el) {
    return lst.tail();
  }

  return node(lst.head(), deleteFirst(lst.tail(), el));
}

/**
 * It recurses down to the end of the list, deleting the last occurrence of el in the tail first.
 * On the way back up, if the current head matches el and the tail was untouched (aka is the element in the tail through using the helper function contains),
 * it skips this head; otherwise it rebuilds the list, avoiding any earlier matches of el in the list
 * @param lst
 * @param el
 * @returns
 */
export function deleteLast<T>(lst: List<T>, el: T): List<T> {
  return reverseList(deleteFirst(reverseList(lst), el));
}

/**
 * lst.map() each element e. If e is a number, return e, else it must be of type List<number>, so call listSum helper
 * @param lst input list, whose elements are either a number of list of numbers
 * @returns list of only numbers
 */
export function squashList(lst: List<number | List<number>>): List<number> {
  return lst.map((e: number | List<number>) => (typeof e === "number" ? e : listSum(e)));
}
