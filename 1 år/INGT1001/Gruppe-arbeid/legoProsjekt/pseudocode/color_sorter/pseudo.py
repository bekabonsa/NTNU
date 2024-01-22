color_values = {"red":(16,4,8),"white":(44,47,86),"blue":(4,8,24),"conveyer":(4,4,6),"Conveyer":(11, 10, 36)}

def dist(coord1, coord2):
    return abs(((coord2[0]-coord1[0])**2 + (coord2[1]-coord1[1])**2 + (coord2[2]-coord1[2])**2)**(1/2))

def ReadColor(coordinate):
    return_color = "error"
    distance = 100
    for key in color_values:
        new_distance = dist(coordinate,color_values[key])
        if new_distance < distance:
            return_color = key
            distance = new_distance 
    return return_color





            color_to_box_position = {"red":1,"white":2,"blue":3}

            previous_box_position = 2


            box_shift = color_to_box_position[chip]-previous_box_position
            if box_shift < 0:
                symbol = -1
            else:
                symbol = 1
            receptor.run_target((symbol)*600, receptor.angle()+160*box_shift)
            previous_box_position = color_to_box_position[chip]