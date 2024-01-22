py def check():
    if leftSensor.rgb()[0] < 30 and leftSensor.rgb()[1] < 30 and leftSensor.rgb()[2] < 30:
        leftBlack = True
    else:
        leftBlack = False
    
    if rightSensor.rgb()[0] < 30 and rightSensor.rgb()[1] < 30 and rightSensor.rgb()[2] < 30:
        rightBlack = True
    else:
        rightBlack = False
        

while True: 
    if power.pressed(): 
        power_state = True
        wait(300) 
        if leftBlack and rightBlack:
            robot.drive(250,0)
        if (not leftBlack) and rightBlack:
            robot.drive(50,20)
        if leftBlack and (not rightBlack):
            robot.drive(50,-20)
        if (not leftBlack) and (not rightBlack):
            robot.stop()

        ev3.screen.print("Left: " + str(leftSensor.color()))
        ev3.screen.print("Right: " + str(rightSensor.color()))

        if power.pressed():
            power_state = False 
            robot.stop()
            wait(500)

robot.stop()
ev3.speaker.say("Shutting down.")



