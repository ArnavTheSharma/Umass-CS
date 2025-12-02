import { Observable, Observer } from "../include/observable.js";

// Extra Credit Functions

export function classifyObservables(obsArr: Observable<number>[]): {
  negative: Observable<number>;
  odd: Observable<number>;
  rest: Observable<number>;
} {
  const negative = new Observable<number>();
  const odd = new Observable<number>();
  const rest = new Observable<number>();

  obsArr.forEach(o => {
    // for each observable
    const observer: Observer<number> = (x: number) => {
      if (x < 0) {
        negative.update(x);
      }
      if (x % 2 !== 0) {
        // also handles negative numbers since -5%2 = -1
        odd.update(x);
      }
      if (!(x < 0) && !(x % 2 !== 0)) {
        rest.update(x);
      }
    };

    o.subscribe(observer);
  });
  return { negative, odd, rest };
}

export function obsStrCond(
  funcArr: ((arg1: string) => string)[],
  f: (arg1: string) => boolean,
  o: Observable<string>
): Observable<string> {
  // TODO: Implement this function
  const ret = new Observable<string>();

  const observer: Observer<string> = (str: string) => {
    const resFromArr: string = funcArr.reduce((acc, fn) => fn(acc), str);
    f(resFromArr) ? ret.update(resFromArr) : ret.update(str);
  };

  o.subscribe(observer);
  return ret;
}

export function statefulObserver(o: Observable<number>): Observable<number> {
  // TODO: Implement this function
  let prev: number | null = null;
  const ret = new Observable<number>();

  const observer: Observer<number> = (x: number) => {
    if (prev === null) {
      prev = x;
      return;
    }
    if (prev !== 0 && x % prev === 0) {
      ret.update(x);
    }
    prev = x;
  };

  o.subscribe(observer);
  return ret;
}

// Optional Additional Practice

// export function classifyTypeObservables(obsArr: (Observable<string> | Observable<number> | Observable<boolean>)[]): {
//   string: Observable<string>;
//   number: Observable<number>;
//   boolean: Observable<boolean>;
// } {
//   // TODO: Implement this function
//   return { string: new Observable(), number: new Observable(), boolean: new Observable() };
// }

// export function mergeMax(o1: Observable<number>, o2: Observable<number>): Observable<{ obs: number; v: number }> {
//   // TODO: Implement this function
//   return new Observable();
// }

// export function merge(o1: Observable<string>, o2: Observable<string>): Observable<string> {
//   // TODO: Implement this function
//   return new Observable();
// }

// export class GreaterAvgObservable extends Observable<number> {
//   constructor() {
//     super();
//   }

//   greaterAvg(): Observable<number> {
//     // TODO: Implement this method
//     return new Observable();
//   }
// }

// export class SignChangeObservable extends Observable<number> {
//   constructor() {
//     super();
//   }

//   signChange(): Observable<number> {
//     // TODO: Implement this method
//     return new Observable();
//   }
// }

// /**
//  * This function shows how the class you created above could be used.
//  * @param numArr Array of numbers
//  * @param f Observer function
//  */
// export function usingSignChange(numArr: number[], f: Observer<number>) {
//   // TODO: Implement this function
// }
