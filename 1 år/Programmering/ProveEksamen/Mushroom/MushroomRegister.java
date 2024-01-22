import java.util.ArrayList;

public class MushroomRegister {
    private int capacity = 2;
    private Shroom[] shrooms = new Shroom[capacity];
    private int shroomCount = 0;

    public void registerShroom(Shroom shroom){

        if(shrooms.length == shroomCount){
            capacity+=2;
            Shroom[] newShrooms = new Shroom[capacity];

            for (int i = 0; i<shrooms.length;i++){
                newShrooms[i] = shrooms[i];
            }
            shrooms = newShrooms;
            System.out.println("Capacity has been increased: "+shrooms.length);
        }

        shrooms[shroomCount] = shroom;
        shroomCount++;
    }

    public Shroom[] getShrooms() {
        return shrooms;
    }
}
