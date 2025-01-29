class Student:
    def __init__(self, n, a):
        self.__name = n
        self.address = a

        def get_name(self):
            return(self.name)
        
if __name__=="__main__":
    x = Student("Arnav", "Amherst")
    # don't access object attributes directly, but instead use methods

    # name mangling
    # WRONG: print(x.__Student__name)
    print(x._Student__name)

# abstract data types lets us not worry about how things work (like how the pygame library detects what keys are pressed or any data structure)
