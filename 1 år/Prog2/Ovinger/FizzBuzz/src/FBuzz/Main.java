package FBuzz;


public class Main {
    public static void main(String[] args) {
        int[] numbers = {3, 5, 15, 101};

        for(int number : numbers){
            String s = fizzbuzz(number);

            System.out.println(s);
        }
    }

    private static String fizzbuzz(int number) {
        String s = "";

        boolean fizz = number % 3 == 0;
        boolean buzz = number % 5 == 0;

        if(fizz && buzz){
            s = "FizzBuzz";
        }
        else if(fizz){
            s  = "Fizz";
        }
        else if(buzz){
            s = "Buzz";
        } return s;
    }
}



