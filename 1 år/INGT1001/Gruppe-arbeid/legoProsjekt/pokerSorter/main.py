#!/usr/bin/env pybricks-micropython


from pybricks.hubs import EV3Brick
from pybricks.ev3devices import Motor, TouchSensor, ColorSensor,
from pybricks.parameters import Port, Button, Color, ImageFile, SoundFile
from pybricks.tools import wait

# The colored objects are either red, green, blue, or yellow.
POSSIBLE_COLORS = [Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.WHITE, Color.BLACK]

# Initialize the EV3 brick.
ev3 = EV3Brick()

# Initialize the motors that drive the conveyor belt and eject the objects.
belt_motor = Motor(Port.C)
sort_motor = Motor(Port.D)


# Initialize the Touch Sensor. It is used to detect when the belt motor has
# moved the sorter module all the way to the left.
touch_sensor = TouchSensor(Port.S1)

# Initialize the Color Sensor. It is used to detect the color of the objects.
color_sensor = ColorSensor(Port.S3)


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
        

        # Wait for the center button to be pressed or a color to be scanned.
        while True:
            # Store the color measured by the Color Sensor.
            color = color_sensor.color()
            # If the center button is pressed or a color is detected,
            # break out of the loop.
            if color in POSSIBLE_COLORS:
                break

      

        # a color was scanned. So we add (append) it to the list.
        ev3.speaker.beep(1000, 100)
        ev3.screen.print(color)
        color_list.append(color)


        #!/usr/bin/env pybricks-micropython


from pybricks.hubs import EV3Brick
from pybricks.ev3devices import Motor, ColorSensor
from pybricks.parameters import Port, Button, Color, ImageFile, SoundFile
from pybricks.tools import wait

# The colored objects are either red, green, blue, or yellow.
POSSIBLE_COLORS = [Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.WHITE, Color.BLACK]

# Initialize the EV3 brick.
ev3 = EV3Brick()

# Initialize the motors that drive the conveyor belt and eject the objects.
belt_motor = Motor(Port.C)
sort_motor = Motor(Port.D)


# Initialize the Color Sensor. It is used to detect the color of the objects.
color_sensor = ColorSensor(Port.S3)


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
        

        # Wait for the center button to be pressed or a color to be scanned.
        while True:
            # Store the color measured by the Color Sensor.
            color = color_sensor.color()
            # If the center button is pressed or a color is detected,
            # break out of the loop.
            if color in POSSIBLE_COLORS and :
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
        


            #putting chips in the correct box

        ##         Black-1  0  White1
        ##  Blue-2                  Red2        lastC red -> black, 2-(-1) = -3*36, if (lastC < color)-difference
        ##        


        black_Ang = -1
        white_Ang = 1
        red_Ang = 2
        blue_Ang = -2

        last_color = color_list[-1]
        last_color_Ang = 0

        if color == Color.BLACK:
            difference = black_Ang - last_color_Ang
            motor.run_target(500, -difference*36)
            last_color_Ang = black_Ang

        if color == Color.RED:
            difference = red_Ang - last_color_Ang
            motor.run_target(500, -difference*36)
            last_color_Ang = red_Ang

        if color == Color.WHITE:
            difference = white_Ang - last_color_Ang
            motor.run_target(500, -difference*36)
            last_color_Ang = white_Ang

        if color == Color.BLUE:
            difference = blue_Ang - last_color_Ang
            motor.run_target(500, -difference*36)
            last_color_Ang = blue_Ang
                




            # We don't want to register the same color once more if we're still
            # looking at the same object. So before we continue, we wait until the
            # sensor no longer sees the object.
            while color_sensor.color() in POSSIBLE_COLORS:
                pass

    
 


