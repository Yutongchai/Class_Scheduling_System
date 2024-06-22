import java.util.ArrayList;
import java.util.Scanner;

public class HomeTuitionSystem {
    private ArrayList<Student> students;
    private ArrayList<Tutor> tutors;
    private Schedule schedule;

    public HomeTuitionSystem() {
        students = new ArrayList<>();
        tutors = new ArrayList<>();
        schedule = new Schedule();
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
        boolean firstTime = true;
        while (true) {
            System.out.println("\n1. View Schedule");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Logout");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        schedule.displayStudentSchedule(student, firstTime);
                        firstTime = false;
                        break;
                    case 2:
                        Lesson.registerCourse(student, tutors, schedule, scanner);
                        firstTime = false;
                        break;
                    case 3:
                        if (firstTime) {
                            System.out.println("You must register at least one subject before deleting.");
                        } else {
                            Lesson.deleteCourse(student, schedule, scanner);
                        }
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                scanner.next(); // Consume invalid input
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public void tutorInterface(Tutor tutor, Scanner scanner) {
        while (true) {
            System.out.println("\n1. View Schedule");
            System.out.println("2. Logout");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        schedule.displayTutorSchedule(tutor);
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                scanner.next(); // Consume invalid input
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
