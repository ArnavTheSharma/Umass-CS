# OOP Lecture
# OOP = method to easily decompose problems into pieces (lets us use: Data Encapsulation, 
#   Information hiding (from other functions), Inheritance)
# Attributes of a Student: Name, Major, SPIRE ID, Address 

class Employee:
    def __init__(self, name="", address="", dept=""):
        #__init__ is the constructor method
        self.name="none"
        self.address=""
        self.department=dept
        #you can also pass values that werent passed as parameters to the constructor 
        self.Salary=15.50
        
    #2 more methods
    def setName(self, name):
        self.name = name
    
    def getSalary(self):
        return(self.Salary)
    
    
    

if __name__=="__main__":
    #creating an object of type Employee (which is the Constructor)
    employee1 = Employee("Arnav", "19 Flintlock Dr", "Physics")
    employee1.setName("Arnav")
    print(employee1.getName())
    
    #notice how you call the object name, then the function within the class of that object
    #also note that putting self as a parameter doesn't mean you have to reference it 
    #   (setName has self.name but we only entered "Arnav" as a parameter)
    