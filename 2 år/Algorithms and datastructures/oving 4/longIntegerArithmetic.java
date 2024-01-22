import java.util.ArrayList;
import java.util.Scanner;

public class longIntegerArithmetic {

    public String[] longNumbers;
    public static String operator;
    public static String input1 = "";
    public static String input2 = "";
    public static Scanner sc = new Scanner(System.in);
    public String input;
    public static String sign;
    public static boolean neg = false;


    public static DoublyLinkedList dLinkedList1 = new DoublyLinkedList();
    public static DoublyLinkedList dLinkedList2 = new DoublyLinkedList();
    public static StringBuilder answer = new StringBuilder("");
    public static DoublyLinkedList answer_dLinkedList = new DoublyLinkedList();



    public static void main(String[] args) {
        System.out.println("This program works by consecutive operations, not every input at once");
        System.out.println("You first type in a number and click enter before specifying an operator and so on.");
        System.out.println("works for NEGATIVE operations too.");
        System.out.println("Input long number only (no operators +/-), 0 to reset input 1:");
        input1 = sc.nextLine();
        while(!input1.matches("[0-9]+")){
            System.out.println("Only numbers allowed, resetting program!");
            input1 = sc.nextLine();
        }

        while(true){

            if (input1.equals("0")) input1 = "0";
            else if (!input1.matches("[0-9]+")) {
                System.out.println("ALERT! Only numbers allowed, resetting program!");
                continue;
            }
            System.out.println("Input operator +/-");
            operator = sc.nextLine();
            if(!operator.equals("+") && !operator.contains("-")){
                System.out.println("ALERT! Illegal operator, resetting program!");
                continue;
            }
            System.out.println("Input long number only (no operators +/-):");
            input2 = sc.nextLine();
            if (!input2.matches("[0-9]+")) {
                System.out.println("ALERT! Only numbers allowed, resetting program!");
                continue;
            }
            calculateExpression();
            input1 = answer.toString();
            answer_dLinkedList.deleteAllNodes();
            dLinkedList1.deleteAllNodes();
            dLinkedList2.deleteAllNodes();
            answer.setLength(0);
        }
    }

    public static void calculateExpression(){
        addDigitsToNode(traverseAndStoreDigits(input1), 1);
        addDigitsToNode(traverseAndStoreDigits(input2), 2);
        DoublyLinkedList.Node current_element1 = dLinkedList1.head;
        DoublyLinkedList.Node current_element2 = dLinkedList2.head;


        if (operator.trim().equals("+")){
            sumNumbers();
        }

        if(operator.trim().equals("-")){
            subtractNumbers();
        }

        DoublyLinkedList.Node current_ans_e = answer_dLinkedList.tail;
        while(current_ans_e != null){
            answer.append(current_ans_e.number);
            current_ans_e = current_ans_e.previous;
        }
        while(answer.toString().startsWith("0")){
            if(answer.toString().equals("0")) break;
            answer.deleteCharAt(0);
        }
        if(neg){
            sign = "-";
        }
        else{
            sign = "";
        }
        System.out.println("Result = "+ sign+ answer.toString());
        System.out.println("Is input 1 greater than input 2? " + isGreaterThan(dLinkedList1, dLinkedList2));
    }

