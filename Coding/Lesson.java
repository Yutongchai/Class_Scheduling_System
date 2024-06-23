import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lesson {
    private int lessonId;
    private String subject;
    private Tutor tutor;
    private Student student;
    private double price;
    private Day day;
    private static List<Lesson> lessons = new ArrayList<>();
    private static double totalFee = 0.0; // Total fee accumulator

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

    // Method to calculate the lesson price based on subject
    public void calculateLessonPrice() {
        double basePrice = 30.0; // Base price per lesson
        if ("Mathematics".equalsIgnoreCase(subject) || "Science".equalsIgnoreCase(subject)) {
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
        lesson.calculateLessonPrice(); // Calculate price based on subject
        totalFee += lesson.getPrice(); // Accumulate total fee
        System.out.println("Student " + student.getName() + " registered for " + subject + " with tutor "
                + tutor.getName() + " on " + day + ". Lesson ID: " + lessonId);
        System.out.println("Tuition fee so far: $" + totalFee); // Print the total fee after adding the lesson
    }

    // Method to get all lessons
    public static List<Lesson> getLessons() {
        return lessons;
    }

    // Method to register courses for a student
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

            // Filter tutors to find available subjects not already registered by the student
            List<String> availableSubjects = tutors.stream()
                    .filter(tutor -> !registeredSubjects.contains(tutor.getSubject()))
                    .map(Tutor::getSubject)
                    .collect(Collectors.toList());

            // Display available subjects with their details
            for (Tutor tutor : tutors) {
                if (availableSubjects.contains(tutor.getSubject())) {
                    Day day = getDayBySubject(tutor.getSubject());
                    System.out.printf("*  %-16s| %-12s| %-10s| %-12s *%n", tutor.getSubject(),
                            tutor.getName(), day != null ? day.toString() : "N/A", "8:00 PM - 10:00 PM");
                }
            }
            System.out.println("*******************************************************************");

            System.out.print("Enter course details (subject): ");
            String subject = scanner.nextLine().trim();

            if (availableSubjects.contains(subject)) {
                Day day = getDayBySubject(subject);
                Tutor tutor = tutors.stream()
                        .filter(t -> t.getSubject().equalsIgnoreCase(subject))
                        .findFirst()
                        .orElse(null);
                double price = calculatePriceForSubject(subject); // Calculate price for the subject
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

    // Method to calculate price based on subject
    private static double calculatePriceForSubject(String subject) {
        double basePrice = 30.0; // Base price per lesson
        if ("Mathematics".equalsIgnoreCase(subject) || "Science".equalsIgnoreCase(subject)) {
            return basePrice + 10; // Math and Science lessons are more expensive
        } else {
            return basePrice; // Default price for other subjects
        }
    }

    // Method to get the day by subject
    private static Day getDayBySubject(String subject) {
        switch (subject.toLowerCase().trim()) {
            case "malay":
                return Day.TUESDAY;
            case "english":
                return Day.THURSDAY;
            case "history":
                return Day.FRIDAY;
            case "mathematics":
                return Day.MONDAY;
            case "science":
                return Day.WEDNESDAY;
            case "french":
                return Day.SUNDAY;
            case "mandarin":
                return Day.SATURDAY;
            default:
                return null;
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


/*  Method to delete a course for a student
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
    }*/
