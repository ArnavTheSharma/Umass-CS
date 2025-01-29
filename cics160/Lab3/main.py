# import pygame
import Vehicle
import KeyboardMover
import Map
car1 = "./Images/orange_truck.png"
car2 = "./Images/red_car.png"
tree = "./Images/tree.png"
def main():
    # pygame.init()
    # canvas      = pygame.display.set_mode((1240, 820))
    car1        = Vehicle.Vehicle("./Images/orange_truck.png")
    car2        = Vehicle.Vehicle("./Images/red_car.png")
    tree        = Vehicle.Vehicle("./Images/tree.png", locx=1000)
    car1        = Path(car1).stem
    car2        = Path(car2).stem
    tree        = Path(tree).stem
    listOfVehicles=[car1,tree,car2]
    kbReader = KeyboardMover.KeyboardMover(car1,'l')
    kbReader2 = KeyboardMover.KeyboardMover(car2,'d')
    myMap = Map.Map((1240,820))
    keepRunning = True
    while (keepRunning):
        # pygame.display.flip()
        if car1.isCollidingWith(tree):
            print("the orange car and the tree have collided!!!")
        if car2.isCollidingWith(tree):
            print("the red car and the tree have collided!!!")
        keepRunning = kbReader.processOneEvent()
        keepRunning = kbReader2.processOneEvent()  
        myMap.update(listOfVehicles)

if __name__ == "__main__":
    main()