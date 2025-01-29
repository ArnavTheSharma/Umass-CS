# 2 objects, even if they have identical attributes, are not equal to each other because their reference addresses in memory are different
# now if we create an object1, and do object2 = object1, they in fact are equal because the pointer (ptr) of both are to the same address
# however if you change a property of object1, you also change the property of object2, since they have the same ptr

# Dunder methods are magic methods that are used to customize classes and objects that aren't called directly but have special funcitonality
#   __init__ is called automatically when an object is created
#   __add__ is called when + is used in a class
#   __eq__ is used when ==

# are strings objects? Yes, however if you compare 2 identical strings, even if their ptr's are different, they are equal 
#   This is because strings have a dunder method, __eq__, which compares characters, not addresses


# the python compiler, when it sees x != y, first checks to see if the ptr's are different 

class Person:
    def __init__(self, n, address):
        self.name = n
        self.address = address
        # the var to the right is the argument to the method, the left one is our attribute to the object 
    
    def __eq__(self, o):
        return(self.name == o.name)
        # we should create getName for this, because it also exposes some attributes unnecessarily (read Interface Segregation Principle)


# if we have another file, Person.py, where we define a Person class, to create an object we do p1 = Person.Person("etc", "etc", etc)
# you can also redefine dunder functions to do their own functions, such as for objects comparing the name attribute instead of ptr's

