import javax.swing.JOptionPane;
public class oving2op1{
	public static void main(String[] args) {
		String Years = JOptionPane.showInputDialog("type in the year:");
		
		final int year = Integer.parseInt(Years);
        if((year%100==0 && year%400==0)|| (year%4==0 && year%100!=0)){
            JOptionPane.showMessageDialog(null, "The year "+year+ " is a leap year" );
        }
        else{
            JOptionPane.showMessageDialog(null, "The year "+year+ " is NOT a leap year" );
        }
    
	}
	
}