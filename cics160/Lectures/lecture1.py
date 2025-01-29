# make var names very descriptive (IDE fills in rest of var anyway)
def maximum(a_list):
    maximum_value_seen_so_far = a_list[0]
    index_of_the_maximum_value_seen_so_far = 0
    while (index_of_the_maximum_value_seen_so_far < len(a_list)):
        print("iteration: ", index_of_the_maximum_value_seen_so_far)
        if (a_list[index_of_the_maximum_value_seen_so_far] < maximum_value_seen_so_far):
            maximum_value_seen_so_far = a_list[index_of_the_maximum_value_seen_so_far]
        index_of_the_maximum_value_seen_so_far += 1
    return(index_of_the_maximum_value_seen_so_far)

if (__name__=="__main__"):
    print(maximum([1, 2, 3, 4, 5]))


def isDivisibleBy(a, b):
    if (a%b == 0):
        return True
    else:
        return False
    
# when we do this
print(isDivisibleBy(10, 3))
# a copy of 10 gets passed to the function so if the function modifies the values inside the function, the originals are unaffected
# however, when we pass lists, we don't pass a copy of the list but the reference to the address of the list, so the originals are affected

# to return more than one value do return (b,a)


# if (__name__=="__main__"):
# lets define 2 files: module.py and main.py:

# module.py
def greet(name):
    print(f"Hello, {name}!")

if __name__ == '__main__':
    greet("Alice")

#main.py
import module

module.greet("Bob")

# every file has a module called __name__, and when you run a file, that file's __name__ module gets
#   called "__main__", and any imported modules (like module from module.py) will just be called module

# If we run module.py, output is Hello, Alice, but if we run main.py we get Hello, Bob, but it doesn't run
#   Hello Alice from module.py because _name_ != "_main_"



# ADT styles: plain english (class, these methods, input outputs, )
# another style:
# __init(name:str, age:int) --> bool
