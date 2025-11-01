import assert from "assert";

import type { StableMatcher, StableMatcherWithTrace } from "../include/stableMatching.js";

function randomInt(min: number, max: number) {
  return Math.floor(Math.random() * (max - min)) + min;
}

export function generateInput(n: number) {
  // map = final nxn array
  const map = [];
  // refArr is just an array from 0 to n-1 in order (doesn't change)
  const refArr = [];
  for (let i = 0; i < n; i++) {
    refArr.push(i);
  }
  // for each row of the map
  for (let i = 0; i < n; i++) {
    const helperArr = [...refArr];
    // shuffles helperArr to add to the ith row of map
    for (let j = n - 1; j > 0; j--) {
      const randIndex = randomInt(0, j + 1);
      // swap 2 indices
      const temp = helperArr[j];
      helperArr[j] = helperArr[randIndex];
      helperArr[randIndex] = temp;
    }
    map[i] = helperArr;
  }
  return map;
}

const NUM_TESTS = 20; // Change this to some reasonably large value
const N = 6; // Change this to some reasonable size

function checkDuplicatesAndInValidRange(arr: number[]) {
  const zeroArr = new Array(arr.length).fill(0);
  return arr.every(e => e >= 0 && e < arr.length && ++zeroArr[e] < 2);
}

// bad time complexity implementation for finding candidate's ID

// function checkValidMap(m: number[][], n:number):boolean {
//   return m.every(subarr => (subarr.length === n) && checkDuplicatesAndInValidRange(subarr));
// }
// function getCandidate(hires: Hire[], company:number):number {
//   return hires.reduce((acc:number, e:Hire) => e.company === company ? acc += e.candidate : acc += 0, 0);
// }

/**
 * Tests whether or not the supplied function is a solution to the stable matching problem.
 * @param makeStableMatching A possible solution to the stable matching problem
 * @throws An `AssertionError` if `makeStableMatching` in not a solution to the stable matching problem
 */
export function stableMatchingOracle(makeStableMatching: StableMatcher) {
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
 * do not match with the result (output).
 */
export function stableMatchingRunOracle(makeStableMatchingTrace: StableMatcherWithTrace): void {
  for (let i = 0; i < NUM_TESTS; ++i) {
    const companies = generateInput(N);
    const candidates = generateInput(N);
    const { trace, out } = makeStableMatchingTrace(companies, candidates);

    // TODO: Assertions go here

    const n = companies.length;

    const companyMatches: number[] = Array<number>(n).fill(-1);
    const candidateMatches: number[] = Array<number>(n).fill(-1);
    const companyProposalCount: number[] = Array<number>(n).fill(0);
    const candidateProposalCount: number[] = Array<number>(n).fill(0);

    for (const offer of trace) {
      assert(typeof(offer.from) === "number" && offer.from >= 0 && offer.from < n, "Invalid offer.from value or type");
      assert(typeof(offer.to) === "number" && offer.to >= 0 && offer.to < n, "Invalid offer.to value or type");
      assert(typeof(offer.fromCo) === "boolean", "Invalid offer.fromCo type");

      const from = offer.from;
      const to = offer.to;
      const fromCo = offer.fromCo;

      if (fromCo) {
        // if offer from company
        const proposalCount = companyProposalCount[from];
        assert(proposalCount < n, "Company already made offers to all candidates");

        const expectedTarget = companies[from][proposalCount];
        assert(to === expectedTarget, "Company isn't proposing to correct candidate in queue of his preference list.");

        companyProposalCount[from]++;

        const currentMatch = candidateMatches[to];
        if (currentMatch === -1) {
          // Candidate isn't matched yet
          candidateMatches[to] = from;
          companyMatches[from] = to;
        } else {
          const prefersNew = prefers(candidates[to], from, currentMatch);
          if (prefersNew) {
            companyMatches[currentMatch] = -1;
            candidateMatches[to] = from;
            companyMatches[from] = to;
          }
        }
      } else {
        const proposalCount = candidateProposalCount[from];
        assert(proposalCount < n, `Candidate ${from} has already made offers to all companies`);

        const expectedTarget = candidates[from][proposalCount];
        assert(
          to === expectedTarget,
          `Candidate ${from} should propose to company ${expectedTarget} (preference ${proposalCount}), not ${to}`
        );

        candidateProposalCount[from]++;

        const currentMatch = companyMatches[to];
        if (currentMatch === -1) {
          // Company unmatched
          companyMatches[to] = from;
          candidateMatches[from] = to;
        } else {
          const prefersNew = prefers(companies[to], from, currentMatch);
          if (prefersNew) {
            candidateMatches[currentMatch] = -1;
            companyMatches[to] = from;
            candidateMatches[from] = to;
          }
        }
      }
    }

    // Final output validation after company and candidate matches are all iterated through fully
    assert(
      out.length === companyMatches.filter(x => x !== -1).length,
      "Output length should match number of matched companies"
    );

    const companySet = new Set<number>();
    const candidateSet = new Set<number>();

    for (const hire of out) {
      assert(hire.company >= 0 && hire.company < n, "Company index out of range");
      assert(hire.candidate >= 0 && hire.candidate < n, "Candidate index out of range");

      const expectedCandidate = companyMatches[hire.company];
      const expectedCompany = candidateMatches[hire.candidate];

      assert(expectedCandidate === hire.candidate, "Company matched to wrong candidate");
      assert(expectedCompany === hire.company, "Candidate matched to wrong company");

      assert(!companySet.has(hire.company), "Duplicate company in output");
      assert(!candidateSet.has(hire.candidate), "Duplicate candidate in output");

      companySet.add(hire.company);
      candidateSet.add(hire.candidate);
    }
  }
}

// Returns true if preferred is ranked higher than current in preferenceList.
function prefers(preferenceList: number[], preferred: number, current: number): boolean {
  const preferredIndex = preferenceList.indexOf(preferred);
  const currentIndex = preferenceList.indexOf(current);
  return preferredIndex < currentIndex;
}
