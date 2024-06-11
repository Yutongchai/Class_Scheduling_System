import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HomeTuitionSystem {
    private ArrayList<Student> students;
    private ArrayList<Tutor> tutors;

    public HomeTuitionSystem() {
        students = new ArrayList<>();
        tutors = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTutor(Tutor tutor) {
        tutors.add(tutor);
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student.getDetails());
        }
    }

    public void displayTutors() {
        for (Tutor tutor : tutors) {
            System.out.println(tutor.getDetails());
        }
    }

    public static void main(String[] args) {
        HomeTuitionSystem system = new HomeTuitionSystem();

        // Read student data from file
        try (BufferedReader reader = new BufferedReader(new FileReader("student.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 4) {
                    String name = parts[0];
                    String phoneNumber = parts[1];
                    String email = parts[2];
                    String grade = parts[3];
                    Student student = new Student(name, null, phoneNumber, grade);
                    system.addStudent(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading student file: " + e.getMessage());
        }

        // Read tutor data from file
        try (BufferedReader reader = new BufferedReader(new FileReader("tutor.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 5) {
                    String name = parts[0];
                    String address = parts[1];
                    String phoneNumber = parts[2];
                    String subject = parts[3];
                    Tutor tutor = new Tutor(name, address, phoneNumber, subject);
                    system.addTutor(tutor);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading tutor file: " + e.getMessage());
        }

        // Display students and tutors
        System.out.println("Students:");
        system.displayStudents();
        System.out.println("Tutors:");
        system.displayTutors();

        Login login = new Login();
        login.login();
    }
}
