// Reduce Review

type Color = [number, number, number];

// Sample Reduce Implementation
function reduce<T, U>(a: T[], f: (acc: U, e: T) => U, init: U): U {
  let result = init;
  for (let i = 0; i < a.length; ++i) {
    result = f(result, a[i]);
  }
  return result;
}

// Example:

const arr = [3, 2, 6, 2, 2, 0];

const result = reduce(arr, (prod, e) => prod * e, 1);
// Alternatively: arr.reduce((prod, e) => prod * e, 1);

console.log(result);

// In class exercises

// Exercise 1
export function sumSquaresPositive(nums: number[]): number {
  // TODO: Implement this function
  return nums.reduce((acc, e) => (e > 0 ? (acc += Math.sqrt(e)) : (acc += 0)), 0);
}

// Exercise 2
export function mainlyBlue(arr: Color[]): number {
  // TODO: Implement this function
  return arr.reduce((count, e) => (e[2] >= 2 * e[0] && e[2] >= 2 * e[1] ? (count += 1) : (count += 0)), 0);
}

export function mainlyBlue2D(arr: Color[][]): number {
  // TODO: Implement this function
  return arr.reduce((count, subarr) => (count += mainlyBlue(subarr)), 0);
}
