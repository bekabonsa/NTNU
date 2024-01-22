import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exit= false;
        TaskDetail studentOverview = new TaskDetail();
        while(!exit){
            int choice = input.nextInt();

            switch(choice){

                case 1:
                    System.out.println("Register Student"+"\n");
                    System.out.println("name: ");
                    String name = input.nextLine();
                    System.out.println("Tasks completed: ");
                    int taskCount = input.nextInt();
                    studentOverview.registerStudent(name, taskCount);

                case 2:
                    System.out.println("There are "+studentOverview.getStudentCount()+" students registered.");

                case 3:
                    String nameS = input.nextLine();
                    System.out.println(names+"");


            }
        }


    }

}
