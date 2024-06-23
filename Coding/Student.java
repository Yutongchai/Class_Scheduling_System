public class Student extends Person {
    private String grade;

    public Student(String name, String username, String password, String email, String phoneNumber, String grade) {
        super(name, username, password, email, phoneNumber);
        this.grade = grade;
    }

    public Student(String username, String password) {
        super(username, password);
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return super.toString() + " " + grade;
    }


    public void enrollLesson(Lesson lesson) {
        // Enrollment logic here
        System.out.println("Student " + getName() + " enrolled in lesson: " + lesson.toString());
        //schedule.addLesson(lesson);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Grade: " + grade;
    }
}
