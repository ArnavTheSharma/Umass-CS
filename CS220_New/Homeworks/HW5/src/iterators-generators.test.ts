import assert from "assert";
import { BusinessQuery, cycle, pairwise } from "./iterators-generators";
import { Business } from "../include/data";

function comparePairwiseArrays(arr1: [number, number][], arr2: [number, number][]) {
  assert(arr1.length === arr2.length, "arrays should be equal in length");
  for (let i = 0; i < arr1.length; i++) {
    assert(arr1[i][0] === arr2[i][0] && arr1[i][1] === arr2[i][1], "each pair should be same");
  }
}

function compareArrays<T>(arr1: T[], arr2: T[]) {
  assert(arr1.length === arr2.length, "arrays should be equal in length");
  for (let i = 0; i < arr1.length; i++) {
    assert(arr1[i] === arr2[i], "corresponding elements should match");
  }
}

describe("pairwise", () => {
  // Write tests for pairwise here
  it("returns correct output given a list of length 4", () => {
    const result = [...pairwise([1, 2, 3, 4])] as [number, number][];
    const expected: [number, number][] = [
      [1, 2],
      [2, 3],
      [3, 4],
    ];
    comparePairwiseArrays(result, expected);
  });

  it("returns empty array given a list of no elements", () => {
    const result = [...pairwise([])];
    assert(result.length === 0, "array should be empty");
  });

  it("returns empty array given a list of 1 element", () => {
    const result = [...pairwise([1])];
    assert(result.length === 0, "array should be empty");
  });
});

describe("cycle", () => {
  // Write tests for cycle here
  it("correctly yields values in round-robin order", () => {
    const it1 = [1, 2, 3];
    const it2 = [4, 5];
    const result = [...cycle([it1, it2])];
    const expected = [1, 4, 2, 5, 3];
    compareArrays(result, expected);
  });

  it("works with one iterable", () => {
    const result = [...cycle([[10, 20, 30]])];
    const expected = [10, 20, 30];
    compareArrays(result, expected);
  });

  it("returns empty if multiple iterable are empty", () => {
    const result = [...cycle([[], [], []])];
    assert(result.length === 0, "result should be an empty array");
  });
});

describe("BusinessQuery.slice", () => {
  it("yields the correct slice of businesses", () => {
    const businesses = [
      { business_id: "a" } as Business,
      { business_id: "b" } as Business,
      { business_id: "c" } as Business,
      { business_id: "d" } as Business,
    ];
    const query = new BusinessQuery(businesses);
    const result = [...query.slice(1, 3)].map(b => b.business_id);
    const expected = ["b", "c"];
    compareArrays(result, expected);
  });

  it("yields empty array if start >= length", () => {
    const businesses = [{ business_id: "a" } as Business, { business_id: "b" } as Business];
    const query = new BusinessQuery(businesses);
    const result = [...query.slice(5)];
    assert(result.length === 0, "slice beyond end should get empty array");
  });

  it("yields slice till end if end > length", () => {
    const businesses = [{ business_id: "a" } as Business, { business_id: "b" } as Business];
    const query = new BusinessQuery(businesses);
    const result = [...query.slice(1, 10)].map(b => b.business_id);
    const expected = ["b"];
    compareArrays(result, expected);
  });
});

describe("BusinessQuery.filter", () => {
  it("filters businesses based on conditions", () => {
    const businesses = [
      { business_id: "a", stars: 5 } as Business,
      { business_id: "b", stars: 3 } as Business,
      { business_id: "c", stars: 4 } as Business,
    ];
    const query = new BusinessQuery(businesses);
    const result = [...query.filter(b => (b.stars ?? 0) >= 4)].map(b => b.business_id);
    const expected = ["a", "c"];
    compareArrays(result, expected);
  });

  it("returns empty array if no businesses match filter requirements", () => {
    const businesses = [{ business_id: "a", stars: 2 } as Business, { business_id: "b", stars: 1 } as Business];
    const query = new BusinessQuery(businesses);
    const result = [...query.filter(b => (b.stars ?? 0) > 5)];
    assert(result.length === 0, "none match filter");
  });
});

describe("BusinessQuery.exclude", () => {
  it("excludes businesses based on a condition", () => {
    const businesses = [
      { business_id: "a", stars: 5 } as Business,
      { business_id: "b", stars: 3 } as Business,
      { business_id: "c", stars: 4 } as Business,
    ];
    const query = new BusinessQuery(businesses);
    const result = [...query.exclude(b => (b.stars ?? 0) >= 4)].map(b => b.business_id);
    const expected = ["b"];
    compareArrays(result, expected);
  });

  it("returns all businesses if none match exclusion", () => {
    const businesses = [{ business_id: "a", stars: 5 } as Business, { business_id: "b", stars: 3 } as Business];
    const query = new BusinessQuery(businesses);
    const result = [...query.exclude(b => (b.stars ?? 0) > 10)].map(b => b.business_id);
    const expected = ["a", "b"];
    compareArrays(result, expected);
  });
});
 