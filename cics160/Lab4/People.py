from Person import Person

class People(Person):
    # def __init__(self):
        # self.list = []

    def __init__(self, name: str, email: str, age: int):
        super().__init__(name, email, age)

    
    def add(self, object):
        self.list.append(object)
