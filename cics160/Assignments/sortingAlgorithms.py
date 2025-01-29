# repeatedly selecting the smallest element from the unsorted portion and swapping it with the first unsorted element
def selection_sort(list):
    # the i will ensure list iterates through unsorted part of list (from 0 to end, then 1 to end, then 2 to end, etc)
    for i in range(0, len(list)):
        min = list[i]
        index = i
        # finds min of the the current unsorted list iteration
        for j in range(i, len(list)):
            if list[j] < min:
                min = list[j]
                index = j
        # don't swap if min is already in position
        if index == i:
            continue

        # swaps min and i-th element
        else:
            list[index] += list[i]
            list[i] = list[index] - list[i]
            list[index] -= list[i]

    return(list)

# print(selection_sort([5, 8, 8, 4, 2, 1, 10]))



# repeatedly swapping the adjacent elements if they are in the wrong order
def bubble_sort(list):
    # same as above function, j will iterate through unsorted part of list, except this time it's in reverse (from end to 0, then end to 1, then end to 2, etc)
    # this checks if current element is less than previous element, and swaps if it is -- continues swapping until element goes to en

    # accidentally switched j and i
    for j in range(0, len(list)):
        for i in reversed(range(j, len(list))):
            if list[i] < list[i-1]:
                list[i-1] += list[i]
                list[i] = list[i-1] - list[i]
                list[i-1] -= list[i]
                # another way to swap: list[i], list[i-1] = list[i-1], list[i]
    return(list)
# print(bubble_sort([5,6,3,9,3,4]))



# insertion sort
# iterates through the list per element, which it inserts into the sorted part of list at the correct index
def insertion_sort(list):
    # iterates through entire list
    for i in range(0, len(list)):
        # iterates through sorted part
        for j in range(0, i):
            # finds correct index to insert the ith element, not jth element
            if list[i] <= list[j]:
                list.insert(j, list[i])
                # after inserting element, pops old element (index i+1 because we added a new element)
                list.pop(i+1)
    return(list)
# print(insertion_sort([5,4,2,6,7,2,1]))



# mergesort recursively divides list into 2 sub lists until it's 1 element then merges 2 sorted lists recursively over and over
def merge_sort(aList):
    # return list if len is 0 or 1
    if (len(aList) < 2):
        return(aList)
    else:
        # splits into 2 sub-lists, list a and b
        mid = len(aList)//2
        a = aList[0:mid]
        b = aList[mid: len(aList)]
        # runs the recursion on both sub lists, which keep creating their own sub lists until length = 1, then merges the smallest sublists back then those combined bigger lists and keeps going up
        aButSorted = merge_sort(a)
        bButSorted = merge_sort(b)
    
    # merges these sorted lists
    return(merge(aButSorted, bButSorted))

def merge(listA, listB):
    newList = []
    while(len(listA)>0 and len(listB)> 0):
        if(listA[0]<listB[0]):
            newList.append(listA[0])
            listA.pop(0)
        else: 
            newList.append(listB[0])
            listB.pop(0)
    # outside while loop, one of list lengths is 0, so it simply adds both to end of newList
    newList.extend(listA)
    newList.extend(listB)
    return(newList)

print(merge_sort([52,5,3,43,3]))


# def mergesort2(aList, aStart, aEnd, bStart, bEnd):
#     mid = len(aList)//2
#     aStart = 0
#     aEnd = mid-1
#     bStart = mid
#     bEnd = len(aList)-1
#     if (aEnd - aStart) == 0:
#         return aList
    
    # aSorted = mergesort2(aList, aStart, aEnd//2, bStart//2, mid)
    # bSorted = mergesort2(aList, bStart, bEnd, bStart, len(aList))