    public static void subtractNumbers(){
        DoublyLinkedList.Node current_e1;
        DoublyLinkedList.Node current_e2;

        if(neg && operator.equals("-")){
            sumNumbers();
            return;
        }

        if(isGreaterThan(dLinkedList2, dLinkedList1) == 1 && neg == false){
            neg = true;
            DoublyLinkedList dl1 = dLinkedList1;
            dLinkedList1 = dLinkedList2;
            dLinkedList2 = dl1;

            current_e1 = dLinkedList2.tail;
            System.out.println("first check e1: " + current_e1.number);
            current_e2 = dLinkedList1.tail;
           // System.out.println(dLinkedList1.tail.number);
           // System.out.println(dLinkedList2.tail.number);
        }
        else{
            current_e1 = dLinkedList1.tail;
            current_e2 = dLinkedList2.tail;
            neg = false;
        }

        int rest = 0;



        while(current_e1 != null || current_e2 != null) {

            if (current_e1 != null && current_e2 != null) {

                if(neg){
                     int value = current_e1.number;
                     int value1 = current_e2.number;
                     current_e2.number = value;
                     current_e1.number = value1;
                }



                if(current_e1.number + rest < current_e2.number){
                    answer_dLinkedList.addNode(current_e1.number+rest+10-current_e2.number);
                    rest = -1;
                }
                else if(current_e1.number + rest >= current_e2.number){
                    answer_dLinkedList.addNode(current_e1.number+rest-current_e2.number);
                    rest = 0;
                }
                current_e1 = current_e1.previous;
                current_e2 = current_e2.previous;
            }

            else if(current_e1 != null && current_e2 == null){
                if(neg){
                    int value = current_e1.number;
                    int value1 = current_e2.number;
                    current_e2.number = value;
                    current_e1.number = value1;

                }

                if(current_e1.number<1){

                    if(rest == 0){
                        answer_dLinkedList.addNode(current_e1.number);
                    }
                    else{
                        answer_dLinkedList.addNode(current_e1.number + 10 + rest);
                        rest = -1;
                    }
                }
                else if (current_e1.number >= 1){
                    answer_dLinkedList.addNode(current_e1.number + rest);
                    rest = 0;
                }

                else if(current_e1 == null && current_e2 != null){

                    answer_dLinkedList.addNode(current_e2.number);
                    current_e2 = current_e2.previous;
                    while(current_e2 != null){
                        answer_dLinkedList.addNode(current_e2.number);
                    }
                    break;
                }
                current_e1 = current_e1.previous;
            }

            else if(current_e1 == null && current_e2 != null){
                if(neg){

                    while(current_e2 != null){
                        if (current_e2.number + rest < 0){
                            answer_dLinkedList.addNode(current_e2.number+rest+10);
                            rest = -1;
                            current_e2 = current_e2.previous;
                            continue;
                        }
                        answer_dLinkedList.addNode(current_e2.number+rest);
                        rest = 0;
                        current_e2 = current_e2.previous;
                    }
                }
            }
        }
    }

    public static int isGreaterThan(DoublyLinkedList a, DoublyLinkedList b){
        DoublyLinkedList.Node current_a = a.head;
        DoublyLinkedList.Node current_b = b.head;
        while (current_a != null){
            if(a.getTotElements() > b.getTotElements()){
                return 1;
            } else if (a.getTotElements()<b.getTotElements()) {
                return 0;
            }
            else if(a.getTotElements() == b.getTotElements()){
                if(current_a.number > current_b.number){
                    return 1;
                } else if (current_a.number < current_b.number) {
                    return 0;
                }

            }



            current_b = current_b.next;
            current_a = current_a.next;
        }

        return -1;
    }

    public static int sumNumbers(){
        DoublyLinkedList.Node current_e1 = dLinkedList1.tail;
        DoublyLinkedList.Node current_e2 = dLinkedList2.tail;
        DoublyLinkedList.Node current_ans_e = answer_dLinkedList.tail;

        if(neg){
           DoublyLinkedList dLink1 = dLinkedList1;
           DoublyLinkedList dLink2 = dLinkedList2;

           dLinkedList2 = dLink1;
           dLinkedList1 = dLink2;

           if(operator.equals("+")){
               if(isGreaterThan(dLinkedList2,dLinkedList1) == 1 && isGreaterThan(dLinkedList2,dLinkedList1) == 0){
                   neg = false;
               }
               subtractNumbers();
               return 1;
           }
        }


        int rest = 0;
        ArrayList<Integer> answer = new ArrayList<>();
        while(current_e1 != null || current_e2 != null) {

            if(current_e1 != null && current_e2 == null){
                answer_dLinkedList.addNode((current_e1.number + rest)%10);
                rest = (current_e1.number+rest)/10;
                current_e1 = current_e1.previous;
                continue;
            }
            if(current_e1 == null && current_e2 != null){
                answer_dLinkedList.addNode((current_e2.number + rest)%10);
                rest = (current_e2.number + rest)/10;
                current_e2 = current_e2.previous;
                continue;
            }
            else if(current_e1 != null && current_e2 != null){
                int digitSum = current_e1.number + current_e2.number + rest;
                if(digitSum > 9){
                    rest = 1;
                    answer_dLinkedList.addNode(digitSum%10);
                }else{
                    rest = 0;
                    answer_dLinkedList.addNode(digitSum);
                }
            }

            //---
            current_e1 = current_e1.previous;
            current_e2 = current_e2.previous;
        }

        if(current_e1 == null && current_e2 == null){
            if(rest > 0) answer_dLinkedList.addNode(1);
        }
        return 0;
    }

    

    public static void addDigitsToNode(int[] digits, int list_nr){
        if(list_nr == 1){
            for(int i = 0; i<digits.length; i++){
             dLinkedList1.addNode(digits[i]);
            }
        }
        else{
            for(int i = 0; i<digits.length; i++){
                dLinkedList2.addNode(digits[i]);
            }
        }
    }

    public static int[] traverseAndStoreDigits(String s){
        int[] singleDigits = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            singleDigits[i] = Character.getNumericValue(s.toCharArray()[i]);
        }
        return singleDigits;
    }

}
