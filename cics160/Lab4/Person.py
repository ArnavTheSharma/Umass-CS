class Person:
    def __init__(self, name:str, email:str, age:int):
        self.name = name
        self.email_address = email
        self.age = age
    
    def __eq__(object1, n):
        return (object1.age == n)
