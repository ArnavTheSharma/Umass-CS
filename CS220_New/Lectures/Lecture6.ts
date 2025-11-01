export {};
// ======================
// 1. WHAT IS A CLOSURE?
// ======================
// A closure is a function that remembers and can access variables from its
    // outer (enclosing) scope even after that outer function has finished executing.

console.log("=== Basic Closure Example ===");

function outerFunction() {
    const outerVar = "I'm from outer scope!";
    
    // innerFunction is a CLOSURE - it "closes over" outerVar
    function innerFunction() {
        console.log(outerVar); // Remembers outerVar even after outerFunction finishes
    }
    
    return innerFunction;
}

const myClosure = outerFunction();
myClosure(); // "I'm from outer scope!" - Still remembers outerVar!

// ====================================
// 2. YOUR FRIEND'S EXAMPLE - EXPLAINED
// ====================================

console.log("\n=== Your Friend's Example - Fixed ===");

let globalFunc = () => console.log("Default function");

const spam = Math.random(); // Global scope variable
console.log("Random spam value:", spam);

if (spam < 0.5) { // Changed condition for more likely execution
    const egg = 43; // Block-scoped variable (only exists in this if block)
    
    const innerFunc = () => {
        console.log("Closure accessing egg:", egg); // ← THIS IS THE CLOSURE!
        // innerFunc "closes over" the egg variable
    };
    
    globalFunc = innerFunc; // Assign the closure to global variable
}

// These would cause errors (commented out to prevent crashes):
// console.log(egg);       // ERROR: egg doesn't exist here - block scope!
// innerFunc();           // ERROR: innerFunc doesn't exist here - block scope!

globalFunc(); // This WORKS! The closure remembers 'egg'

// =============================
// 3. CLOSURE WITH PRIVATE DATA
// =============================

console.log("\n=== Closure for Private Data ===");

function createCounter() {
    let count = 0; // "Private" variable - only accessible through closures
    
    return {
        increment: () => ++count,
        decrement: () => --count,
        getCount: () => count
    };
}

const counter = createCounter();
console.log("Initial count:", counter.getCount()); // 0
console.log("After increment:", counter.increment()); // 1
console.log("After another increment:", counter.increment()); // 2
console.log("After decrement:", counter.decrement()); // 1

// count variable is NOT directly accessible - it's "private"
// console.log(count); // This would be an ERROR

// ================================
// 4. CLOSURES IN LOOPS - COMMON GOTCHA!
// ================================

console.log("\n=== Closures in Loops ===");

console.log("Problem (without closure fix):");
for (var i = 0; i < 3; i++) {
    setTimeout(() => {
        console.log("var i value:", i); // Always 3 - doesn't work as expected!
    }, 100);
}

console.log("Solution (with closure):");
for (let i = 0; i < 3; i++) {
    // Each iteration creates a new scope, "closing over" the current i value
    setTimeout(() => {
        console.log("let i value:", i); // 0, 1, 2 - works correctly!
    }, 100);
}

// Alternative solution with function scope:
console.log("Alternative solution (IIFE):");
for (var i = 0; i < 3; i++) {
    ((index) => {
        setTimeout(() => {
            console.log("IIFE i value:", index); // 0, 1, 2
        }, 100);
    })(i); // Immediately Invoked Function Expression
}

// ================================
// 5. PRACTICAL USE: EVENT HANDLERS
// ================================

console.log("\n=== Practical Use: Event Handlers ===");

function setupButtons() {
    const buttons = [
        { id: "btn1", color: "red" },
        { id: "btn2", color: "blue" },
        { id: "btn3", color: "green" }
    ];
    
    buttons.forEach(button => {
        // Each click handler is a closure that remembers its specific button
        const handler = () => {
            console.log(`Button ${button.id} clicked! Color: ${button.color}`);
        };
        // In a real browser: document.getElementById(button.id).addEventListener('click', handler);
        console.log(`Setting up handler for ${button.id} that remembers color: ${button.color}`);
    });
}

setupButtons();

// ===========================================
// 6. SCOPE vs EXECUTION CONTEXT (FRAME)
// ===========================================

console.log("\n=== Scope vs Execution Context ===");

// SCOPE: Where variables are accessible (determined by code structure)
// EXECUTION CONTEXT: The environment when code is running (call stack)

function scopeVsContext() {
    const scopedVar = "I'm scoped here";
    
    return function closureDemo() {
        // This closure remembers scopedVar from its scope (where it was defined)
        console.log("Closure accessing:", scopedVar);
        
        // Execution context provides the 'this' value and arguments
        console.log("Execution context this:", this);
    };
}

const demoFunc = scopeVsContext();
demoFunc(); // Calls with current execution context, but remembers original scope

// ================================
// 7. ADVANCED: CLOSURE CHAINING
// ================================

console.log("\n=== Advanced: Closure Chaining ===");

function createMultiplier(factor:number) {
    // factor is "closed over" by the returned function
    return (number:number) => number * factor;
}

const double = createMultiplier(2);
const triple = createMultiplier(3);

console.log("Double 5:", double(5)); // 10
console.log("Triple 5:", triple(5)); // 15
console.log("Double 10:", double(10)); // 20

// Each returned function maintains its own closure with the factor value
// that was passed when createMultiplier was called

// ===========================================
// KEY TAKEAWAYS (Copy these notes!)
// ===========================================

/*
CLOSURE CHEAT SHEET:

1. **Definition**: A function that remembers variables from where it was created,
   even after that outer function has finished executing.

2. **How it works**: When a function is defined, it gets a "backpack" (closure)
   containing all the variables it needs from its outer scope.

3. **Scope vs Context**:
   - SCOPE: Where variables live (determined by code structure)
   - CONTEXT: The 'this' value and environment when function is called

4. **Common Uses**:
   - Data privacy/encapsulation
   - Event handlers that need specific data
   - Function factories (like createMultiplier)
   - Solving loop variable problems

5. **Key Behavior**: Closures remember VARIABLES, not VALUES. If a variable
   changes after closure creation, the closure sees the updated value.

Try modifying these examples to deepen your understanding!
*/  






// so if we call outer_func in the global scope, it creates a frame, which stores object values even after the outer_func is done executing. These objects are like the local variables and inner_functions. 