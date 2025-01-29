import unittest
from Sprite_collection import Sprite_collection
from My_sprite import My_sprite
import os
os.environ['PYGAME_HIDE_SUPPORT_PROMPT'] = "hide"

obj1 = My_sprite("C:\Users\arnav\OneDrive\cics160\Lab3\Images\orange_truck.png", (1, 1))
obj2 = My_sprite("C:\Users\arnav\OneDrive\cics160\Lab3\Images\green_car.png", (2, 2))
obj3 = My_sprite("C:\Users\arnav\OneDrive\cics160\Lab3\Images\red_car.png", (3, 3))
obj4 = obj3
obj5 = My_sprite("C:\Users\arnav\OneDrive\cics160\Lab3\Images\red_car.png", (5, 5))

collection = Sprite_collection.Sprite_collection()
Sprite_collection.add(obj1)
Sprite_collection.add(obj2)
Sprite_collection.add(obj3)
Sprite_collection.add(obj4)
Sprite_collection.add(obj5)


class Test(unittest.TestCase): 
    def test_for_element_t_the_beginning_of_list(self): 
        self.assertEqual(obj1, collection.search(obj1))
        
    def test_of_element_at_the_end_of_list(self): 
        self.assertEqual(obj5, collection.search(obj5))

    def  test_for_element_not_at_the_beginning_or_end_of_list(self):
        self.assertEqual(True, collection.search(obj2))
    
    def test_for_multiple_matching_elements(self):
        self.assertEqual([obj3, obj4], collection.search(obj3))
