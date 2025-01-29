# for the autograder
import os
os.environ['PYGAME_HIDE_SUPPORT_PROMPT'] = "hide"

import pygame
from My_sprite import My_sprite

class Sprite_collection:
    def __init__(self):
        self.Sprite_collection = []
        self.collection = []
    
    def add(self, My_sprite):
        self.Sprite_collection.append(My_sprite)

    def search(self, ms2):
        for i in self.Sprite_collection:
            if ms2 == i:
                self.collection.append(i)

        return self.collection