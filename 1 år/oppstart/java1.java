
import javax.swing.JOptionPane;
class java1 {
	public static void main(String[] args) {
		String hours = JOptionPane.showInputDialog("type in hours:");
		String minutes = JOptionPane.showInputDialog("type in Minutes:");
		String secondss = JOptionPane.showInputDialog("type in seconds:");
		//String breddeR = JOptionPane.showInputDialog("s:");
		float hour = Float.parseFloat(hours);
		float minute = Float.parseFloat(minutes);
		float seconds = Float.parseFloat(secondss);
		//float bredde = Float.parseFloat(breddeR);
		
		JOptionPane.showMessageDialog(null, "Det tilsvarer " +(hour*3600f+minute*60f+seconds)+"s");

	}
}

