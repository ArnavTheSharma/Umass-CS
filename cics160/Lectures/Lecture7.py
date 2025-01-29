# Binary Search is basically constantly adjusting the high and low limit and comparing the middle 
#   (which is the middle of high and low, aka average of them) with the value we are searching for
#   This is O(log2(n))

def b_search(lis,target):
    '''Assumes list is sorted'''
    low = 0
    high = len(lis)-1
    while high>=low:
        mid = (high+low)//2
        if lis[mid] == target:
            return mid
        elif lis[mid]>target:
            high = mid-1
        else:
            low = mid+1
    return -1

# linear search is more efficient if unsorted list
# you can run a for loop through the list, or you can use a while loop: 

def search(lis, target):
    index = -1
    found = False
    i = 0
    while i<len(lis) and not found:
        if(lis[i] == target):
            found = True
            index = i
        i = i+1
    return index

# Sorting a list:

# Selection Sort: 
# Find the smallest element, and swap it into the first position of the list
# Find the next smallest element and swap it into the second position. 
# Continue this process until we are out of elements
#   (Make a seperate function to find the min of a list)
#   This is O(n^2) because there are 2 loops (1 is nested)

# Bubble Sort: 
# We could compare each neighbor value and swap the 2 if the smaller number is after, but this is also O(n^2)

# 