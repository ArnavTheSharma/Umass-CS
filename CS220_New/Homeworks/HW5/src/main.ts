import { BusinessQuery } from "./iterators-generators.js";
import { Business } from "../include/data.js";

const businesses = [
  { business_id: "a" } as Business,
  { business_id: "b" } as Business,
  { business_id: "c" } as Business,
  { business_id: "d" } as Business,
];
const query = new BusinessQuery(businesses);
const result = [...query.slice(1, 3)].map(b => b.business_id);

const businesses2 = [{ business_id: "a" } as Business, { business_id: "b" } as Business];
const query2 = new BusinessQuery(businesses2);
const result2 = [...query.slice(1, 10)].map(b => b.business_id);

console.log(result);
console.log(result2);
