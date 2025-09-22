import assert from "assert";
import { Color, COLORS, Image } from "../include/image.js";
import {
  imageMapCoord,
  imageMapIf,
  mapWindow,
  isGrayish,
  makeGrayish,
  pixelBlur,
  imageBlur,
  mapLine,
  findAverage,
  isWithinWindow,
} from "./imageProcessingHOF.js";

// imageMapCoord, imageMap filter on img with given fn
// imageBlur filters entire on img using pixelBlur
// imageMapIf, mapWindow, makeGrayish, mapLine conditional filter with given fn
// isGrayish, pixelBlur is a conditional func itself
// findAverage and isWithinWindow are js helper funcs

// self note: should i put compareImages in my test file, and do i need to really test it? do i needa test expectColorToBeCloseTo?

/**
 * Helper function to check if a color is equal to another one with an error of 1 (default)
 * @param actual color1
 * @param expected color2
 * @param error margin of error
 */
function expectColorToBeCloseTo(actual: Color, expected: Color, error = 1) {
  [0, 1, 2].forEach(i => expect(Math.abs(actual[i] - expected[i])).toBeLessThanOrEqual(error));
}

/**
 * changes each color channel value to the sum of the coordinate the pixel is at
 * forEach on [0,1,2] (to change each index/color channel of the pixel)
 * returns a color whose channels get a new value that depend on the x and y coor
 * @param img input img
 * @param x x coordinate to check
 * @param y y coordinate to check
 * @returns a color to change the particular pixel we're checking to
 */
function gradient(img: Image, x: number, y: number): Color {
  const c = img.getPixel(x, y);
  img.setPixel(
    x,
    y,
    [0, 1, 2].map(i => (x + y + c[i]) % 255)
  );
  return img.getPixel(x, y);
}

function dontModifyOriginalImg<Args extends unknown[]>(
  fn: (img: Image, ...args: Args) => Image,
  args: Args = [] as unknown as Args
): void {
  const original: Image = Image.create(10, 10, COLORS.WHITE);
  const originalCopy: Image = original.copy();
  fn(originalCopy, ...args);
  assert(compareImages(original, originalCopy), "imageMapCoord shouldn't modify original image");
}

/**
 * first checks if widths and heights match
 * does a forEach on img1.coordinates (which just says for each coordinate in img1)
 * gets the pixel for each respective image and compares the pixels
 * @param img1 img1 to compare
 * @param img2 img2 to compare
 * @param error error (default = 1) to handle floats
 * @returns
 */
function compareImages(img1: Image, img2: Image, error = 1) {
  if (img1.width !== img2.width || img1.height !== img2.height) {
    return false;
  }

  try {
    img1.coordinates().forEach(([x, y]) => {
      const color1 = img1.getPixel(x, y);
      const color2 = img2.getPixel(x, y);
      expectColorToBeCloseTo(color1, color2, error);
    });
    return true;
  } catch {
    return false;
  }
}

describe("mapLine", () => {
  // Tests for mapLine go here.

  it("should not change img since lineNo out of bounds (under 0 for one)", () => {
    const lineNo = -1;
    const random = Image.create(10, 10, COLORS.WHITE);
    const randomCopy = random.copy();
    mapLine(random, lineNo, (_r, _g, _b) => [0, 0, 0]);
    assert(compareImages(random, randomCopy), "mapLine shouldn't change anything since lineNo is invalid");
  });

  it("should not change img since lineNo out of bounds (over height for one)", () => {
    const random = Image.create(10, 10, COLORS.WHITE);
    const lineNo: number = random.height + 10;
    const randomCopy = random.copy();
    mapLine(random, lineNo, (_r, _g, _b) => [0, 0, 0]);
    assert(compareImages(random, randomCopy), "mapLine shouldn't change anything since lineNo is invalid");
  });

  it("should change img lineNo 5 to all green", () => {
    const img = Image.create(10, 10, COLORS.WHITE);
    const lineNo = 5;

    mapLine(img, lineNo, (_r, _g, _b) => [0, 255, 0]);

    for (let x = 0; x < img.width; x++) {
      const pixel = img.getPixel(x, lineNo);
      assert(pixel[0] === 0 && pixel[1] === 255 && pixel[2] === 0);
    }
  });

  it("should ignore if lineNo isn't an integer, like a float", () => {
    const img = Image.create(10, 10, COLORS.WHITE);
    const imgCopy = img.copy();

    mapLine(img, 2.5, (_r, _g, _b) => [0, 255, 0]);

    assert(compareImages(img, imgCopy));
  });
});

