package inharitance.abstraction.triangle;

abstract class Person{
    abstract void eat();
}

public class W3scuuuuulz {


        public static void main(String args[]){
            Person p = new Person(){
                void eat(){System.out.println("nice fruits");}
            };
            p.eat();
        }
}

