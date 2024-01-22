import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spiller {
    private String name;
    private int poengSum;
    private int terningsOyer = 6; 
    private java.util.Random terning= new java.util.Random();
    private ArrayList<Integer> kaster = new ArrayList<Integer>();

    Spiller(String name){
        this.name = name;
    }

    public void kastTerningen(){
        kaster.add(terning.nextInt(terningsOyer)+1);

        System.out.println(kaster.get(kaster.size()-1)+""+"\n");
    
        poengSum+=kaster.get(kaster.size()-1);

        if(kaster.get(kaster.size()-1) == 1){
            System.out.println(this.poengSum);
            this.poengSum = 0;
            
        }
    }

    public int getSumPoeng(){
        return poengSum;
    }

    public void erFerdig(){
        System.out.println(this.name + "has finished with "+poengSum+" points. It only took "+kaster.size()+" tries");

    }
}