describe("imageMapCoord", () => {
  it("should apply the function (which is turning the pixel black) to every pixel", () => {
    const img = Image.create(10, 10, COLORS.WHITE);
    const imgBlacked = imageMapCoord(img, (_img, _x, _y) => [0, 0, 0]);

    const newImg = Image.create(img.width, img.height, [0, 0, 0]);

    assert(
      compareImages(imgBlacked, newImg),
      "both images should be identical since 1 is created as entirely black and the other got blacked as a function using imageMap"
    );
  });

  it("should change a blank canvas to a different color if the function depends on coordinates", () => {
    const whiteImg: Image = Image.create(10, 10, COLORS.WHITE);
    const newImg: Image = imageMapCoord(whiteImg, gradient);
    assert(
      !compareImages(whiteImg, newImg),
      "images shouldn't be the same since you applied the gradient function to the white image"
    );
  });

  it("shouldn't change function if the callback does nothing to it", () => {
    const img = Image.create(10, 10, COLORS.WHITE);
    const newImg = imageMapCoord(img, (_img, x, y) => img.getPixel(x, y));
    assert(compareImages(img, newImg));
  });
  it("should change the image if it's only a single pixel", () => {
    const whiteImg: Image = Image.create(1, 1, COLORS.WHITE);
    const newImg: Image = imageMapCoord(whiteImg, gradient);
    assert(
      !compareImages(whiteImg, newImg),
      "images shouldn't be the same since you applied the gradient function to the white image"
    );
  });
  it("should not modify original image", () => {
    dontModifyOriginalImg(imageMapCoord, [(_img: Image, _x: number, _y: number) => [0, 0, 0]]);
  });
});

describe("imageMapIf", () => {
  // More tests for imageMapIf go here
  it("should only change lines that satisfy a given condition", () => {
    const whiteImg: Image = Image.create(10, 10, COLORS.WHITE);
    const newImg = imageMapIf(
      whiteImg,
      (_img, x, _y) => x % 2 == 0,
      _c => COLORS.BLACK
    );
    expectColorToBeCloseTo(whiteImg.getPixel(1, 1), newImg.getPixel(1, 1));
    assert(!compareImages(whiteImg, newImg));

    expect(newImg.getPixel(0, 0)).toEqual(COLORS.BLACK);
    expect(newImg.getPixel(1, 0)).toEqual(COLORS.WHITE);
  });

  it("should not change anything if provided an impossible condition to satisfy in the situation", () => {
    const img = Image.create(10, 10, COLORS.WHITE);
    const newImg = imageMapIf(
      img,
      (_img, x, _y) => x > 1000000,
      _c => COLORS.BLACK
    );
    assert(compareImages(img, newImg));
  });

  it("should modify entire image based on a condition that is always true", () => {
    const img = Image.create(10, 10, COLORS.WHITE);
    const newImg = imageMapIf(
      img,
      (_img, x, _y) => x < 100,
      _c => COLORS.BLACK
    );
    assert(!compareImages(img, newImg));
  });

  it("should not modify original image", () => {
    dontModifyOriginalImg(imageMapIf, [
      (_img: Image, x: number, _y: number) => x % 2 === 0,
      (_c: Color) => COLORS.BLACK,
    ]);
  });
});

describe("isWithinWindow", () => {
  it("should return true since both x and y coors are within window", () => {
    assert(isWithinWindow(5, 5, [0, 10], [0, 10]), "we expect (5,5) to be inside [0,10] x [0,10]");
  });
  it("should return true even with floats", () => {
    assert(
      isWithinWindow(5.5, 5.5, [0.3, 10.5], [0.3, 10.5]),
      "we expect (5.5, 5.5) to be inside [0.3, 10.5] x [0.3, 10.5], with floating point bounds"
    );
  });
  it("should return false since x is out of bounds (underneath)", () => {
    assert(!isWithinWindow(-5, 5, [0, 10], [0, 10])),
      "we expect (-5, 5) to be out of bounds of [0, 10] x [0, 10] since x < lower bound";
  });
  it("should return false since y is out of bounds (over it)", () => {
    assert(!isWithinWindow(5, 500, [0, 10], [0, 10])),
      "we expect (5, 500) to be out of bounds of [0, 10] x [0, 10] since y > upper bound";
  });
  it("should still work if the xInterval or yInterval follows [max, min] instead of [min,max]", () => {
    assert(isWithinWindow(5, 5, [100, 0], [10, 0])),
      "we expect (5, 5) to be in bounds even though the min and max are in the wrong order (instead of [0,10] we recieved [10,0]";
  });
});

