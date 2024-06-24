public class Student extends Person {

    public Student(String name, String username, String password, String email, String phoneNumber) {
        super(name, username, password, email, phoneNumber);
    }

    public Student(String username, String password) {
        super(username, password);
    }

    public String getDetails() {
        return super.getDetails();
    }
}
