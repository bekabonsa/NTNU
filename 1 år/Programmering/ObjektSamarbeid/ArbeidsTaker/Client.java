import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        boolean exit = false;

        String firstname, lastname;
        int birthyear, employeeNr, yearHired;
        float monthlyPayment, taxPercent;
        Employee employee = null;
        Person person = null;

        Scanner input = new Scanner(System.in);

        //Personal details
        System.out.println("First name: ");
        firstname = input.nextLine();
        System.out.printf("Last name: ");
        lastname = input.nextLine();
        System.out.println("Birth year ");
        birthyear = input.nextInt();
        person = new Person(firstname, lastname, birthyear);


        //Employee info
        System.out.println("Employee nr: ");
        employeeNr = input.nextInt();
        System.out.println("Year hired: ");
        yearHired = input.nextInt();
        System.out.println("Monthly salary: ");
        monthlyPayment = input.nextFloat();
        System.out.println("Tax cut percentage: ");
        taxPercent = input.nextFloat();
        employee = new Employee(person,employeeNr, yearHired, monthlyPayment, taxPercent);
        employee.toString();

        while(!exit){

            System.out.println("Type 1 to fill in personal details and 2 to change employee details. 3 to exit. Random "
                    + "number for info");
            int choice = input.nextInt();

           switch (choice){

               case 1:
                   System.out.println("First name: ");
                   firstname = input.nextLine();
                   System.out.printf("Last name: ");
                   lastname = input.nextLine();
                   System.out.println("Birth year ");
                   birthyear = input.nextInt();
                   person = new Person(firstname, lastname, birthyear);
                   employee = new Employee(person);
                   System.out.println(employee);

               case 2:
                   System.out.println("Employee nr: ");
                   employeeNr = input.nextInt();
                   System.out.println("Year hired: ");
                   yearHired = input.nextInt();
                   System.out.println("Monthly salary: ");
                   monthlyPayment = input.nextFloat();
                   System.out.println("Tax cut percentage: ");
                   taxPercent = input.nextFloat();
                   employee = new Employee(person,employeeNr, yearHired, monthlyPayment, taxPercent);
                   System.out.println(employee);
                   continue;
               case 3:
                   exit = !exit;

           }
            employee.showDetails();
        }




    }



}
