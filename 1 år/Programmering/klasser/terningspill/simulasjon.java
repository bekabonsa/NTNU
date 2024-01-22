import java.util.Random;


public class simulasjon{

    public static void main(String[] args){
       Spiller Player1 = new Spiller("player-1");
       Spiller Player2 = new Spiller("player-2");

       while(Player1.getSumPoeng() < 100 && Player2.getSumPoeng() < 100){
        Player1.kastTerningen();
        Player2.kastTerningen();
       }
       if (Player1.getSumPoeng() >= 100){
        Player1.erFerdig();
       }
       else{
        Player2.erFerdig();
       }


    }
}