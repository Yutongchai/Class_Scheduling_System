import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lesson {
    private int lessonId;
    private String date;
    private String time;
    private String subject;
    private Tutor tutor;
    private Student student;
    private double price;
    private String day;
    private boolean isConfirmed;
    private boolean isCancelled;
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
    public Lesson(int lessonId, String date, String time, String subject, Tutor tutor, Student student, double price,
            String day) {
        this.lessonId = lessonId;
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.tutor = tutor;
        this.student = student;
        this.price = price;
        this.day = day;
        this.isConfirmed = false;
        this.isCancelled = false;
        lessons.add(this);
    }

    // Getters and Setters
    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    // Method to confirm the lesson
    public void confirmLesson() {
        if (!isCancelled) {
            this.isConfirmed = true;
            System.out.println("Lesson confirmed.");
        } else {
            System.out.println("Cannot confirm a cancelled lesson.");
        }
    }

    // Method to cancel the lesson
    public void cancelLesson() {
        if (isConfirmed) {
            System.out.println("Cannot cancel a confirmed lesson.");
        } else {
            this.isCancelled = true;
            System.out.println("Lesson cancelled.");
        }
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
        System.out.println("The calculated price for the lesson is: " + price);
    }

    // Method to calculate total fee per month
    public static double calculateTotalFeePerMonth(Student student) {
        return lessons.stream()
                .filter(lesson -> lesson.getStudent().equals(student) && !lesson.isCancelled)
                .mapToDouble(Lesson::getPrice)
                .sum();
    }

    // Method to save lessons to a file
    public static void saveLessonsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Lesson lesson : lessons) {
                writer.write(lesson.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the lessons to the file.");
        }
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

    // Main method to handle user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Register a new subject");
            System.out.println("2. List subjects offered");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter the name of the new subject: ");
                String newSubject = scanner.nextLine();
                registerSubject(newSubject);
            } else if (choice == 2) {
                listSubjectsOffered();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // toString method
    @Override
    public String toString() {
        return "Lesson [lessonId=" + lessonId + ", date=" + date + ", time=" + time + ", subject=" + subject +
                ", tutor=" + tutor.getName() + ", student=" + student.getName() + ", price=" + price + ", day=" + day +
                ", isConfirmed=" + isConfirmed + ", isCancelled=" + isCancelled + "]";
    }
}
