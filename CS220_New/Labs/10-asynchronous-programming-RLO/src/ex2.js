/* eslint-disable @typescript-eslint/no-unsafe-member-access */
/* eslint-disable @typescript-eslint/no-unsafe-assignment */
/* eslint-disable @typescript-eslint/no-floating-promises */
// Exercise 2

import { completeHunt, searchClueAtLocation } from "../include/scavenger_hunt.js";

// searchClueAtLocation: this function takes in a location name (string) and returns a Promise that either:
// - fulfills (succeeds) with an object { part: "pass", index: 0 } that contains the part of password and its index
// - rejects (fails) and returns an Error object
// Note: the indices are all integers in the range [0, total number of password parts)

// completeHunt: this function takes in your password (string) and checks if it is correct
// if the password is incorrect, or you take too long to figure out the password, the method will fail

const locations = ["LGRT", "CICS Building", "Worcester DC", "ILC", "Rec Center", "DuBois Library", "Campus Pond"];

// ###########################################################################
// TODO: Find the secret message
// ###########################################################################
// Fill up passwordParts with the password segments you receive from `searchClueAtLocation`
const passwordParts = Array(locations.length).fill("");

const PromisesArr = locations.map(loc => searchClueAtLocation(loc).catch(err => null));

Promise.all(PromisesArr).then(results => {
    results.forEach(result => {
        if (result && result.part && typeof result.index === 'number') {
            passwordParts[result.index] = result.part;
        }
    });

    const password = passwordParts.join("")
    console.log(`Password: ${password}`)
    completeHunt(password)
})

// locations.forEach( loc => {
//     let promise = searchClueAtLocation(loc);
//     promise.then(obj => {
//         passwordParts[obj.index] = obj.part;
//         console.log("Yay");
//         return 0;
//     });
//     promise.catch(obj => {console.log("err");})
// })


// ###########################################################################
// #                             END OF YOUR CODE                            #
// ###########################################################################

// Once you've filled up passwordParts, you may find the three lines below helpful