describe("mapWindow", () => {
  // More tests for mapWindow go here
  it("should only change pixels within given windows", () => {
    const whiteImg: Image = Image.create(10, 10, COLORS.WHITE);
    const newImg = mapWindow(whiteImg, [0, 3], [0, 3], _c => COLORS.BLACK);
    for (let i = 4; i < 10; i += 3) {
      expectColorToBeCloseTo(newImg.getPixel(i, i), COLORS.WHITE);
    }
    for (let i = 0; i < 3; i++) {
      expectColorToBeCloseTo(newImg.getPixel(i, i), COLORS.BLACK);
    }
  });
  it("should change the image with a window thats 1 pixel big", () => {
    const whiteImg: Image = Image.create(1, 1, COLORS.WHITE);
    const newImg = mapWindow(whiteImg, [0, 3], [0, 3], _c => COLORS.BLACK);
    expectColorToBeCloseTo(newImg.getPixel(0, 0), COLORS.BLACK);
  });
  it("shouldnt round down a floating point lower bound when checking if a pixel is in bounds", () => {
    const whiteImg: Image = Image.create(10, 10, COLORS.WHITE);
    const newImg = mapWindow(whiteImg, [2.3, 5], [2.3, 5], _c => COLORS.BLACK);
    expectColorToBeCloseTo(newImg.getPixel(2, 2), COLORS.WHITE);
    expectColorToBeCloseTo(newImg.getPixel(3, 3), COLORS.BLACK);
  });
  it("should handle negative lower bound intervals correctly", () => {
    const whiteImg: Image = Image.create(10, 10, COLORS.WHITE);
    const blackImg: Image = Image.create(10, 10, COLORS.BLACK);
    const newImg = mapWindow(whiteImg, [-2, 12], [-2, 12], _c => COLORS.BLACK);
    assert(compareImages(newImg, blackImg));
  });

  it("shouldnt change anything if both bounds are negative", () => {
    const whiteImg: Image = Image.create(10, 10, COLORS.WHITE);
    const newImg = mapWindow(whiteImg, [-2, -1], [-2, -1], _c => COLORS.BLACK);
    assert(compareImages(newImg, whiteImg));
  });

  it("should not modify original image", () => {
    dontModifyOriginalImg(mapWindow, [[2, 5], [2, 5], (_c: Color) => COLORS.BLACK]);
  });
});

describe("isGrayish", () => {
  // More tests for isGrayish go here
  it("should return true for very grayish colors", () => {
    assert(isGrayish([100, 101, 102]));
  });
  it("should return false for very non-grayish colors", () => {
    assert(!isGrayish([0, 101, 255]));
  });
  it("should handle barely gray cases", () => {
    assert(isGrayish([0, 85, 0]));
    assert(!isGrayish([0, 86, 0]));
  });
});

describe("makeGrayish", () => {
  // More tests for makeGrayish go here
  it("should convert non-grayish pixels to grayish ones", () => {
    const img = Image.create(2, 2, [255, 0, 0]);
    const newImg = makeGrayish(img);
    assert(newImg.getPixel(0, 0)[0] !== 255); // if it turned every pixel to grayish, it should average every color channel, meaning the first channel can't be 255
  });

  it("should make each new pixels' channel of a non-grayish img to the average of all channels", () => {
    const img = Image.create(2, 2, [180, 0, 0]);
    const newImg = makeGrayish(img);
    assert([0, 1, 2].every(i => newImg.getPixel(0, 0)[i] == 60));
  });

  it("should not change an image if it is already grayish", () => {
    const img = Image.create(2, 2, [180, 180, 180]);
    const newImg = makeGrayish(img);
    assert(compareImages(img, newImg));
  });
  it("should not modify original image", () => {
    dontModifyOriginalImg(makeGrayish);
  });
});

