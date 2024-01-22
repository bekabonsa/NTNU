import javax.swing.*;

public class Valuta{
   double in_USD ; //rate
   String name; 
   //

public Valuta(String name){
 this.name = name ;
}

public void Nok(){
 in_USD = .11;
}

public void Euro(){
    in_USD = 1.18;
   }

public void Yen(){
    in_USD = 0.0091;
   }
   
   
}


//dnk to yen = dnk to usd then usd to yen


/*double Nok;
double Usd = .11; 
double Euro = 0.097;
double Yen = 12.64; */