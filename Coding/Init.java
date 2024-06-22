import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Init {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        Person personLoggedIn = login.loginInterface(input);

        HomeTuitionSystem homeTuitionSystem = new HomeTuitionSystem();

        try {
            File myObj = new File("Tutor.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] tutorData = data.split(",");
                String gender = tutorData[0];
                String name = tutorData[1];
                String email = tutorData[2];
                String phone = tutorData[3];
                String subject = tutorData[4];

                Tutor tutor = new Tutor(gender, name, email, phone, subject.trim()); // Trim to remove leading/trailing
                                                                                     // spaces
                homeTuitionSystem.addTutor(tutor);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File 'Tutor.txt' not found or could not be read.");
            e.printStackTrace();
        }

        if (personLoggedIn instanceof Student) {
            homeTuitionSystem.studentInterface((Student) personLoggedIn, input);
        } else if (personLoggedIn instanceof Tutor) {
            homeTuitionSystem.tutorInterface((Tutor) personLoggedIn, input);
        } else {
            System.out.println("Logged in user is not recognized as a student or a tutor. Exiting...");
        }

        input.close();
    }
}
