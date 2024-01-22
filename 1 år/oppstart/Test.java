
import javax.swing.*;
public class Test {
    public static void main(String[] args) {
        String secondsInput = JOptionPane.showInputDialog("Input seconds: ");
        int seconds = Integer.parseInt(secondsInput);
        final int HOURS = seconds / 3600;
        final int MINUTES = seconds/60%60;
        final int SECONDS = seconds % 60;
        JOptionPane.showMessageDialog(null, HOURS + " hours, " + MINUTES + " minutes, " + SECONDS + " seconds.");
    }
}
