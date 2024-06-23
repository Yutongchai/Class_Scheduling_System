import java.util.Scanner;

public class Init {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        
        // Perform login
        Person personLoggedIn = login.loginInterface(input);

        // Check if login was successful
        if (personLoggedIn != null) {
            HomeTuitionSystem homeTuitionSystem = new HomeTuitionSystem();

            // Determine the interface based on the logged-in person
            if (personLoggedIn instanceof Student) {
                homeTuitionSystem.studentInterface((Student) personLoggedIn, input);
            } else if (personLoggedIn instanceof Admin) {
                homeTuitionSystem.adminInterface((Admin) personLoggedIn, input);
            } else {
                System.out.println("Logged in user is not recognized as a student or an admin. Exiting...");
            }
            
        } else {
            System.out.println("Login failed. Exiting...");
        }

        input.close();
    }
}
