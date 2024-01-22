#!/usr/bin/env pybricks-micropython


from pybricks.hubs import EV3Brick
from pybricks.ev3devices import Motor, TouchSensor, ColorSensor
from pybricks.parameters import Port, Button, Color, ImageFile, SoundFile
from pybricks.tools import wait

# The colored objects are either red, green, blue, or yellow.
POSSIBLE_COLORS = [Color.RED, Color.BLUE, Color.WHITE]

# Initialize the EV3 brick.
ev3 = EV3Brick()

# Initialize the motors that drive the conveyor belt and eject the objects.
belt_motor = Motor(Port.C)
sort_motor = Motor(Port.D)


# Initialize the Touch Sensor. It is used to detect when the belt motor has
# moved the sorter module all the way to the left.


# Initialize the Color Sensor. It is used to detect the color of the objects.
color_sensor = ColorSensor(Port.S1)


# This is the main loop. It waits for you to scan and insert 8 colored objects.
# Then it sorts them by color. Then the process starts over and you can scan
# and insert the next set of colored objects.
while True:
 
    belt_motor.run(500)
    

    # When we scan the objects, we store all the color numbers in a list.
    # We start with an empty list. It will grow as we add colors to it.
    color_list = []
    antallchips = 100
    # This loop scans the colors of the objects. It repeats until 8 objects
    # are scanned and placed in the chute. This is done by repeating the loop
    # while the length of the list is still less than 8.
    while len(color_list) < antallchips:

        # Show how many colored objects we have already scanned.
        

        # Wait for a color to be scanned.
        while True:
            # Store the color measured by the Color Sensor.
            color = color_sensor.color()
            # If a color is detected,
            # break out of the loop.
            if color in POSSIBLE_COLORS:
                color_list.append(color)
                break

      

        # a color was scanned. So we add (append) it to the list.
        ev3.screen.print(color)
        color_list.append(color)

        # We don't want to register the same color once more if we're still
        # looking at the same object. So before we continue, we wait until the
        # sensor no longer sees the object.
        while color_sensor.color() in POSSIBLE_COLORS:
            pass


     #sorting colors, straight motion. 
    #red_______white______blue                  // not needed (total length = 10 cm.   middle to edge length = 5cm.)
    #white to blue
        
        
    red = -1
    white = 0
    blue = 1

    lastposition = 0
    speed = 500
    #rotation degree needed for rotating from white to blue
    rotationalDeg = 150

    if color == Color.RED:
        difference = red - lastposition
        motor.run_target(speed, (-difference*rotationalDegMax))
        lastposition = red

    if  color == Color.WHITE:
        difference = white - lastposition
        motor.run_target(speed, (-difference*rotationalDegMax))
        lastposition = white

    if  color == Color.BLUE:
        difference = blue - lastposition
        motor.run_target(speed, (-difference*rotationalDegMax))
        lastposition = blue
    