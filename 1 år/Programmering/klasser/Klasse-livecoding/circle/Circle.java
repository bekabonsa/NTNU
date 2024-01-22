package circle;
import java.lang.Math;
import java.util.Scanner;
public class Circle {
    String circleID;
    double area;
    double radius;
    public Circle(double radius, String circleID){
        this.radius = radius;
        this.circleID = circleID;
    }


    public double getArea(){
        this.area = Math.PI * Math.pow(radius, 2);
        return Math.PI * Math.pow(radius, 2);
    }
    public void ToString(){
        System.out.println("Arealet til sirkel "+circleID+" er:"+getArea());
    }


    public class Circles{
        public static void main(String[] args){

            Scanner input = new Scanner(System.in);
            System.out.println("sett in raddius:");
            double in = input.nextDouble();
            Circle sirkel = new Circle(in, "circle 1");

            
            boolean exit = false;
            

            
           
                

        }
    }

}
