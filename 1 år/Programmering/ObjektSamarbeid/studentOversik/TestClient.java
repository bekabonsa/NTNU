

public class TestClient {

    public static void main(String[] args) {

        Student[] student = new Student[2];
        boolean exit = false;
        student[0] = new Student("Beka", 9);
        student[1] = new Student("Bob", 0);

        //Creating task details for each student
        TaskDetail studentTasks = new TaskDetail(student);

        //check if increment task count works
        student[1].incTaskCount(1);

        //check how many students are registered
        System.out.println("There are "+studentTasks.getStudentCount()+" registered students");

        //check how many tasks a student has solved
        System.out.println(student[0].getName()+" has solved "+studentTasks.studentCompletedTasks(student[0])+" tasks");

        //increase task count for a student
        studentTasks.incStudentCompletedTasks(student[0]);
        System.out.println(student[0].getName()+" has finished one more task making it "+studentTasks.studentCompletedTasks(student[0]));

        //register a new student

        studentTasks.registerStudent("Newbie", 0);

        //show all students
        System.out.println(studentTasks);


    }


}
