import type { Image, Color } from "../include/image.js";

/**
 * helper for makeGrayish and pixelBlur
 * uses .reduce function, where the accumulator (ave) starts at 0, and you add each elements value divided by arr.length
 * @param arr takes an input array
 * @returns a value (doesnt need to be an int)
 */
export function findAverage(arr: number[]): number {
  if (arr.length == 0) {
    return 0;
  }
  return arr.reduce((ave, e) => (ave += e / arr.length), 0);
}

/**
 * applies func to each pixel in a given line of an img.
 * helper for imageMapCoord
 * @param img An image
 * @param lineNo Given line number to check
 * @param func A color transformation function
 */
export function mapLine(img: Image, lineNo: number, func: (img: Image, x: number, y: number) => Color): void {
  if (lineNo >= 0 && lineNo < img.height && Number.isInteger(lineNo)) {
    for (let i = 0; i < img.width; i++) {
      img.setPixel(i, lineNo, func(img, i, lineNo));
    }
  }
  return;
}

/**
 * applies mapLine to each line of img using a for loop
 * @param img An image
 * @param func A color transformation function
 */
export function imageMapCoord(img: Image, func: (img: Image, x: number, y: number) => Color): Image {
  // TODO
  const returnImg = img.copy();

  for (let i = 0; i < img.height; i++) {
    mapLine(returnImg, i, func);
  }

  return returnImg;
}

/**
 * makes a copy of the input img, applies imageMapCoord
 * callback in imageMapCoord is a ternary operator which checks
 * if cond() is satisfied and applies func() if it is, else the same unchanged pixel
 * @param img An image
 * @param cond checks if a pixel in an image satisfies a condition, returns a bool
 * @param func a color transformation function
 * @returns new image
 */
export function imageMapIf(
  img: Image,
  cond: (img: Image, x: number, y: number) => boolean,
  func: (p: Color) => Color
): Image {
  // TODO
  const returnImg = img.copy();
  return imageMapCoord(returnImg, (_img, x, y) => {
    const pNew = returnImg.getPixel(x, y);
    return cond(returnImg, x, y) ? func(pNew) : pNew;
  });
}

/**
 * A helper function used to help mapWindow, which just checks if the x and y parameter is within the x and y provided interval.
 * @param x x coor to check
 * @param y y coor to check
 * @param xInterval; desired x interval - Assumed to be a two element array containing [x_min, x_max]
 * @param yInterval; desired y interval - Assumed to be a two element array containing [y_min, y_max]
 */
export function isWithinWindow(x: number, y: number, xInterval: number[], yInterval: number[]): boolean {
  return (
    x >= Math.min(...xInterval) &&
    x <= Math.max(...xInterval) &&
    y >= Math.min(...yInterval) &&
    y <= Math.max(...yInterval)
  );
}

/**
 * applies `func` to the pixels of `img` for pixels within the x and y interval.
 * basically uses imageMapIf and isWithinWindow as helper function
 * @param img input image
 * @param xInterval Assumed to be a two element array containing [x_min, x_max]
 * @param yInterval Assumed to be a two element array containing [y_min, y_max]
 * @param func color transformation function
 * @returns
 */

export function mapWindow(img: Image, xInterval: number[], yInterval: number[], func: (p: Color) => Color): Image {
  return imageMapIf(img, (_img, x, y) => isWithinWindow(x, y, xInterval, yInterval), func);
}

/**
 * helper function used to help makeGrayish, which just checks if the given color is a grayish color, which means
 * check if the difference between the maximum and minimum color channel value is at most 85
 * @param p; color to check, which is a [r,g,b] array
 *
 */

export function isGrayish(p: Color): boolean {
  // TODO
  const maxVal: number = Math.max(...p); // spread operator
  const minVal: number = Math.min(...p);

  return maxVal - minVal <= 85;
}

/**
 * applies imageMapIf using the isGrayish function for every pixel
 * in callback of imageMapIf, if pixel isn't grayish (which is why we put !isGrayish)
 * then we find the average of the 3 channels of the color, m, (using helper fn) and returns [m,m,m]
 * @param img input image
 * @returns a grayish image
 */

export function makeGrayish(img: Image): Image {
  // TODO
  return imageMapIf(
    img,
    (img, x, y) => !isGrayish(img.getPixel(x, y)),
    (p: Color) => {
      const m = Math.round(findAverage(p));
      return [m, m, m];
    }
  );
}

/**
 *
 * @param img input image
 * @param x x coor of pixel to blur
 * @param y y coor of pixel to blur
 * @returns the new color the pixel should have
 */

export function pixelBlur(img: Image, x: number, y: number): Color {
  // TODO
  // const xInterval = [x - 1, x + 1];
  // const yInterval = [y - 1, y + 1];
  // const validCoords: number[][] = img.coordinates().filter(coor => isWithinWindow(coor[0], coor[1], xInterval, yInterval)); // coor[0] and coor[1] are the x and y coordinates
  // const validColors: number[][] = validCoords.map(([x, y]) => img.getPixel(x, y));

  // return [0, 1, 2].map(i => Math.round(findAverage(validColors.map(e => e[i]))));
  const neighborColors: number[][] = [];
  const xLowerBound = x === 0 ? 0 : x - 1;
  const yLowerBound = y === 0 ? 0 : y - 1;
  const xUpperBound = x === img.width - 1 ? img.width - 1 : x + 1;
  const yUpperBound = y === img.height - 1 ? img.height - 1 : y + 1;

  for (let i = xLowerBound; i <= xUpperBound; i++) {
    for (let j = yLowerBound; j <= yUpperBound; j++) {
      neighborColors.push(img.getPixel(i, j)); // creates an array of colors (9 in total)
    }
  }
  // i should return the average value of rgb, respectively
  return [0, 1, 2].map(i => Math.round(findAverage(neighborColors.map(color => color[i]))));
}

/**
 * applies imageMapCoord to the image and applies pixelBlur to each pixel
 * @param img input image
 * @returns new image
 */
export function imageBlur(img: Image): Image {
  // TODO
  const res = img.copy();
  return imageMapCoord(res, (_e, x, y) => pixelBlur(img, x, y));
}

// imageMapCoord and imageMapIf => former is same as latter except it'll always apply the function, vs latter applies a func with diff parameters iff func2 returns true
//    for mapWindow you must use imageMapIf: same thing, except applied func if coor is within given xy range
//    makeGrayish does the same thing. Func1 makes it trunacated average of rgb. func2 checks isGrayish
// pixelBlur uses trunucated averages as well, but with the rgb of itself and 8 surrounding pizels, while makeGrayish just its own 3 rgb coors
//    imageBlur is just imageMapCoord but the func you input is pixelBlur
