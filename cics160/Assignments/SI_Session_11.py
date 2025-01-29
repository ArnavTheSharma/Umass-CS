# Given a sorted list of integers and a target value, write a function that
# returns True if the value exists or False if it doesn’t. The function should bigO of
# log(n).

# lst =  [1,2,3,4,5,6,7,8,9]
# import math

# def find_num(list, num):
#     len = len(list) // 2 
#     for i in range(0, int(math.log2(len(list)))+1):
#         if num < list[len]:
#             len = int(len*0.5)
#         elif num > list[len]:
#             len = int(len(list) - len*0.5)
#         else:
#             return True
#     return False

# print(find_num(lst, 6))        

lst = [1,2,3,4,5,6,7,8,9]

def find_num(lst, n):
    high = len(lst)
    low = 0
    
    while high >= low:
        mid = (high + low) // 2

        if n > lst[mid]:
            low = mid + 1
        
        elif n < lst[mid]:
            high = mid - 1

        else:
            return True
    return False

lst = [1, 5, 6, 8, 0, 2, 4, 3]
def appears_twice(list):
    for i in range(0, len(list)):
        for j in range(i+1, len(list)):
            if list[i] == list[j]:
                return True
    return False

print(appears_twice(lst))