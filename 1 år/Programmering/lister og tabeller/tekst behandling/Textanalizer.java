import java.util.Arrays;
import java.lang.Math;
import java.util.Locale;

public class Textanalizer {

    private String txt;

    //constructor
    public Textanalizer(String txt) {
        this.txt = txt;
    }


    public String getText(){
        return txt;
    }


    /**
     * calculate how many words are in a text by using space as a separator for a word;
     * @return int; how many words that were found
     */
    public int wordCount() {

        String[] str = txt.split(" ");

        return str.length;
    }


    /**
     * calculates average letter count of a word.
     * @return average letter count from a text.
     */
    public int averageWordLetterCount() {
        String[] str = txt.split(" ");
        float sum = 0;
        for (int i = 0; i < str.length; i++) {

            sum += (float) str[i].length();

        }
        return Math.round(sum / (float) str.length);
    }


    /**
     * calculates the average words per given delimiter;
     * @return int average word per period;
     */

    public int averagePeriodicWordCount() {

        String[] str = txt.split(",|:|\\.|\\?|!");
        int periode = str.length;
        int antallOrd = 0;

        for (int i = 0; i < str.length; i++) {
            //after a period a sentence starts with a single "space" char therefore we get an extra word.
            // we must compensate by subtracting 1 after the first sentence
            if(i>=1){antallOrd += str[i].split(" ").length-1; continue;}

            antallOrd += str[i].split(" ").length;



        }

        float ant_per_Periode = (float)antallOrd/(float)periode;
        return Math.round(ant_per_Periode);
    }

    /**
     * replaces a word with a different word everywhere the word is present in a text
     * @return String; a modified version of the original text with words replaced
     */

    public String replace(String replaceMe, String replacer){

        String str = txt;
        String newStr = txt.replaceAll(replaceMe, replacer);

        return newStr;
    }

    /**
     * Make all characters in a text uppercase letters
     * @return String; modified version of the text with all characters turned to uppercase letters
     */

    public String upperCase(){
        String str = txt;
        String newStr = txt.toUpperCase();

        return newStr;

    }

    @Override
    public String toString() {
        return "Textanalizer{" +
                "txt='" + txt + '\'' +
                '}';
    }
}
