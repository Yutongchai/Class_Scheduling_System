public class Student extends Person {
    private String grade;

    public Student(String name, String email, String phoneNumber, String grade) {
        super(name, email, phoneNumber);
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void enrollLesson(Lesson lesson) {
        // Enrollment logic here
        System.out.println("Student " + getName() + " enrolled in lesson: " + lesson.toString());
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Grade: " + grade;
    }
}
