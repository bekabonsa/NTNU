import time
from pybricks.hubs import EV3Brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor,
                                 InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import Port, Stop, Direction, Button, Color
from pybricks.tools import wait, StopWatch, DataLog
from pybricks.robotics import DriveBase
from pybricks.media.ev3dev import SoundFile, ImageFile


RIGHT = False
STOP = False
ev3 = EV3Brick()
motor_left = Motor(Port.B)
motor_right = Motor(Port.C)
robot = DriveBase(motor_left, motor_right, 55.5, 100)
color_sensor = ColorSensor(Port.S3)
SPEED = 50  # mm/s

ev3.screen.print('Place me on a \nwhite surface!')
while not color_sensor.color() == Color.WHITE:
    time.sleep(0.1)

ev3.screen.clear()
ev3.screen.print('On white surface!\nCalibrating...')
WHITE = color_sensor.reflection()
ev3.screen.print('Press right button to \ncontinue!')

while not RIGHT:
    for item in ev3.buttons.pressed():
        if item == Button.RIGHT:
            RIGHT = True
            ev3.screen.clear()
            break
        else:
            time.sleep(0.1)

RIGHT = False

ev3.screen.print('Searching \nblack line!')
robot.drive(speed=SPEED, turn_rate=0)
while not color_sensor.color() == Color.BLACK:
    time.sleep(0.1)
robot.stop()
ev3.screen.clear()

ev3.screen.print('Black line found!\nCalibrating...')
time.sleep(1)
BLACK = color_sensor.reflection()
THREASHOLD = (BLACK + WHITE) / 2
ev3.screen.clear()

ev3.screen.print('Follow black line!\nPress any button\n to STOP!')
while not STOP:
    for item in ev3.buttons.pressed():
        if item:
            STOP = True
            ev3.screen.clear()
            ev3.screen.print('Stopping!')
            break
    deviation = color_sensor.reflection() - THREASHOLD
    TURNRATE = deviation * 1.2
    robot.drive(speed=SPEED, turn_rate=TURNRATE)
robot.stop()