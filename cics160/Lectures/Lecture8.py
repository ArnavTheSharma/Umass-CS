# Week 8 Recursion 

# direct recursion of A
def functionA():
    return (functionA())

# indirect recursion of B

def functionB():
    return functionC()

def functionC():
    return functionB()

# To create a multiplication recursion with only addition: 
def mult(a, b):
    if b == 1:
        return a
    else:
        return(mult(a, b-1) + a)
# e.g. a=7, b=4
# This would start with 7 + mult(7, 3), then 7 + mult(7, 2), then 7 + mult(7, 1), and because b=1, 
#   it returns 7, so now it goes back up like dominoes, since mult(7, 2) = 7 + mult(7, 1) = 7 + 7 = 14
#   and because mult(7, 2) = 14, we now know mult(7, 3) and so forth

# stack overflow: when recursion goes on too long and computer runs out of memory - python says max recursion, c says stack overflow
# time complexity: O()

# n factorial for n > 0 (0! = 1)
def fact(n):
    if n == 0:
        return 1
    return(fact(n-1) * n)
# fact(-5) would move away, not towards base case of 0, since iterations go from -5 to -6 to -7 ...

# fibb sequence - return nth element
def fibb(n):
    if n == 0 or n == 1:
        return 1
    else:
        return (fibb(n-1) + fibb(n-2))
    
print(fibb(5))

# mergesort

def mergesort(list):
    if len(list) < 2:
        return list
    else:
        midpoint = len(list)//2
        listA = list[0:midpoint]
        listB = list[midpoint:len(list)]
        listA = mergesort(listA)
        listB = mergesort(listB)

def merge(listA, listB):
    while (listA > 0 or listB > 0):
        listC = []
        if listA[0] > listB[0]:
            listC.append(listB[0])
            listB.pop(0)
        elif listA[0] < listB[0]:
            listC.append(listA[0])
            listB.pop(0)
        elif len(listA) == 0:
            listC.append(listB[0])