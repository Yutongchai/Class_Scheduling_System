import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeTuitionSystem {
    private ArrayList<Student> students;
    private ArrayList<Tutor> tutors;
    private Schedule schedule;

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

    public void studentInterface(Student student, Scanner scanner) {
        int count = 0;
        while (true) {
            System.out.println("1. View Schedule");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Logout");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        schedule.displayLessonsForStudent(student);
                        break;
                    case 2:
                        System.out.print("Enter course details (subject): ");
                        String subject = scanner.nextLine();
                        Tutor tutor = tutors.get(count++ % tutors.size()); // Assigning first tutor for simplicity
                        double price = 50.0; // Assuming a fixed price
                        int lessonId = Lesson.getLessons().size() + 1;
                        Lesson.addLesson(lessonId, subject, tutor, student, price);
                        break;
                    case 3:
                        System.out.print("Enter course subject to delete: ");
                        String courseToDelete = scanner.nextLine();
                        schedule.removeLessonForStudent(student, courseToDelete);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                scanner.next();
            }
        }
    }
}

/*
 * import java.io.BufferedReader;
 * import java.io.FileReader;
 * import java.io.IOException;
 * import java.util.ArrayList;
 * import java.util.Scanner;
 * 
 * public class HomeTuitionSystem {
 * private ArrayList<Student> students;
 * private ArrayList<Tutor> tutors;
 * private Schedule schedule;
 * 
 * public HomeTuitionSystem() {
 * students = new ArrayList<>();
 * tutors = new ArrayList<>();
 * }
 * 
 * public void addStudent(Student student) {
 * students.add(student);
 * }
 * 
 * public void addTutor(Tutor tutor) {
 * tutors.add(tutor);
 * }
 * 
 * public void displayStudents() {
 * for (Student student : students) {
 * System.out.println(student.getDetails());
 * }
 * }
 * 
 * public void displayTutors() {
 * for (Tutor tutor : tutors) {
 * System.out.println(tutor.getDetails());
 * }
 * }
 * 
 * public void studentInterface(Student student) {
 * Scanner scanner = new Scanner(System.in);
 * while (true) {
 * System.out.println("1. View Schedule");
 * System.out.println("2. Add Course");
 * System.out.println("3. Delete Course");
 * System.out.println("4. Logout");
 * int choice = scanner.nextInt();
 * scanner.nextLine(); // Consume newline
 * 
 * switch (choice) {
 * case 1:
 * schedule.displayLessonsForStudent(student);
 * break;
 * case 2:
 * System.out.print("Enter course details (subject date time day): ");
 * String subject = scanner.next();
 * String date = scanner.next();
 * String time = scanner.next();
 * String day = scanner.next().toUpperCase();
 * Day dayEnum = Day.valueOf(day);
 * Tutor tutor = tutors.get(0); // Assigning first tutor for simplicity
 * double price = 50.0; // Assuming a fixed price
 * int lessonId = schedule.getLessons().size() + 1;
 * Lesson lesson = new Lesson(lessonId, date, time, subject, tutor, student,
 * price, dayEnum);
 * schedule.addLesson(lesson);
 * break;
 * case 3:
 * System.out.print("Enter course subject to delete: ");
 * String courseToDelete = scanner.next();
 * schedule.removeLessonForStudent(student, courseToDelete);
 * break;
 * case 4:
 * return;
 * default:
 * System.out.println("Invalid choice. Please try again.");
 * }
 * }
 * }
 * 
 * public void tutorInterface(Tutor tutor) {
 * Scanner scanner = new Scanner(System.in);
 * while (true) {
 * System.out.println("1. View Teaching Schedule");
 * System.out.println("2. View Student List");
 * System.out.println("3. Enter Grades");
 * System.out.println("4. Logout");
 * int choice = scanner.nextInt();
 * scanner.nextLine(); // Consume newline
 * 
 * switch (choice) {
 * case 1:
 * schedule.displayLessonsForTutor(tutor);
 * break;
 * case 2:
 * for (Student student : students) {
 * System.out.println(student.getDetails());
 * }
 * break;
 * case 3:
 * System.out.print("Enter student name: ");
 * String studentName = scanner.next();
 * System.out.print("Enter grade: ");
 * String grade = scanner.next();
 * for (Student student : students) {
 * if (student.getName().equals(studentName)) {
 * student.setGrade(grade);
 * System.out.println("Grade updated.");
 * }
 * }
 * break;
 * case 4:
 * return;
 * default:
 * System.out.println("Invalid choice. Please try again.");
 * }
 * }
 * }
 * 
 * public static void main(String[] args) {
 * HomeTuitionSystem system = new HomeTuitionSystem();
 * 
 * // Read student data from file
 * try (BufferedReader reader = new BufferedReader(new
 * FileReader("student.txt"))) {
 * String line;
 * while ((line = reader.readLine()) != null) {
 * String[] parts = line.split(" ");
 * if (parts.length >= 4) {
 * String name = parts[0];
 * String phoneNumber = parts[1];
 * String email = parts[2];
 * String grade = parts[3];
 * Student student = new Student("Student", name, null, phoneNumber, grade);
 * system.addStudent(student);
 * }
 * }
 * } catch (IOException e) {
 * System.err.println("Error reading student file: " + e.getMessage());
 * }
 * 
 * // Read tutor data from file
 * try (BufferedReader reader = new BufferedReader(new FileReader("tutor.txt")))
 * {
 * String line;
 * while ((line = reader.readLine()) != null) {
 * String[] parts = line.split(" ");
 * if (parts.length >= 4) {
 * String name = parts[0];
 * String address = parts[1];
 * String phoneNumber = parts[2];
 * String subject = parts[3];
 * Tutor tutor = new Tutor("Tutor", name, address, phoneNumber, subject);
 * system.addTutor(tutor);
 * }
 * }
 * } catch (IOException e) {
 * System.err.println("Error reading tutor file: " + e.getMessage());
 * }
 * 
 * // Display students and tutors
 * System.out.println("Students:");
 * system.displayStudents();
 * System.out.println("Tutors:");
 * system.displayTutors();
 * 
 * Login login = new Login();
 * Person person = login.login();
 * 
 * if (person != null) {
 * System.out.println("*---------------------------------------------------*");
 * System.out.println("|                HOME TUITION SYSTEM                |");
 * System.out.println("*---------------------------------------------------*");
 * if (person instanceof Student) {
 * system.studentInterface((Student) person);
 * } else if (person instanceof Tutor) {
 * system.tutorInterface((Tutor) person);
 * }
 * }
 * 
 * }
 * }
 */
