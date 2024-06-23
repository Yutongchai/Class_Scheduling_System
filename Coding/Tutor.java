/*import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;*/

public class Tutor extends Person {
    private String subject;
    //private ArrayList<Student> studentList;
    //private ArrayList<Lesson> lessonList;

    public Tutor(String gender, String name, String email, String phoneNumber, String subject) {
        super(name, email, phoneNumber);
        this.subject = subject;
        //this.studentList = new ArrayList<>();
        //this.lessonList = new ArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    /*public void addStudent(Student student) {
        studentList.add(student);
        System.out.println("Student " + student.getName() + " added to tutor " + getName() + "'s list.");
    }

    public void addLesson(Lesson lesson) {
        lessonList.add(lesson);
        System.out.println("Lesson added: " + lesson.toString());
    }

    public List<Student> getStudentList() {
        return Lesson.getLessons().stream()
                .filter(lesson -> lesson.getTutor().equals(this))
                .map(Lesson::getStudent)
                .distinct()
                .collect(Collectors.toList());
    }

    public void displayStudentList() {
        System.out.println("Tutor: " + getName() + ", Subject: " + subject);
        List<Student> students = getStudentList();
        if (students.isEmpty()) {
            System.out.println("No students registered for " + subject + ".");
        } else {
            System.out.println("Students registered for " + subject + ":");
            for (Student student : students) {
                System.out.println("- " + student.getName());
            }
        }
    }*/

    public void printDetails() {

        System.out.println();
    }

}
