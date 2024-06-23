import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {
    private Map<String, String> studentCredentials;
    private Map<String, String> adminCredentials;
    private static final String STUDENT_FILE = "student.txt";

    public Login() {
        studentCredentials = new HashMap<>();
        adminCredentials = new HashMap<>();
        adminCredentials.put("admin", "123");
        loadStudentCredentials();  // Load student credentials from file on object creation
    }

    public Person loginInterface(Scanner scanner) {
        System.out.println("Login as (student/Admin): ");
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

                // Ask if the student wants to register a new account
                System.out.println("Do you want to register a new account? (yes/no): ");
                String choice = scanner.nextLine().trim();

                if (choice.equalsIgnoreCase("yes")) {
                    registerStudent(scanner);
                    return getStudentByUsername(username);
                } else {
                    return null;
                }
            }
        } else if (userType.equalsIgnoreCase("admin")) {
            // Admin login logic (not implemented in this example)
            System.out.println("Admin login not implemented yet.");
            return null;
        } else {
            System.out.println("Invalid user type.");
            return null;
        }
    }

    private void loadStudentCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 5) {
                    String username = parts[2];
                    String password = parts[3];
                    studentCredentials.put(username, password);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student credentials: " + e.getMessage());
        }
    }

    public void registerStudent(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE, true))) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine().trim();

            System.out.print("Choose a username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Choose a password: ");
            String password = scanner.nextLine().trim();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine().trim();

            writer.write(name + " " + phoneNumber + " " + username + " " + password + " " + email);
            writer.newLine();
            writer.flush();
            studentCredentials.put(username, password);
            System.out.println("Registration successful for student: " + username);
        } catch (IOException e) {
            System.out.println("Error registering student: " + e.getMessage());
        }
    }

    private Person getStudentByUsername(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+"); // Split by whitespace
                if (parts.length >= 4) {
                    String fileUsername = parts[2]; // Username is at index 2
                    String password = parts[3]; // Password is at index 3

                    if (fileUsername.equals(username)) {
                        // Return a Student object with the matched username
                        return new Student(username, password); // Assuming Student class constructor accepts username
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student file: " + e.getMessage());
        }
        return null; // Return null if username is not found
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