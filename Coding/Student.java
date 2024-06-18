public class Student extends Person {
    private String grade;
    //private Schedule schedule;

    public Student(String type, String name, String email, String phoneNumber, String grade) {
        super("Student", name, email, phoneNumber);
        this.grade = grade;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String newgrade) {
        grade = newgrade;
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
