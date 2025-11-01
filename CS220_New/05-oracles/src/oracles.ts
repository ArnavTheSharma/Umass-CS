import assert from "assert";

import type { StableMatcher, StableMatcherWithTrace } from "../include/stableMatching.js";

function randomInt(min: number, max: number): number {
  return Math.floor(Math.random() * (max - min)) + min;
}

export function generateInput(n: number): number[][] {
  // map = final nxn array
  const map: number[][] = [];

  // refArr is just an array from 0 to n-1 in order (doesn't change)
  const refArr: number[] = [];
  for (let i = 0; i < n; i++) {
    refArr.push(i);
  }

  // for each row of the map
  for (let i = 0; i < n; i++) {
    const helperArr = [...refArr];

    // shuffles helperArr to add to the ith row of map
    for (let j = n - 1; j > 0; j--) {
      const randIndex: number = randomInt(0, j + 1);

      // swap 2 indices
      const temp = helperArr[j];
      helperArr[j] = helperArr[randIndex];
      helperArr[randIndex] = temp;
    }
    map[i] = helperArr;
  }

  return map;
}

const NUM_TESTS = 100; // Change this to some reasonably large value
const N = 10; // Change this to some reasonable size

function checkDuplicatesAndInValidRange(arr: number[]): boolean {
  const zeroArr: number[] = new Array<number>(arr.length).fill(0);
  return arr.every(e => e >= 0 && e < arr.length && ++zeroArr[e] < 2);
}

// function checkValidMap(m: number[][], n:number):boolean {
//   return m.every(subarr => (subarr.length === n) && checkDuplicatesAndInValidRange(subarr));
// }

// bad time complexity implementation for finding candidate's ID
// function getCandidate(hires: Hire[], company:number):number {
//   return hires.reduce((acc:number, e:Hire) => e.company === company ? acc += e.candidate : acc += 0, 0);
// }

/**
 * Tests whether or not
 *  the supplied function is a solution to the stable matching problem.
 * @param makeStableMatching A possible solution to the stable matching problem
 * @throws An `AssertionError` if `makeStableMatching` in not a solution to the stable matching problem
 */
export function stableMatchingOracle(makeStableMatching: StableMatcher): void {
  for (let i = 0; i < NUM_TESTS; ++i) {
    const companies = generateInput(N);
    const candidates = generateInput(N);
    const hires = makeStableMatching(companies, candidates);

    // checks if input maps are valid
    // assert(companies.length === hires.length, "Hires length is correct.");
    // assert(candidates.length === hires.length, "Candidates length is correct.");
    // assert(checkValidMap(companies, N) && checkValidMap(candidates, N) === true, "both maps should be valid");

    const candidateList: number[] = [];
    const companyList: number[] = [];
    hires.forEach(hire => {
      candidateList.push(hire.candidate);
      companyList.push(hire.company);
    });
    assert(checkDuplicatesAndInValidRange(candidateList));
    assert(checkDuplicatesAndInValidRange(companyList));

    hires.forEach(hire => {
      // current candidate/company
      const candidate = hire.candidate;
      const candidatePreferences = candidates[candidate];
      const candidateMatchRanking = candidatePreferences.indexOf(hire.company); // candidate's preference of matched company (0 if first, 1 if second, etc etc)

      // loop through every company the candidate prefers more than current
      for (let i = 0; i < candidateMatchRanking; i++) {
        const preferredCompany = candidatePreferences[i]; // current iteration company when looping through the "better" companies
        const preferredCompanysObj = hires.find(e => e.company === preferredCompany); // preferred Company's Hire object
        const preferredCompanysMatch = preferredCompanysObj ? preferredCompanysObj.candidate : -1; // -1 if obj not defined

        const preferredCompanysPreferences = companies[preferredCompany];
        assert(
          preferredCompanysPreferences.indexOf(preferredCompanysMatch) <
            preferredCompanysPreferences.indexOf(candidate),
          "instability exists"
        ); // checks if this is an unstable match
      }
    });
    // TODO: More assertions go here.
  }
}

// Part B

/**
 * Tests whether or not the supplied function follows the supplied algorithm.
 * @param makeStableMatchingTrace A possible solution to the stable matching problem and its possible steps
 * @throws An `AssertionError` if `makeStableMatchingTrace` does not follow the specified algorithm, or its steps (trace)
 * do not match with the result (out).
 */
export function stableMatchingRunOracle(makeStableMatchingTrace: StableMatcherWithTrace): void {
  for (let i = 0; i < NUM_TESTS; ++i) {
    const companies = generateInput(N);
    const candidates = generateInput(N);
    const { trace, out } = makeStableMatchingTrace(companies, candidates);

    // This statement is here to prevent linter warnings about `trace` and `out` not being used.
    // Remove it as necessary.
    console.log(trace, out);

    // TODO: Assertions go here
  }
}
