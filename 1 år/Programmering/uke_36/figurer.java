import javax.swing.JOptionPane;

public class figurer{
public static void main(String[] args){
    int antall = Integer.parseInt(JOptionPane.showInputDialog("antall ganger figuren skal repetere seg:"));

    for(int i=0; i<antall; i++){
        String s = "*";
        System.out.println(s.repeat(i)+"\n");   
    }

}
}