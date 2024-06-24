public class Student extends Person {
    private String grade;

    public Student(String name, String username, String password, String email, String phoneNumber, String grade) {
        super(name, username, password, email, phoneNumber);
        this.grade = grade;
    }

    public Student(String username, String password) {
        super(username, password);
    }
}
