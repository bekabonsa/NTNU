
abstract class Person{
    abstract void eat();

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

public class W3scuulz {


    public static void main(String args[]){
        Person p = new Person(){
            void eat(){System.out.println("nice fruits");}
        };
        p.eat();
    }
}

