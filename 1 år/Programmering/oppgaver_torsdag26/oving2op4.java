import javax.swing.JOptionPane;
public class oving2op4 {
    public static void main(String[] args){
        String antall = JOptionPane.showInputDialog("hvor mange tall har du lyst å sjekke?:");
        int total = Integer.parseInt(antall);
        int numbers[] = new int[total];
        
        //String til = JOptionPane.showInputDialog("til:");
        if(total < 1){
            JOptionPane.showMessageDialog(null, total +"er mindre enn 1. bruk common sense");
            return;
           }
            for (int x = 0; x < total; x++){
                
                int Tall = Integer.parseInt(JOptionPane.showInputDialog("tall"+x+";"));
                numbers[x] = Tall;
                
            }
            for (int x = 0; x < total; x++){
            boolean flag = false;
            if(numbers[x] == 1){
                flag = true;
                
            }
            for(int i=2; i<numbers[x]/2;i++){
               
                if((numbers[x]>2)&&(numbers[x]%i==0)){
                    flag = true;
                }
            }
            if(!flag){
                System.out.println("det er primtall " + numbers[x]);
            }else{
                System.out.println("det er ikke primtall " + numbers[x]);

            }
            }

            
                
            
            //System.out.println(numbers[x] + " is not a prime number.");
        
		
       
    }
    


}
