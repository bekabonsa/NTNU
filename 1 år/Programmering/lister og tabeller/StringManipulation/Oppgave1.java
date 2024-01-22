import java.sql.SQLOutput;

public class Oppgave1 {

    public static void main(String[] args) {

        NewString str = new NewString("mama jamma+");

        System.out.println(str.shorten());
        System.out.println(str.removeChar("+"));

    }

}
