import java.util.Locale;
import java.util.Arrays;
public class Tekstanalyse {

    private int[] letterOccur;
    private static String[] alphabet = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
            "r","s","t","u","v","w","x","y","z","æ","ø","å"};
    private String input;


    public Tekstanalyse(String str){

    this.input = str;
    letterOccur = new int[30];

    str = str.toLowerCase();

    putInfo(str);


    }



    public void putInfo(String str){
        for(int i = 0; i<str.length(); i++){

            char charIndex = str.charAt(i);
            String letter = Character.toString((char)charIndex);
            int ind = Arrays.asList(alphabet).indexOf(letter);


        /*
            hvis charIndex == -1 er det ingen treff for søket dermed legger
             vi alt som ikke er bokstav til siste index, inkludert mellomrom.
        */

            if (ind == -1) {
                ind = 29;
            }
            letterOccur[ind]++;

        }
    }



    /**
     * to find how many letters that are different, in other words how many letters that aren't 0.
     * @return different occurences
     */
    public int findDifferentLettersI(){
        //bryr oss bare om bokstaver og ikke den siste indeks dermed length blir -1
        int differentOccurences = 0;

        for (int i = 0; i<letterOccur.length-1; i++){

            if(letterOccur[i] == 0) continue;
            differentOccurences++;

        }

        return differentOccurences;

    }

    public long letterCount(){
        long letterC = 0;

        for (int i = 0; i<letterOccur.length-1; i++){

            if(letterOccur[i] == 0) continue;
            letterC+=letterOccur[i];

        }

        return letterC;
    }

    public double notLetterPercent(){

        return (double)letterOccur[letterOccur.length-1]/(double)letterCount();

    }

    public int letterOccurence(String c){
        c = c.toLowerCase();
        int ind = Arrays.asList(alphabet).indexOf(c);
        return letterOccur[ind];

    }

    public String mostRepeatingLetter(){

        int max = 0;
        String others = "";
        int ind = 0;

        for(int i = 0; i<letterOccur.length-1; i++){

            if(letterOccur[i]>max){
                max = letterOccur[i];
                others = alphabet[i];
            }
           else if(letterOccur[i] == max){
                others = others + ", " + alphabet[i];
                ind++;
            }
        }



        //show which letter max value is, 1) get index1 for max, 2) alphabet[index1] is the letter we are looking for

        String mostRL;
        return "most repeating letters is/are "+others;
    }

    @Override
    public String toString() {
        return "Tekstanalyse{" +
                "letterOccur=" + Arrays.toString(letterOccur) +'}'+
                "\n Det er "+findDifferentLettersI()+" forskjellige bokstaver"
                +"\n Det er "+findDifferentLettersI()+" forskjellige bokstaver"+
                "\n Det er "+letterCount()+" bokstaver totalt"+
                "\n Det er "+notLetterPercent()+"% som ikke er bokstav"+
                "\n"+mostRepeatingLetter();
    }
}
