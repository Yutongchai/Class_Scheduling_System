import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                        while (true) {
                            ArrayList<String> registeredSubjects = (ArrayList<String>) Lesson.getLessons().stream()
                                    .filter(lesson -> lesson.getStudent().equals(student))
                                    .map(Lesson::getSubject)
                                    .collect(Collectors.toList());

                            System.out.print("List of subjects offered:\n");
                            System.out.print("*****************************************************\n");
                            System.out.print("*  Subject         | Day       | Time               *\n");

                            if (!registeredSubjects.contains("Malay")) {
                                System.out.print("*  Malay           | Friday    | 8:00 PM - 10:00 PM *\n");
                            }
                            if (!registeredSubjects.contains("English")) {
                                System.out.print("*  English         | Monday    | 8:00 PM - 10:00 PM *\n");
                            }
                            if (!registeredSubjects.contains("History")) {
                                System.out.print("*  History         | Thursday  | 8:00 PM - 10:00 PM *\n");
                            }
                            if (!registeredSubjects.contains("Mathematics")) {
                                System.out.print("*  Mathematics     | Tuesday   | 8:00 PM - 10:00 PM *\n");
                            }
                            if (!registeredSubjects.contains("Science")) {
                                System.out.print("*  Science         | Wednesday | 8:00 PM - 10:00 PM *\n");
                            }

                            System.out.print("*******************************************************\n");
                            System.out.print("Enter course details (subject):\n");
                            String subject = scanner.nextLine();

                            List<String> validSubjects = List.of("Malay", "English", "History", "Mathematics",
                                    "Science");

                            if (validSubjects.contains(subject) && !registeredSubjects.contains(subject)) {
                                Tutor tutor = tutors.get(count++ % tutors.size()); // Assigning first tutor for
                                                                                   // simplicity
                                double price = 50.0; // Assuming a fixed price
                                int lessonId = Lesson.getLessons().size() + 1;
                                Lesson.addLesson(lessonId, subject, tutor, student, price);

                                System.out.println("Do you want to register another subject? (yes/no)");
                                String response = scanner.nextLine();
                                if (response.equalsIgnoreCase("no")) {
                                    double totalPrice = Lesson.calculateTotalFeePerMonth(student);
                                    System.out.println("Total price to pay: $" + totalPrice);
                                    schedule.displayLessonsForStudent(student);
                                    return;
                                }
                                break;
                            } else {
                                System.out.println(
                                        "Invalid subject or already registered. Please enter a valid subject.");
                            }
                        }
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
