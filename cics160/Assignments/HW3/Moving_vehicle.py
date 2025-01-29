# for the autograder
import os
os.environ['PYGAME_HIDE_SUPPORT_PROMPT'] = "hide"

import pygame

from Colliding_object import Colliding_object

class Moving_vehicle(Colliding_object):
    def __init__(self, image_file_name:str, location:tuple[int, int]=(0,0)):
        super().__init__(image_file_name, location)

    def set_location(self, loc:tuple[int,int]=(0,0)):
        self.locx = loc[0]
        self.locy = loc[1]
        self.bounding_box = pygame.Rect(self.locx, self.locy, self.width, self.height)
        self.location = loc 