
public class Admin extends Person {
    
    
    public Admin(String username, String password) {
        super(username, password);
    }
    
};




























/*import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;*/

// private static final String TUTOR_FILE = "tutor.txt";
/*public void registerTutor(Scanner scanner) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(TUTOR_FILE, true))) {
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine().trim();

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine().trim();

        System.out.print("Enter subject: ");
        String subject = scanner.nextLine().trim();

        if (gender.isEmpty() || name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || subject.isEmpty()) {
            System.out.println("Error: All fields are required.");
            return;
        }

        writer.write(gender + ", " + name + ", " + email + ", " + phoneNumber + ", " + subject);
        writer.newLine();
        writer.flush();
        System.out.println("Registration successful for tutor: " + name);
    } catch (IOException e) {
        System.out.println("Error adding tutor: " + e.getMessage());
    }
}*/