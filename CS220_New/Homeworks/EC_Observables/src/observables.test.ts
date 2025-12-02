import assert from "assert";
import { Observable, Observer } from "../include/observable.js";
import { classifyObservables, obsStrCond, statefulObserver } from "./observables.js";

function compareArrays(arr1: number[] | string[], arr2: number[] | string[]) {
  assert(arr1.length === arr2.length, "arrays should be equal in length");
  assert(typeof arr1 === typeof arr2, "arrays should be comparing elements of same type");
  for (let i = 0; i < arr1.length; i++) {
    assert(arr1[i] === arr2[i], "corresponding elements should match");
  }
}

function sampleInputsObsStrCond(): {
  funcs: ((s: string) => string)[];
  predicate: (s: string) => boolean;
} {
  const funcs = [(s: string) => s.toUpperCase(), (s: string) => s + "!"];

  const predicate = (s: string) => s.endsWith("!");

  return { funcs, predicate };
}

function collectObserverOutputsStrings(out: Observable<string>) {
  const results: string[] = [];

  const spy = jest.fn();
  out.subscribe(spy);

  const observer: Observer<string> = (x: string) => results.push(x);
  out.subscribe(observer);
  return { spy: spy, results: results };
}

function collectObserverOutputs(out: Observable<number>) {
  const results: number[] = [];

  const spy = jest.fn();
  out.subscribe(spy);

  const observer: Observer<number> = (x: number) => results.push(x);
  out.subscribe(observer);
  return { spy: spy, results: results };
}

describe("classifyObservables", () => {
  it("should properly place values in correct observable", () => {
    const a = new Observable<number>();
    const b = new Observable<number>();
    const { negative, odd, rest } = classifyObservables([a, b]);

    const { results: resultNeg, spy: spyNeg } = collectObserverOutputs(negative);
    const { results: resultOdd, spy: spyOdd } = collectObserverOutputs(odd);
    const { results: resultRest, spy: spyRest } = collectObserverOutputs(rest);

    a.update(-5); // negative + odd
    a.update(4); // rest
    b.update(3); // odd
    b.update(-2); // negative
    b.update(10); // rest

    compareArrays(resultNeg, [-5, -2]);
    compareArrays(resultOdd, [-5, 3]);
    compareArrays(resultRest, [4, 10]);

    expect(spyNeg).toHaveBeenCalledTimes(2);
    expect(spyNeg).toHaveBeenNthCalledWith(1, -5);
    expect(spyNeg).toHaveBeenNthCalledWith(2, -2);

    expect(spyOdd).toHaveBeenCalledTimes(2);
    expect(spyOdd).toHaveBeenNthCalledWith(1, -5);
    expect(spyOdd).toHaveBeenNthCalledWith(2, 3);

    expect(spyRest).toHaveBeenCalledTimes(2);
    expect(spyRest).toHaveBeenNthCalledWith(1, 4);
    expect(spyRest).toHaveBeenNthCalledWith(2, 10);
  });

  it("should return nothing if there are no observables", () => {
    const { negative, odd, rest } = classifyObservables([]);

    const { results: resultNeg, spy: spyNeg } = collectObserverOutputs(negative);
    const { results: resultOdd, spy: spyOdd } = collectObserverOutputs(odd);
    const { results: resultRest, spy: spyRest } = collectObserverOutputs(rest);

    compareArrays(resultNeg, []);
    compareArrays(resultOdd, []);
    compareArrays(resultRest, []);

    expect(spyNeg).toHaveBeenCalledTimes(0);
    expect(spyOdd).toHaveBeenCalledTimes(0);
    expect(spyRest).toHaveBeenCalledTimes(0);
  });

  it("should work if only 1 observable in array", () => {
    const a = new Observable<number>();
    const { negative, odd, rest } = classifyObservables([a]);

    const { results: resultNeg, spy: spyNeg } = collectObserverOutputs(negative);
    const { results: resultOdd, spy: spyOdd } = collectObserverOutputs(odd);
    const { results: resultRest, spy: spyRest } = collectObserverOutputs(rest);

    a.update(-5); // negative + odd
    a.update(4); // rest
    a.update(3); // odd
    a.update(-2); // negative
    a.update(10); // rest

    compareArrays(resultNeg, [-5, -2]);
    compareArrays(resultOdd, [-5, 3]);
    compareArrays(resultRest, [4, 10]);

    expect(spyNeg).toHaveBeenCalledTimes(2);
    expect(spyNeg).toHaveBeenNthCalledWith(1, -5);
    expect(spyNeg).toHaveBeenNthCalledWith(2, -2);

    expect(spyOdd).toHaveBeenCalledTimes(2);
    expect(spyOdd).toHaveBeenNthCalledWith(1, -5);
    expect(spyOdd).toHaveBeenNthCalledWith(2, 3);

    expect(spyRest).toHaveBeenCalledTimes(2);
    expect(spyRest).toHaveBeenNthCalledWith(1, 4);
    expect(spyRest).toHaveBeenNthCalledWith(2, 10);
  });

  it("should push 0 to Rest", () => {
    const a = new Observable<number>();
    const { negative, odd, rest } = classifyObservables([a]);

    const { results: resultNeg, spy: spyNeg } = collectObserverOutputs(negative);
    const { results: resultOdd, spy: spyOdd } = collectObserverOutputs(odd);
    const { results: resultRest, spy: spyRest } = collectObserverOutputs(rest);

    a.update(0); // negative + odd

    compareArrays(resultNeg, []);
    compareArrays(resultOdd, []);
    compareArrays(resultRest, [0]);

    expect(spyNeg).toHaveBeenCalledTimes(0);
    expect(spyOdd).toHaveBeenCalledTimes(0);
    expect(spyRest).toHaveBeenCalledTimes(1);
    expect(spyRest).toHaveBeenNthCalledWith(1, 0);
  });
});

