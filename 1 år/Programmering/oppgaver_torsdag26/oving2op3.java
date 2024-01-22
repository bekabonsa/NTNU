import javax.swing.JOptionPane;
public class oving2op3 {
    public static void main(String[] args){
        int start;
        int slutt;
        do{
		start = Integer.parseInt(JOptionPane.showInputDialog("heltall fra:"));
        slutt = Integer.parseInt(JOptionPane.showInputDialog("opptil:"));
        }while(start<=1 || slutt <= 1);
        int antall = slutt-start;
        int verdi = start;
       // do {
           if(start>slutt){
            JOptionPane.showMessageDialog(null, start +">"+ slutt);
            return;
           }
            for (int x = 0; x < antall+1; x++){
            for (int i = 1; i < 11; i++) {
                System.out.println(verdi+"*"+i+"="+verdi*i);
              }
            verdi++;
            }
       //} while (true);
    }
    


}



