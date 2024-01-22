
import javax.swing.JOptionPane;
public class oppgave3 {
	public static void main(String[] args) {
		String Seconds = JOptionPane.showInputDialog("type in seconds:");
		//String Minutes = JOptionPane.showInputDialog("type in Minutes:");
		//String breddeR = JOptionPane.showInputDialog("s:");
		final int seconds = Integer.parseInt(Seconds);
		final int hours = seconds / 3600;
		final int minutes = (seconds % 3600)/60; 
		final int sekunder = (seconds % 3600) % 60;
       
        
		
	

		//float Minute = Float.parseFloat(Minutes);
		//float bredde = Float.parseFloat(breddeR);
		
		JOptionPane.showMessageDialog(null, hours+":"+minutes+":"+sekunder);
		
	}
	
}


