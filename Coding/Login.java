import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {
    private Map<String, String> studentCredentials;
    private Map<String, String> tutorCredentials;

    public Login() {
        studentCredentials = new HashMap<>();
        tutorCredentials = new HashMap<>();
        // Sample student and tutor credentials (username, password)
        studentCredentials.put("student1", "password1");
        tutorCredentials.put("tutor1", "password1");
    }

    public Person loginInterface(Scanner scanner) {
        System.out.println("Login as (student/tutor): ");
        String userType = scanner.nextLine();

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        if (userType.equalsIgnoreCase("student")) {
            if (studentCredentials.containsKey(username) && studentCredentials.get(username).equals(password)) {
                System.out.println("Login successful as student.");
                // Fetch student details from the system
                // Assuming we have a method to get a student object by username
                return getStudentByUsername(username);
            } else {
                System.out.println("Login failed. Invalid credentials.");
                return null;
            }
        } else if (userType.equalsIgnoreCase("tutor")) {
            if (tutorCredentials.containsKey(username) && tutorCredentials.get(username).equals(password)) {
                System.out.println("Login successful as tutor.");
                // Fetch tutor details from the system
                // Assuming we have a method to get a tutor object by username
                return getTutorByUsername(username);
            } else {
                System.out.println("Login failed. Invalid credentials.");
                return null;
            }
        } else {
            System.out.println("Invalid user type.");
            return null;
        }

    }

    // Placeholder methods to simulate fetching a student or tutor by username
    private Student getStudentByUsername(String username) {
        // Implement logic to fetch student by username
        // For now, returning a dummy student
        return new Student("Student", username, "student@example.com", "1234567890", "Grade 10");
    }

    private Tutor getTutorByUsername(String username) {
        // Implement logic to fetch tutor by username
        // For now, returning a dummy tutor
        return new Tutor("Tutor", username, "123 Main St", "1234567890", "Math");
    }
}

/*
 * import java.util.HashMap;
 * import java.util.Map;
 * import java.util.Scanner;
 * 
 * public class Login {
 * private Map<String, String> studentCredentials;
 * private Map<String, String> tutorCredentials;
 * 
 * public Login() {
 * studentCredentials = new HashMap<>();
 * tutorCredentials = new HashMap<>();
 * // Sample student and tutor credentials (username, password)
 * studentCredentials.put("student1", "password1");
 * tutorCredentials.put("tutor1", "password1");
 * }
 * 
 * public void login() {
 * Scanner scanner = new Scanner(System.in);
 * System.out.println("Login as (student/tutor): ");
 * String userType = scanner.nextLine();
 * 
 * System.out.println("Enter username: ");
 * String username = scanner.nextLine();
 * 
 * System.out.println("Enter password: ");
 * String password = scanner.nextLine();
 * 
 * if (userType.equalsIgnoreCase("student")) {
 * if (studentCredentials.containsKey(username) &&
 * studentCredentials.get(username).equals(password)) {
 * System.out.println("Login successful as student.");
 * // Proceed with student functionality
 * } else {
 * System.out.
 * println("Student not found. Do you want to create a new student profile? (yes/no)"
 * );
 * String createNewProfile = scanner.nextLine();
 * if (createNewProfile.equalsIgnoreCase("yes")) {
 * System.out.println("Enter student name: ");
 * String name = scanner.nextLine();
 * System.out.println("Enter student phone number: ");
 * String phoneNumber = scanner.nextLine();
 * System.out.println("Enter student grade: ");
 * String grade = scanner.nextLine();
 * Student newStudent = new Student("Student", username, null, phoneNumber,
 * grade);
 * studentCredentials.put(username, password);
 * System.out.println("New student profile created.");
 * // Proceed with student functionality
 * } else {
 * System.out.println("Login failed.");
 * }
 * }
 * } else if (userType.equalsIgnoreCase("tutor")) {
 * // Login logic for tutor
 * } else {
 * System.out.println("Invalid user type.");
 * }
 * scanner.close();
 * }
 * 
 * 
 * 
 * }
 */

/*
 * public class Login {
 * // This is a placeholder login method
 * public User login() {
 * Scanner scanner = new Scanner(System.in);
 * 
 * // Dummy login for demonstration purposes
 * System.out.println("Enter your user ID:");
 * String userId = scanner.nextLine();
 * 
 * // Determine if the user is a student or a tutor based on userId
 * // In a real application, this would involve checking a database or a file
 * if (userId.startsWith("S")) {
 * // Assuming the student ID starts with "S"
 * for (Student student : HomeTuitionSystem.students) {
 * if (student.getUserId().equals(userId)) {
 * return new User("Student", student, null);
 * }
 * }
 * } else if (userId.startsWith("T")) {
 * // Assuming the tutor ID starts with "T"
 * for (Tutor tutor : HomeTuitionSystem.tutors) {
 * if (tutor.getUserId().equals(userId)) {
 * return new User("Tutor", null, tutor);
 * }
 * }
 * }
 * 
 * System.out.println("Invalid user ID.");
 * return null;
 * }
 * }
 */