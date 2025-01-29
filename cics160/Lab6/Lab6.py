#Let’s begin by writing a recursive function search(lst:list, e:int), which returns True if lst contains an element equal to e, and False otherwise.

def recursive_search(list, e):
   if len(list)==0:
       return False
   elif list[-1]==e:
       return True
   else:
       return recursive_search(list[:-1],e)
# The recursive step involves reducing the length of the list by 1 using the slice function by popping the last element per step. This way, the length of the list approaches the base case of the list size being 0. However, if the target is reached during that time (the last element of the list equals the target), the function returns true.

# Here’s the algorithm we will use. For the base case, we will return 0 for an empty list (how many times does e appear in an empty list? Zero times). As a second base case, for a list of size 1, we will return 1 if the element in that single position is equal to e, and zero otherwise.
#For the recursive step, what we will do is create two new lists, slicing the original list in two pieces of approximately the same length (they might be different by a little bit) by finding the middle point. Then we will call the count() function recursively, twice, on each slice of the original list, and combine the result after both calls finish.
def count(list, e):
   counter = 0
   if len(list)==0:
       return 0
   elif len(list)==1:
       if list[0]==e:
           return 1
       else:
           return 0
   else:
       midpoint = len(list)//2
       return counter+count(list[:midpoint],e)+count(list[midpoint:],e)

print(count([1,2,2,2], 2))
print(count([1,2,2,5], 5))
print(count([1,2,2,2], 8))

# The time complexity for the search function would be O(n) because that’s the worst case scenario, where we don’t find the element and search through the entire list. 
# The time complexity for the count function would be O(2^n) because the function calls itself twice per step for n steps. 


# failed attempt
# def search(lst, n=len(lst), e):
#     if n < 0:
#         return False
#     if lst[n] == e:
#         return True
#     else:
#         return(search(lst,n-1,e))
    
# print(search([1,2,3,4,5], 4, 3))