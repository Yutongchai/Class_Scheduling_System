import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {
    private Map<String, String> studentCredentials;

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "123";

    private static final String STUDENT_FILE = "student.txt";
    private static final String ADMIN_FILE = "tutor.txt";

    public Login() {
        studentCredentials = new HashMap<>();
    }

    public Person loginInterface(Scanner scanner, HomeTuitionSystem homeTuitionSystem) {
        System.out.println("Login as (student/admin): ");
        String userType = scanner.nextLine();

        System.out.println("\nEnter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        if (userType.equalsIgnoreCase("student")) {
            return loginStudent(username, password, scanner, homeTuitionSystem);
        } else if (userType.equalsIgnoreCase("admin")) {
            return loginAdmin(username, password, scanner);
        } else {
            System.out.println("Invalid user type.");
            return null;
        }
    }

    private Person loginStudent(String username, String password, Scanner scanner,
            HomeTuitionSystem homeTuitionSystem) {
        if (studentCredentials.containsKey(username) && studentCredentials.get(username).equals(password)) {
            System.out.println("Login successful as student. Welcome back, " + username + ".");
            // Fetch student details from the system
            // Assuming we have a method to get a student object by username
            return homeTuitionSystem.getStudent(username); // Placeholder, replace with actual logic
        } else {
            System.out.println("Login failed. Invalid credentials.");

            // Ask if the student wants to register a new account
            System.out.println("Do you want to register a new account? (yes/no): ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("yes")) {
                return registerStudent(scanner, homeTuitionSystem);
            } else {
                return null;
            }
        }
    }

    private Person loginAdmin(String username, String password, Scanner scanner) {
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Login successful as Admin.");
            return new Admin(username, password); // Assuming Admin class constructor accepts username and password
        } else {
            System.out.println("Login failed. Invalid admin credentials.");
            return null;
        }
    }

    public void loadStudentCredentials(HomeTuitionSystem homeTuitionSystem) throws FileNotFoundException {
        File myObj = new File(STUDENT_FILE);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] studentData = data.split(" ");
            String name = studentData[0];
            String phone = studentData[1];
            String username = studentData[2];
            String password = studentData[3];
            String email = studentData[4];
            String grade = studentData[5];

            studentCredentials.put(username, password);

            Student student = new Student(name, username, password, email, phone, grade);
            homeTuitionSystem.addStudent(student);
        }
        myReader.close();
    }

    public void loadTutorCredentials(HomeTuitionSystem homeTuitionSystem) throws FileNotFoundException {
        File myObj = new File(ADMIN_FILE);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] tutorData = data.split(", ");
            String gender = tutorData[0];
            String name = tutorData[1];
            String email = tutorData[2];
            String phone = tutorData[3];
            String subject = tutorData[4];

            Tutor tutor = new Tutor(gender, name, email, phone, subject);
            homeTuitionSystem.addTutor(tutor);
        }
        myReader.close();
    }

    public Person registerStudent(Scanner scanner, HomeTuitionSystem homeTuitionSystem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE, true))) {
            System.out.print("\nEnter your name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine().trim();

            System.out.print("Choose a username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Choose a password: ");
            String password = scanner.nextLine().trim();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter your grade: ");
            String grade = scanner.nextLine().trim();

            writer.write(name + " " + phoneNumber + " " + username + " " + password + " " + email + " " + grade);
            writer.newLine();
            writer.flush();

            Student newStudent = new Student(name, username, password, email, phoneNumber, grade);
            studentCredentials.put(username, password);
            homeTuitionSystem.addStudent(newStudent);

            System.out.println("Registration successful for student: " + username);

            return newStudent;
        } catch (IOException e) {
            System.out.println("Error registering student: " + e.getMessage());
        }
        return null;
    }
}
