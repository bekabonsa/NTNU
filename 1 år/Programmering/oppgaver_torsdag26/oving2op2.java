import javax.swing.JOptionPane;
public class oving2op2 {
    public static void main(String[] args){
        String Aprice = JOptionPane.showInputDialog("price for A:");
        String Agrams = JOptionPane.showInputDialog("A in grams:");
        String Bprice = JOptionPane.showInputDialog("price for B:");
        String Bgrams = JOptionPane.showInputDialog("B in grams:");
		final float ApriceI = Float.parseFloat(Aprice);
        final float AgramsI = Float.parseFloat(Agrams);
        final float BpriceI = Float.parseFloat(Bprice);
        final float BgramsI = Float.parseFloat(Bgrams);
        final float ratioA = ApriceI/AgramsI;
        final float ratiob = BpriceI/BgramsI;

        if(ratioA > ratiob) {
            JOptionPane.showMessageDialog(null, "prisen for B er lavere enn prisen for A. Ratio A= " +ratioA+ "ratio b = "+ratiob);
        }
        else if(ratioA < ratiob){
            JOptionPane.showMessageDialog(null, "prisen for A er lavere enn prisen for B. Ratio A= " +ratioA+ "ratio b = "+ratiob);
        }
        else{
            JOptionPane.showMessageDialog(null, "Begge er like verdt. Ratio A= " +ratioA+ "ratio b = "+ratiob);
        }
    }
    
}
