import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lesson {
    private int lessonId;
    private String subject;
    private Tutor tutor;
    private Student student;
    private double price;
    private static List<Lesson> lessons = new ArrayList<>();
    private static List<String> subjectsOffered = new ArrayList<>();

    static {
        // Initialize with some default subjects
        subjectsOffered.add("Math");
        subjectsOffered.add("Science");
        subjectsOffered.add("History");
        subjectsOffered.add("English");
    }

    // Constructor
    public Lesson(int lessonId, String subject, Tutor tutor, Student student, double price) {
        this.lessonId = lessonId;
        this.subject = subject;
        this.tutor = tutor;
        this.student = student;
        this.price = price;
        lessons.add(this);
    }

    // Getters
    public int getLessonId() {
        return lessonId;
    }

    public String getSubject() {
        return subject;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Student getStudent() {
        return student;
    }

    public double getPrice() {
        return price;
    }

    // Method to calculate the lesson price (simple implementation)
    public void calculateLessonPrice() {
        double basePrice = 20.0; // Base price per lesson
        if ("Math".equalsIgnoreCase(subject)) {
            price = basePrice + 10; // Math lessons are more expensive
        } else if ("Science".equalsIgnoreCase(subject)) {
            price = basePrice + 15; // Science lessons are even more expensive
        } else {
            price = basePrice; // Default price for other subjects
        }
    }

    // Method to calculate total fee per month
    public static double calculateTotalFeePerMonth(Student student) {
        return lessons.stream()
                .filter(lesson -> lesson.getStudent().equals(student))
                .mapToDouble(Lesson::getPrice)
                .sum();
    }

    // Method to register a new subject
    public static void registerSubject(String subject) {
        if (!subjectsOffered.contains(subject)) {
            subjectsOffered.add(subject);
            System.out.println("New subject registered: " + subject);
        } else {
            System.out.println("Subject already offered: " + subject);
        }
    }

    // Method to list all subjects offered
    public static void listSubjectsOffered() {
        System.out.println("Subjects offered:");
        for (String subject : subjectsOffered) {
            System.out.println(subject);
        }
    }

    // Method to register a student for a subject
    public static void registerStudentForSubject(Student student, String subject, Tutor tutor) {
        int lessonId = lessons.size() + 1;
        Lesson lesson = new Lesson(lessonId, subject, tutor, student, 0);
        lesson.calculateLessonPrice();
        System.out.println("Student " + student.getName() + " registered for " + subject);
        subjectsOffered.remove(subject);
    }

    // Main method to handle user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student currentStudent = null;

        while (true) {
            if (currentStudent == null) {
                System.out.println("1. Register a new student");
                System.out.println("5. Exit");
            } else {
                System.out.println("2. Register a student for a subject");
                System.out.println("3. List subjects offered");
                System.out.println("4. Show student's registered subjects");
                System.out.println("5. Exit");
            }
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1 && currentStudent == null) {
                System.out.print("Enter student name: ");
                String studentName = scanner.nextLine();
                Student student = new Student(studentName);
                Student.registerStudent(student);
                currentStudent = student;
                System.out.println("Now register subjects for the new student.");
                System.out.println();
            } else if (currentStudent != null) {
                if (choice == 2) {
                    listSubjectsOffered();
                    System.out.print("Enter subject to register: ");
                    String subject = scanner.nextLine();
                    if (!subjectsOffered.contains(subject)) {
                        System.out.println("Subject not offered.");
                        continue;
                    }
                    Tutor tutor = new Tutor("Default Tutor"); // Assuming a default tutor for simplicity
                    registerStudentForSubject(currentStudent, subject, tutor);
                } else if (choice == 3) {
                    listSubjectsOffered();
                } else if (choice == 4) {
                    currentStudent.showRegisteredLessons();
                } else if (choice == 5) {
                    if (currentStudent != null) {
                        double totalFee = Lesson.calculateTotalFeePerMonth(currentStudent);
                        System.out.println("Total fee per month for " + currentStudent.getName() + ": " + totalFee);
                    }
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("No student is currently selected. Please register a student first.");
            }
        }
        scanner.close();
    }

    // toString method
    @Override
    public String toString() {
        return "Lesson [lessonId=" + lessonId + ", subject=" + subject +
                ", tutor=" + tutor.getName() + ", student=" + student.getName() + ", price=" + price + "]";
    }

    // Method to get all lessons
    public static List<Lesson> getLessons() {
        return lessons;
    }
}
