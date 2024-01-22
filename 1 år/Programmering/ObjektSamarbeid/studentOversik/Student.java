/**
 * Student class with name and amount of tasks a student has completed
 */

public class Student {
    private String name;
    private int taskCount;

    public Student(String name, int taskCount){
        this.name = name;
        this.taskCount = taskCount;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public String getName() {
        return name;
    }

    public void incTaskCount(int inc){
        taskCount+= inc;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", taskCount=" + taskCount +
                '}';
    }
}
