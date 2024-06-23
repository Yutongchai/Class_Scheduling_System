import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Lesson {
    private int lessonId;
    private String subject;
    private Tutor tutor;
    private Student student;
    private double price;
    private Day day;
    private static List<Lesson> lessons = new ArrayList<>();

    // Constructor
    public Lesson(int lessonId, String subject, Tutor tutor, Student student, double price, Day day) {
        this.lessonId = lessonId;
        this.subject = subject;
        this.tutor = tutor;
        this.student = student;
        this.price = price;
        this.day = day;
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

    public Day getDay() {
        return day;
    }

    // Method to calculate the lesson price (simple implementation)
    public void calculateLessonPrice() {
        double basePrice = 30.0; // Base price per lesson
        if ("Math".equalsIgnoreCase(subject) || "Science".equalsIgnoreCase(subject)) {
            price = basePrice + 10; // Math and Science lessons are more expensive
        } else {
            price = basePrice; // Default price for other subjects
        }
    }

    // Method to calculate total fee per month for a student
    public static double calculateTotalFeePerMonth(Student student) {
        return lessons.stream()
                .filter(lesson -> lesson.getStudent().equals(student))
                .mapToDouble(Lesson::getPrice)
                .sum();
    }

    // Method to add a new lesson for a student
    public static void addLesson(int lessonId, String subject, Tutor tutor, Student student, double price, Day day) {
        Lesson lesson = new Lesson(lessonId, subject, tutor, student, price, day);
        lesson.calculateLessonPrice();
        lessons.add(lesson);
        System.out.println("Student " + student.getName() + " registered for " + subject + " with tutor "
                + tutor.getName() + " on " + day + ". Lesson ID: " + lessonId);
    }

    // Method to get all lessons
    public static List<Lesson> getLessons() {
        return lessons;
    }

    // Adjusted registerCourse method in Lesson class
    public static void registerCourse(Student student, List<Tutor> tutors, Schedule schedule, Scanner scanner) {
        boolean registerAnother;
        do {
            List<String> registeredSubjects = lessons.stream()
                    .filter(lesson -> lesson.getStudent().equals(student))
                    .map(Lesson::getSubject)
                    .collect(Collectors.toList());

            System.out.println("List of subjects offered:");
            System.out.println("*******************************************************************");
            System.out.println("*  Subject         | Tutor       | Day       | Time               *");
            System.out.println("*******************************************************************");

            // Filter tutors to find available subjects not already registered by the
            // student
            List<String> availableSubjects = tutors.stream()
                    .filter(tutor -> !registeredSubjects.contains(tutor.getSubject()))
                    .map(Tutor::getSubject)
                    .collect(Collectors.toList());

            // Display available subjects with their details
            for (Tutor tutor : tutors) {
                if (availableSubjects.contains(tutor.getSubject())) {
                    System.out.printf("*  %-16s| %-12s| %-10s| %-12s *%n", tutor.getSubject(),
                            tutor.getName(), getDayBySubject(tutor.getSubject()), "8:00 PM - 10:00 PM");
                }
            }
            System.out.println("*******************************************************************");

            System.out.print("Enter course details (subject): ");
            String subject = scanner.nextLine().trim();

            if (availableSubjects.contains(subject)) {
                Day day = getDayBySubject(subject);
                Tutor tutor = tutors.stream()
                        .filter(t -> t.getSubject().equals(subject))
                        .findFirst()
                        .orElse(null); // Assigning first available tutor for simplicity
                double price = 30.0; // Assuming a fixed price
                int lessonId = lessons.size() + 1;
                Lesson.addLesson(lessonId, subject, tutor, student, price, day);
                schedule.addLesson(new Lesson(lessonId, subject, tutor, student, price, day));

                registerAnother = promptForAnotherCourse(scanner);
            } else {
                System.out.println("Invalid subject or already registered. Please enter a valid subject.");
                registerAnother = true; // Keep looping to register another course
            }
        } while (registerAnother);
    }

    // Method to delete a course for a student
    public static void deleteCourse(Student student, Schedule schedule, Scanner scanner) {
        List<Lesson> studentLessons = lessons.stream()
                .filter(lesson -> lesson.getStudent().equals(student))
                .collect(Collectors.toList());

        if (studentLessons.isEmpty()) {
            System.out.println("No subjects to delete.");
            return;
        }

        Set<String> uniqueSubjects = studentLessons.stream()
                .map(Lesson::getSubject)
                .collect(Collectors.toSet());

        System.out.println("Subjects registered:");
        uniqueSubjects.forEach(System.out::println);

        System.out.print("Enter course subject to delete: ");
        String courseToDelete = scanner.nextLine();

        if (uniqueSubjects.contains(courseToDelete)) {
            lessons.removeIf(lesson -> lesson.getStudent().equals(student)
                    && lesson.getSubject().equalsIgnoreCase(courseToDelete));
            schedule.removeLesson(student.getName(), courseToDelete);
            System.out.println("Subject " + courseToDelete + " has been deleted.");
        } else {
            System.out.println("Subject not found. Please enter a valid subject.");
        }
    }

    // Method to prompt for another course registration
    private static boolean promptForAnotherCourse(Scanner scanner) {
        while (true) {
            System.out.println("Do you want to register another subject? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                return true;
            } else if (response.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    // Utility method to get day by subject
    private static Day getDayBySubject(String subject) {
        switch (subject.toLowerCase()) {
            case "malay":
                return Day.FRIDAY;
            case "english":
                return Day.MONDAY;
            case "history":
                return Day.THURSDAY;
            case "mathematics":
                return Day.TUESDAY;
            case "science":
                return Day.WEDNESDAY;
            default:
                return null; // Or handle unknown subjects appropriately
        }
    }

    // toString method
    @Override
    public String toString() {
        return "Lesson [lessonId=" + lessonId + ", subject=" + subject +
                ", tutor=" + tutor.getName() + ", student=" + student.getName() + ", price=" + price +
                ", day=" + day + "]";
    }

    
}
