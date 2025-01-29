import pygame

class Map():
    def __init__(self, canvas_size):
        self.canvas_size=canvas_size
        self.canvas      = pygame.display.set_mode(self.canvas_size)
    def update(self, vehicles):
        pygame.display.flip()
        for vehicle in vehicles:
            self.canvas.blit(vehicle.getImage(),vehicle.getPosition())
