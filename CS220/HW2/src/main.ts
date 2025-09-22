// import { Image, COLORS } from "../include/image.js";
// import {
//   imageMapCoord,
//   imageMapIf,
//   mapWindow,
//   isGrayish,
//   makeGrayish,
//   pixelBlur,
//   imageBlur,
//   mapLine,
//   findAverage,
//   isWithinWindow,
// } from "./imageProcessingHOF.js";

// const art = Image.loadImageFromGallery("art");

// art.show();
// imageMapCoord(art, (img, x, y) => {
//   const c = img.getPixel(x, y);
//   if (y % 2 === 0) {
//     return [0, 0, 0];
//   }

//   return c;
// }).show();

// imageMapIf(
//   art,
//   (img, x, y) => {
//     const c = img.getPixel(x, y);
//     return y % 2 === 0;
//   },
//   c => [0, 0, 0]
// ).show();

// mapWindow(art, [3, 50], [3, 50], c => [0, 0, 0]).show();

// makeGrayish(art).show();
// imageBlur(art).show();

// const img = Image.create(2, 2, [0, 100, 200]);
// img.setPixel(0,0, [255,255,255]);
// console.log(pixelBlur(img, 1, 1));

// const whiteImg: Image = Image.create(10, 10, [200,200,200]);
// const newImg = imageMapCoord(whiteImg, (img, x, y) => {
//     const c = img.getPixel(x,y);
//     img.setPixel(x, y, [0, 1, 2].map(i => (x + y + c[i])));
//     return img.getPixel(x,y);
//   });

// console.log(whiteImg.getPixel(0,0));
// console.log(newImg.getPixel());

const arr = [4, 6, 2, 1];

console.log(arr.reduce((acc, i) => (acc += i), 0));
