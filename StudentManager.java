import java.util.*;
class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String msg) {
        super(msg);
    }
}
class Student {
    private Integer rollNo;      
    private String name;
    private String email;
    private String course;
    private Double marks;         
    private char grade;

    public Student(Integer rollNo, String name, String email, String course, Double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    private void calculateGrade() {
        if (marks >= 90) grade = 'A';
        else if (marks >= 80) grade = 'B';
        else if (marks >= 70) grade = 'C';
        else grade = 'D';
    }

    public void display() {
        System.out.println("\n--- Student Details ---");
        System.out.println("Roll No : " + rollNo);
        System.out.println("Name    : " + name);
        System.out.println("Email   : " + email);
        System.out.println("Course  : " + course);
        System.out.println("Marks   : " + marks);
        System.out.println("Grade   : " + grade);
    }
}

class Loader implements Runnable {
    public void run() {
        System.out.print("Loading");
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(400);
                System.out.print(".");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
    }
}
public class StudentManager{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Roll No (Integer): ");
            Integer roll = Integer.valueOf(sc.nextInt());  

            sc.nextLine(); 

            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            if (name.trim().isEmpty()) throw new Exception("Name cannot be empty!");

            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Course: ");
            String course = sc.nextLine();
            if (course.trim().isEmpty()) throw new Exception("Course cannot be empty!");
            System.out.print("Enter Marks (0-100): ");
            Double marks = Double.valueOf(sc.nextDouble()); 

            if (marks < 0 || marks > 100) 
                throw new Exception("Invalid Marks! Must be between 0-100");
            Thread t = new Thread(new Loader());
            t.start();
            t.join();

            Student s = new Student(roll, name, email, course, marks);
            s.display();
            System.out.println("\nProgram execution completed.");
        } 
        catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("\n Exception Occurred: " + e.getMessage());
        }
        finally {
            System.out.println("Finally Block Executed – Resources Closed.");
        }
        sc.close();
    }
}
