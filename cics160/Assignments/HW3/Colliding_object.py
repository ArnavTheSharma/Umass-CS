from My_sprite import My_sprite

# for the autograder
import os
os.environ['PYGAME_HIDE_SUPPORT_PROMPT'] = "hide"

import pygame

class Colliding_object(My_sprite):
    def __init__(self, image_file_name:str="", location:tuple[int, int]=(0,0)):
        super().__init__(image_file_name, location)
        self.location = location
        self.locx = location[0]
        self.locy = location[1]
        self.width = self.width
        self.height = self.height
        self.bounding_box = pygame.Rect(self.locx, self.locy, self.width, self.height)
        
    def get_bounding_box(self):
        return (self.bounding_box)
    
    def is_colliding_with(self, co):
        return (pygame.Rect.colliderect(self.bounding_box, co.bounding_box))