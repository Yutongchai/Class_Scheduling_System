import java.util.ArrayList;

public class Tutor extends Person {
    private String subject;
    private ArrayList<Student> studentList;
    private ArrayList<Lesson> lessonList;

    public Tutor(String type, String name, String email, String phoneNumber, String subject) {
        super("Tutor", name, email, phoneNumber);
        this.subject = subject;
        this.studentList = new ArrayList<>();
        this.lessonList = new ArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    public void addStudent(Student student) {
        studentList.add(student);
        System.out.println("Student " + student.getName() + " added to tutor " + getName() + "'s list.");
    }

    public void addLesson(Lesson lesson) {
        lessonList.add(lesson);
        System.out.println("Lesson added: " + lesson.toString());
    }

    public void displayInfo() {
        System.out.println("Tutor: " + getName() + ", Subject: " + subject);
        System.out.println("Students:");
        for (Student student : studentList) {
            System.out.println(" - " + student.getDetails());
        }
        System.out.println("Lessons:");
        for (Lesson lesson : lessonList) {
            System.out.println(" - " + lesson.toString());
        }
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Subject: " + subject;
    }
}
