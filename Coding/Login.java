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

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login as (student/tutor): ");
        String userType = scanner.nextLine();
    
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
    
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
    
        if (userType.equalsIgnoreCase("student")) {
            if (studentCredentials.containsKey(username) && studentCredentials.get(username).equals(password)) {
                System.out.println("Login successful as student.");
                // Proceed with student functionality
            } else {
                System.out.println("Student not found. Do you want to create a new student profile? (yes/no)");
                String createNewProfile = scanner.nextLine();
                if (createNewProfile.equalsIgnoreCase("yes")) {
                    System.out.println("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter student phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Enter student grade: ");
                    String grade = scanner.nextLine();
                    Student newStudent = new Student(username, null, phoneNumber, grade);
                    studentCredentials.put(username, password);
                    System.out.println("New student profile created.");
                    // Proceed with student functionality
                } else {
                    System.out.println("Login failed.");
                }
            }
        } else if (userType.equalsIgnoreCase("tutor")) {
            // Login logic for tutor
        } else {
            System.out.println("Invalid user type.");
        }
    }
    

}
