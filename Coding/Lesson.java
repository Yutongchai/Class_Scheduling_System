import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private int lessonId;
    private String subject;
    private Tutor tutor;
    private Student student;
    private double price;
    private static List<Lesson> lessons = new ArrayList<>();

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

    // Method to calculate total fee per month for a student
    public static double calculateTotalFeePerMonth(Student student) {
        return lessons.stream()
                .filter(lesson -> lesson.getStudent().equals(student))
                .mapToDouble(Lesson::getPrice)
                .sum();
    }

    // Method to add a new lesson for a student
    public static void addLesson(int lessonId, String subject, Tutor tutor, Student student, double price) {
        Lesson lesson = new Lesson(lessonId, subject, tutor, student, price);
        lesson.calculateLessonPrice();
        System.out.println("Student " + student.getName() + " registered for " + subject + " with tutor "
                + tutor.getName() + ". Lesson ID: " + lessonId);
    }

    // Method to get all lessons
    public static List<Lesson> getLessons() {
        return lessons;
    }

    // toString method
    @Override
    public String toString() {
        return "Lesson [lessonId=" + lessonId + ", subject=" + subject +
                ", tutor=" + tutor.getName() + ", student=" + student.getName() + ", price=" + price + "]";
    }
}
