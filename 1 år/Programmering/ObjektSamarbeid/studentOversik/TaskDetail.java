import java.util.Arrays;
/**
 * a class that oversees how many tasks each student has completed.
 */

public class TaskDetail {

    private Student[] students;
    private int studentCount;

    public TaskDetail(){

    }

    public TaskDetail(Student[] students){
        this.students = students;
        countStudents();
    }

    /**
     * count the amount of students that were initialized
     *
     */
    public void countStudents(){
        for(int i = 0; i < students.length; i++) studentCount++;
    }

    public int getStudentCount() {
        return studentCount;
    }

    /**
     * get amount of completed tasks from student
     * @param student student object
     * @return int tasks completed
     */
    public int studentCompletedTasks(Student student){
        return student.getTaskCount();
    }

    /**
     * register a new student
     * @param name
     * @param taskCount
     */

    public void registerStudent(String name, int taskCount){
        Student[] studentsNew = Arrays.copyOf(students, students.length+1);
        Student newStudent = new Student(name, taskCount);
        studentsNew[studentsNew.length-1] = newStudent;
        students = studentsNew;
        studentCount++;
    }

    /**
     * increase student tasks completed
     * @param student
     */
    public void incStudentCompletedTasks(Student student){
      student.incTaskCount(1);
    }

    public int findStudent(String name){
        int ind = 0;
        for(int i = 0; i<students.length; i++){
            if(students[i].getName().equals(name)){
                ind = i;
                break;
            }
        }
        return ind;
    }

    @Override
    public String toString() {
        return "TaskDetail{" +
                "students=" + Arrays.toString(students) +
                ", studentCount=" + studentCount +
                '}';
    }
}
