import pygame
import Vehicle


def main():
    pygame.init()
    canvas = pygame.display.set_mode((1240, 820))
    car1 = Vehicle.Vehicle("./Images/orange_truck.png")
    tree = Vehicle.Vehicle("./Images/tree.png")
    tree_size = tree.getImage().get_size()
    tree.setPosition(canvas.get_size()[0] - tree_size[0], 0)
    keepRunning = True
    while (keepRunning):
        canvas.blit(car1.getImage(), car1.getPosition())
        canvas.blit(tree.getImage(), tree.getPosition())
        pygame.display.flip()
        pressedKeys = pygame.key.get_pressed()
        if pressedKeys[pygame.K_d]:
            car1.setPosition(car1.getX() + 1, car1.getY())
            if (car1.isCollidingWith(tree)):
                print("The car and the tree have collided!!!")
        for event in pygame.event.get():
            if (event.type == pygame.QUIT):
                keepRunning = False


if __name__ == "__main__":
    main()