describe("obsStrCond", () => {
  it("should update with composed result if predicate is true", () => {
    const input = new Observable<string>();
    const { funcs, predicate } = sampleInputsObsStrCond();
    const out = obsStrCond(funcs, predicate, input);
    const { spy, results } = collectObserverOutputsStrings(out);

    input.update("hello");
    const correct = ["HELLO!"];

    compareArrays(results, correct);
    expect(spy).toHaveBeenCalledTimes(1);
    expect(spy).toHaveBeenNthCalledWith(1, "HELLO!");
  });

  it("should update with original result if predicate is false", () => {
    const input = new Observable<string>();
    const { funcs } = sampleInputsObsStrCond();
    const predicate = (s: string) => !s.endsWith("!"); // new predicate: doesn't end with !
    const out = obsStrCond(funcs, predicate, input);
    const { spy, results } = collectObserverOutputsStrings(out);

    input.update("hello");
    const correct = ["hello"];

    compareArrays(results, correct);
    expect(spy).toHaveBeenCalledTimes(1);
    expect(spy).toHaveBeenNthCalledWith(1, "hello");
  });

  it("should correctly process multiple inputs", () => {
    const input = new Observable<string>();
    const { funcs, predicate } = sampleInputsObsStrCond();
    const out = obsStrCond(funcs, predicate, input);
    const { spy, results } = collectObserverOutputsStrings(out);

    input.update("a");
    input.update("b");
    input.update("c");

    const correct = ["A!", "B!", "C!"];

    compareArrays(results, correct);
    expect(spy).toHaveBeenCalledTimes(3);
    expect(spy).toHaveBeenNthCalledWith(1, "A!");
    expect(spy).toHaveBeenNthCalledWith(2, "B!");
    expect(spy).toHaveBeenNthCalledWith(3, "C!");
  });

  it("should apply the functions in funcArr in correct chronological order", () => {
    const input = new Observable<string>();
    const funcs = [(s: string) => s + "A", (s: string) => s + "B", (s: string) => s + s];
    const predicate = () => true; // always accept result
    const out = obsStrCond(funcs, predicate, input);
    const { spy, results } = collectObserverOutputsStrings(out);

    input.update("X");
    // "X" → "XA" → "XAB" → "XABXAB"
    const correct = ["XABXAB"];

    compareArrays(results, correct);
    expect(spy).toHaveBeenCalledTimes(1);
    expect(spy).toHaveBeenNthCalledWith(1, "XABXAB");
  });

  it("should composed on a varying predicate result", () => {
    const input = new Observable<string>();
    const funcs = [
      (s: string) => s + s, // duplicates str
    ];

    const predicate = (s: string) => s.length > 3;

    const out = obsStrCond(funcs, predicate, input);
    const { spy, results } = collectObserverOutputsStrings(out);

    input.update("a"); // "aa" length = 2 so predicate false
    input.update("bb"); // "bbbb" length = 4 so predicate true
    input.update("ccc"); // "cccccc" length = 6 so predicate true
    input.update("d"); // "dd" length = 2 so predicate true

    const correct = ["a", "bbbb", "cccccc", "d"];

    compareArrays(results, correct);
    expect(spy).toHaveBeenCalledTimes(4);
    expect(spy).toHaveBeenNthCalledWith(1, "a");
    expect(spy).toHaveBeenNthCalledWith(2, "bbbb");
    expect(spy).toHaveBeenNthCalledWith(3, "cccccc");
    expect(spy).toHaveBeenNthCalledWith(4, "d");
  });
});

