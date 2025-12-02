import assert from "assert";
import fetchMock from "jest-fetch-mock";
import { arrayIsShort, getObjsWithName } from "./lab.js";
import { jest } from "@jest/globals";

const SECOND = 1000;
jest.setTimeout(30 * SECOND);

// example from lab slides
describe("arrayIsShort", () => {
  beforeEach(() => {
    fetchMock.enableMocks();
  });

  afterEach(() => {
    fetchMock.resetMocks();
    fetchMock.disableMocks();
  });

  it("returns yes if promise fulfills with short array", async () => {
    fetchMock.mockResponseOnce(JSON.stringify([1]));
    const res = await arrayIsShort("test_url");
    expect(res).toEqual("yes");
  });

  it("returns no if promise fulfills with long array", async () => {
    fetchMock.mockImplementation(url => {
      const arr = JSON.stringify([1, 2, 3, 4, 5]);
      return Promise.resolve(new Response(arr));
    });
    const res = await arrayIsShort("test_url");
    expect(res).toEqual("no");
  });

  it("returns uh oh if promise from fetch rejects", async () => {
    fetchMock.mockReject(new Error("Test"));
    const res = await arrayIsShort("test_url");
    expect(res).toEqual("Test");
  });

  it("returns uh oh if fetch produces not ok response", async () => {
    fetchMock.mockResponseOnce(JSON.stringify([]), { status: 404 });
    const res = await arrayIsShort("test_url");
    expect(res).toEqual("Response error");
  });

  it("returns uh oh if fetch doesnt produce an array", async () => {
    fetchMock.mockResponseOnce(JSON.stringify({ a: 1 }));
    const res = await arrayIsShort("test_url");
    expect(res).toEqual("Not an array");
  });
});

// Exercise 2 and 3: write tests for getObjsWithName
describe("getObjsWithName", () => {
  // Todo: returns empty arr if promises reject. case which every obj has names, some have names, none have name, and mix of fulfilling and rejecting
  beforeEach(() => {
    fetchMock.enableMocks();
  });

  afterEach(() => {
    fetchMock.resetMocks();
    fetchMock.disableMocks();
  });

  // TODO: write your tests here

  it("returns empty arr if all promises reject", async () => {
    fetchMock.mockReject(new Error("Test"));
    const urls = ["a", "b", "c"];
    const res = await getObjsWithName(urls);
    expect(res).toEqual([]);
  });

  it("works if all objects have a name property", async () => {
    const objs = {
      a: [{ name: "a1" }, { name: "a2" }],
      b: [{ name: "b1" }, { name: "b2" }],
      c: [{ name: "c1" }, { name: "c2" }],
    };

    fetchMock.mockImplementation(url => {
      return Promise.resolve({
        ok: true,
        json: () => Promise.resolve(objs[url]),
      })
    });
    const urls = ["a", "b", "c"];
    // const res = await getObjsWithName(urls);
    // expect(res).toEqual([]); // double check this line

    const res = await getObjsWithName(urls);
    expect(res.every(obj => "name" in obj)).toBe(true);
    expect(res).toEqual([
      { name: "a1" }, { name: "a2" },
      { name: "b1" }, { name: "b2" },
      { name: "c1" }, { name: "c2" },
    ]);
  });


  
});
