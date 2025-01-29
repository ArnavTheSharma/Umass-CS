# OOP = encapsulation, information hiding, polymorphism, abstraction 
# method = function that only exists inside a class for an object (in Java it's method, not function)

import pygame

class Vehicle(pygame.sprite.Sprite):
    def __init__(self, image, color=(0,0,0), width=100, height=75, locx=0, locy=0):
        self.image = pygame.image.load(image)
        self.width = width
        self.height = height
        self.x = locx
        self.y = locy

    def getImage(self):
        return(self.image)
    
    def getPosition(self):
        return(self.x, self.y)
    
    def setPosition(self, x, y):
        self.x = x
        self.y = y

# SOLID (Guidelines, not rules)
#  S: Single Responsibility Principle (Each method handles only 1 thing, goal of OOP is to break up code)
#    A change in specifications of 1 method (like calculating GPA) shouldn't change another method 
#    or object or module (like a student's grades) 

# Let's jump to D instead of continuing to O
#  D: Dependency inversion principle (high level modules shouldn't depend on low level modules. 
#     Both should depend on abstractions/interfaces) 
   
#   For a system that registers students for courses, the system doesn't care how the data was entered for example
#       Low-level modules: implementation details, such as interacting with databases, file systems, and external services
#       Abstraction: hide complex implementation details and show only the necessary features of an object