describe("statefulObserver", () => {
  it("should emit divisible values correctly and in right order", () => {
    const input = new Observable<number>();
    const out = statefulObserver(input);
    const { spy, results } = collectObserverOutputs(out);

    input.update(3); // prev = 3
    input.update(9); // 9 divisible by 3. prev = 9
    input.update(4); // 9 not divisible by 4. prev = 4
    input.update(8); // 8 divisible by 4. prev = 8

    const correct: number[] = [9, 8];
    compareArrays(results, correct);

    expect(spy).toHaveBeenCalledTimes(2);
    expect(spy).toHaveBeenNthCalledWith(1, 9);
    expect(spy).toHaveBeenNthCalledWith(2, 8);
  });

  it("should handle case of only 1 element correctly (output nothing)", () => {
    const input = new Observable<number>();
    const out = statefulObserver(input);
    const { spy, results } = collectObserverOutputs(out);

    input.update(6);

    const correct: number[] = [];
    compareArrays(results, correct);

    expect(spy).toHaveBeenCalledTimes(0);
  });

  it("shouldn't add 0 and whatever update comes after 0", () => {
    const input = new Observable<number>();
    const out = statefulObserver(input);
    const { spy, results } = collectObserverOutputs(out);

    input.update(0);
    input.update(4);
    input.update(8);
    input.update(9);

    const correct: number[] = [8];
    compareArrays(results, correct);

    expect(spy).toHaveBeenCalledTimes(1);
    expect(spy).toHaveBeenNthCalledWith(1, 8);
  });

  it("should emit same result to multiple subscribers", () => {
    const input = new Observable<number>();
    const out = statefulObserver(input);

    const { results: result1, spy: spy1 } = collectObserverOutputs(out);
    const { results: result2, spy: spy2 } = collectObserverOutputs(out);

    input.update(3);
    input.update(9);
    input.update(8);
    input.update(16);

    const correct: number[] = [9, 16];
    compareArrays(result1, correct);
    compareArrays(result2, correct);

    expect(spy1).toHaveBeenCalledTimes(2);
    expect(spy1).toHaveBeenNthCalledWith(1, 9);
    expect(spy1).toHaveBeenNthCalledWith(2, 16);
    expect(spy2).toHaveBeenCalledTimes(2);
    expect(spy2).toHaveBeenNthCalledWith(1, 9);
    expect(spy2).toHaveBeenNthCalledWith(2, 16);
  });
});
