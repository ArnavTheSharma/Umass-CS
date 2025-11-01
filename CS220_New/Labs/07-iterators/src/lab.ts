// In Class Exercises

/**
 * EXERCISE 1
 *
 * Implement the function makeRepeat that returns an instance of a custom RepeatIterator class.
 * The RepeatIterator class should be an iterable iterator that produces the given value N times.
 * N is assumed to be a non-negative integer.
 */

class RepeatIterator<T> implements IterableIterator<T> {
  // TODO: Implement this class
  private remaining: number;
  private value?: T;

  constructor(value: T, n: number) {
    this.value = value;
    this.remaining = n;
  }

  next(): IteratorResult<T> {
    return this.remaining-- > 0 ? { value: this.value as any, done: false } : { value: undefined, done: true };
  }

  [Symbol.iterator]() {
    return this;
  }
}

export function makeRepeat<T>(value: T, count: number) {
  // TODO: Implement this function
  return new RepeatIterator(value, count); // replace as needed
}

// Try using the iterator produced by makeRepeat below!
// Run the code below after uncommenting it using npm run start

// for (const item of makeRepeat("spam", 3)) {
//     console.log(item);
// }

// for (const item of makeRepeat("ham", 1)) {
//     console.log(item);
// }

// for (const item of makeRepeat("egg", 0)) {
//     console.log(item);
// }

/**
 * EXERCISE 2
 *
 * Write a function that takes in an array of iterables and returns an instance of a custom
 * ChainIterator class. The ChainIterator class should be an iterable iterator that yields
 * items from the first iterable until it runs out. It should then yield items from the next
 * iterable in the given array, repeating this process until reaching the end of the array of
 * iterables.
 *
 * You may assume the given array is not empty.
 *
 */

class ChainIterator<T> {
  // TODO: Implement this class
  private arr: Iterable<T>[];
  private currentIterator: Iterator<T>;
  private index = 0;

  constructor(arr: Iterable<T>[]) {
    this.arr = arr;
    this.currentIterator = this.arr[0][Symbol.iterator]();
  }

  next(): IteratorResult<T> {
    while (this.index < this.arr.length) {
      if (this.currentIterator) {
        // do we have an iterator object at all in our current index (is it truthy)
        const iteratorItem = this.currentIterator.next();

        if (iteratorItem.done === false) {
          // current iterator item in current index isn't done yet
          return { value: iteratorItem.value, done: false };
        } else {
          if (++this.index < this.arr.length) {
            // next index is still within bounds of array
            this.currentIterator = this.arr[this.index][Symbol.iterator]();
          } else {
            this.currentIterator = null as any;
          }
        }
      }
    }
    return { value: undefined, done: true };
  }

  [Symbol.iterator]() {
    return this;
  }
}

export function makeChain<T>(iterables: Iterable<T>[]): ChainIterator<T> {
  // TODO: Implement this function
  return new ChainIterator(iterables); // replace as needed
}
