import pygame
import Vehicle

class KeyboardMover():
    def __init__(self, vehicle, key):
        self.vehicle=vehicle
        self.key=key
    def processOneEvent(self):
        pressedKeys =  pygame.key.get_pressed()
        pressedKey = ord(self.key)
        if pressedKeys[pressedKey]:
            self.vehicle.setPosition(self.vehicle.getX() + 1, self.vehicle.getY())
        for event in pygame.event.get():
            if (event.type == pygame.QUIT):
                return False
            
        return True