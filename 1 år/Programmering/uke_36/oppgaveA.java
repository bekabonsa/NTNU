public class oppgaveA {
    public static void main(String[] args){
        double sum = 17600;
        double avdrag = 550;
        double rest = sum-avdrag;
        double rente = 0.075;
        double totaltBetalt = 0;
        int months = 0;
    while(rest>0){
    System.out.println(months+" måned "+Belop(rente, rest, totaltBetalt, avdrag)+" \n");  
    rest-=Belop(rente, rest, totaltBetalt, avdrag);
    rest*=1+rente;
    totaltBetalt=totaltBetalt+Belop(rente, rest, totaltBetalt, avdrag);
    months++;
    }
    System.out.println("det tar "+months+" måneder og du betaler totalt "+totaltBetalt+"kr");  

    }

    static double Belop(double rente, double rest, double totaltBetalt, double avdrag){
        double monthlybelop = rente*rest+avdrag;
        
        return monthlybelop;
    }

}

