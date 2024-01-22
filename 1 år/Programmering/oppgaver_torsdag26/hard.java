import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
public class hard{
    public static void main(String[] args){
        String tallet = JOptionPane.showInputDialog("Tall:");
		final int num = Integer.parseInt(tallet);
           // JOptionPane.showMessageDialog(null, start +">"+ slutt);   
               // 
       //} while (true);
       System.out.println(prime_factors(num));
    }

    public static List<Integer> prime_factors(int num){
       List<Integer> arr = new ArrayList<>();
       List<Integer> arr1 = new ArrayList<>();
       
        for (int i = 1; i < num/2; i++){

        if(i == 1) continue;    
        if (num%i==0){
                arr.add(i);
                
            }
        }
        for(int x: arr){
            boolean prime = true;
            for(int i = 2; i<x/2;i++){
                if(x%i==0){
                prime= !prime;
                continue;
                }
                else{
                    arr1.add(x);
                }
            } 
        }
        return arr1;
        
        } 

}




