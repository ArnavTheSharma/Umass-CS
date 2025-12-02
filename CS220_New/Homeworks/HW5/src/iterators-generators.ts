import type { Business } from "../include/data";

export function* pairwise<T>(iterable: Iterable<T>): Generator<[T, T]> {
  const iter = iterable[Symbol.iterator]();
  let curr: { value: T; done?: boolean } = iter.next();
  if (curr.done) {
    return;
  } // nothing to iterate over

  let next: { value: T; done?: boolean } = iter.next();

  while (!next.done) {
    yield [curr.value, next.value];
    curr = next;
    next = iter.next();
  }
}

export function* cycle<T>(iterables: Iterable<T>[]): Generator<T> {
  const iterators: Iterator<T>[] = iterables.map(i => i[Symbol.iterator]());
  const isFinished: number[] = new Array<number>(iterables.length).fill(0); // 1 indicates the iterator in that particular index is done, and 0 means it still has some values left

  while (isFinished.includes(0)) {
    for (let i = 0; i < iterables.length; i++) {
      const res = iterators[i].next();
      if (res.done) {
        isFinished[i] = 1;
      } else {
        yield res.value;
      }
    }
  }
}

type FilterFunc = (business: Business) => boolean;

// Declare the BusinessQuery class here.
// Start the declaration with "export class" so that
// it can be imported from other modules.
export class BusinessQuery implements Iterable<Business> {
  private source: Iterable<Business>;

  constructor(iterable: Iterable<Business>) {
    this.source = iterable;
  }

  filter(func: FilterFunc): BusinessQuery {
    return new BusinessQuery(
      (self =>
        function* () {
          for (const business of self.source) {
            if (func(business)) {
              yield business;
            }
          }
        })(this)()
    );
  }

  exclude(func: FilterFunc): BusinessQuery {
    return this.filter(e => !func(e));
  }

  slice(start: number, end?: number) {
    if (!Number.isInteger(start) || (end !== undefined && !Number.isInteger(end))) {
      throw new SliceError("slice: start and end must be integers");
    }
    if (start < 0 || (end !== undefined && end < 0)) {
      throw new SliceError("slice: start and end must be non-negative");
    }
    if (end !== undefined && start > end) {
      throw new SliceError("slice: start must not be greater than end");
    }

    let currIndex = 0;
    if (end !== undefined) {
      return this.filter(_e => {
        // console.log(currIndex);
        // console.log(e);
        return currIndex++ >= start && currIndex <= end;
      });
    } else {
      return this.filter(_e => currIndex++ >= start);
    }
  }

  [Symbol.iterator]() {
    return this.source[Symbol.iterator]();
  }
}

export class SliceError extends Error {}