describe("pixelBlur", () => {
  // Tests for pixelBlur go here
  it("should average rgb color channels of all neighbors and itself", () => {
    const img = Image.create(3, 3, [0, 0, 0]);
    img.setPixel(1, 1, [72, 72, 72]);
    const blurredColor: Color = pixelBlur(img, 1, 1);
    assert([0, 1, 2].every(i => blurredColor[i] === 8)); // since 72/9 = 8
  });

  it("should average rgb color of corner channel and divide by 4, not 9", () => {
    const img = Image.create(3, 3, [0, 0, 0]);
    img.setPixel(0, 0, [72, 72, 72]);
    const blurredColor: Color = pixelBlur(img, 0, 0);
    assert([0, 1, 2].every(i => blurredColor[i] === 18)); // since 72/3 = 18
  });
  it("should have different channel values if the averages of each channel is different", () => {
    const img = Image.create(3, 3, [0, 0, 0]);
    img.setPixel(0, 0, [0, 30, 90]);
    const blurredColor: Color = pixelBlur(img, 0, 0);
    assert([0, 1, 2].every(i => blurredColor[i] !== blurredColor[(i + 1) % 3])); // basically compares the colors' channel with the next channel (modulo 3 to loop around as well)
  });
  it("should handle edges pixel blurs correctly", () => {
    const img = Image.create(3, 3, [0, 0, 0]);
    img.setPixel(1, 0, [72, 72, 72]);
    const blurredColor: Color = pixelBlur(img, 1, 0);
    assert([0, 1, 2].every(i => blurredColor[i] === 12)); // since 72/3 = 18
  });
});

describe("imageBlur", () => {
  // Tests for imageBlur go here
  it("should return a different image after the blur is applied", () => {
    const img = Image.create(3, 3, [0, 0, 0]);
    img.setPixel(0, 0, [0, 30, 90]);
    const newImg = imageBlur(img);
    assert(!compareImages(img, newImg));
  });

  it("should not change anything in a uniform picture", () => {
    const img = Image.create(3, 3, [0, 0, 0]);
    const newImg = imageBlur(img);
    assert(compareImages(img, newImg));
  });

  it("should blur a 2x2 image", () => {
    const img = Image.create(2, 2, [0, 0, 0]);
    img.setPixel(1, 1, [200, 200, 200]);
    const newImg = imageBlur(img);
    assert(!compareImages(img, newImg));
  });
  it("should not modify original image", () => {
    dontModifyOriginalImg(imageBlur);
  });
});

describe("findAverage", () => {
  it("should return the average of normal numbers", () => {
    assert(findAverage([0, 1, 2]) === 1);
  });
  it("should return the average of normal numbers (including negatives)", () => {
    assert(findAverage([-1, 0, 1]) === 0);
  });
  it("should return the average of an array of zeroes", () => {
    assert(findAverage([0, 0, 0, 0, 0]) === 0);
  });
  it("should return a float value average of normal numbers", () => {
    assert(findAverage([0, 1]) === 0.5);
  });
  it("should return 0 for an empty array", () => {
    assert(findAverage([]) === 0);
  });
  it("should return the value for arrays with 1 value", () => {
    assert(findAverage([2]) === 2);
  });
});

describe("compareImages", () => {
  it("should return true when comparing a copy of an image", () => {
    const random = Image.create(10, 10, COLORS.WHITE);
    const randomCopy = random.copy();
    assert(compareImages(random, randomCopy), "should return true since identical images");
  });

  it("should return false when comparing 2 images of different colors", () => {
    const redImage = Image.create(50, 25, [255, 0, 0]);
    const blackImage = Image.create(50, 25, [0, 0, 0]);
    assert(
      !compareImages(redImage, blackImage),
      "since I put !compareImages, if the function returns false we should be good"
    );
  });

  it("should return false when comparing 2 images of different dimensions", () => {
    const redImage = Image.create(50, 50, [255, 0, 0]);
    const blackImage = Image.create(50, 25, [0, 0, 0]);
    assert(
      !compareImages(redImage, blackImage),
      "since I put !compareImages, if the function returns false we should be good"
    );
  });
});
