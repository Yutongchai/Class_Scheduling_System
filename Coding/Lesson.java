import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Student " + student.getName() + " registered for " + subject + " with tutor "
                + tutor.getName() + " on " + day + ". Lesson ID: " + lessonId);
    }

    // Method to get all lessons
    public static List<Lesson> getLessons() {
        return lessons;
    }

    // toString method
    @Override
    public String toString() {
        return "Lesson [lessonId=" + lessonId + ", subject=" + subject +
                ", tutor=" + tutor.getName() + ", student=" + student.getName() + ", price=" + price +
                ", day=" + day + "]";
    }
}
