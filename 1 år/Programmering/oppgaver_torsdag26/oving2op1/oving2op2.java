package oving2op1;

import javax.swing.JOptionPane;
public class oving2op2 {
    public static void main(String[] args){
        String Aprice = JOptionPane.showInputDialog("price for A:");
        String Agrams = JOptionPane.showInputDialog("A in grams:");
        String Bprice = JOptionPane.showInputDialog("price for A:");
        String Bgrams = JOptionPane.showInputDialog("A in grams:");
		final float ApriceI = Float.parseFloat(Aprice);
        final float AgramsI = Float.parseFloat(Agrams);
        final float BpriceI = Float.parseFloat(Bprice);
        final float BgramsI = Float.parseFloat(Bgrams);
        final float ratioA = ApriceI/AgramsI;
        final float ratioB = BpriceI/BgramsI;

        if(ratioA > ratioB) {
            JOptionPane.showMessageDialog(null, "prisen for B er lavere enn prisen for A. Ratio A= " +ratioA+ "ratio b = "+ratioB);
        }
        else if(ratioA < ratioB){
            JOptionPane.showMessageDialog(null, "prisen for A er lavere enn prisen for B. Ratio A= " +ratioA+ "ratio b = "+ratioB);
        }
        else{
            JOptionPane.showMessageDialog(null, "Begge er like verdt. Ratio A= " +ratioA+ "ratio b = "+ratioB);
        }



    }


}
