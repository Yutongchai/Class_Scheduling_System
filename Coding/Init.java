import java.io.FileNotFoundException;
import java.util.Scanner;

public class Init {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HomeTuitionSystem homeTuitionSystem = new HomeTuitionSystem();
        Login login = new Login();
        Person personLoggedIn;

        try {
            // new method called
            login.loadStudentCredentials(homeTuitionSystem);
            login.loadTutorCredentials(homeTuitionSystem);

            personLoggedIn = login.loginInterface(input, homeTuitionSystem);
            // Check if login was successful
            if (personLoggedIn != null) {

                // Determine the interface based on the logged-in person
                if (personLoggedIn instanceof Student) {
                    homeTuitionSystem.studentInterface((Student) personLoggedIn, input);
                } else if (personLoggedIn instanceof Admin) {
                    homeTuitionSystem.adminInterface((Admin) personLoggedIn, input);
                } else {
                    System.out.println("\nLogged in user is not recognized as a student or an admin. Exiting...");
                }

            } else {
                System.out.println("\nLogin failed. Exiting...");
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        input.close();
    }
}
