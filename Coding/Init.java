import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Init {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        Person personLoggedIn = login.loginInterface(input);


        Tutor tutor1 = new Tutor("Male", "Hakim", "Hakim@gmail.com", "019-7882345", "Mathematics");
        Tutor tutor2 = new Tutor("Female", "Rahimah", "rahimah@gmail.com", "014-8890067", "Malay");
        Tutor tutor3 = new Tutor("Female", "Azizah", "azizah@gmail.com", "014-8890089", "Science");
        Tutor tutor4 = new Tutor("Female", "Bharates", "bharates@gmail.com", "014-8890033", "English");
        Tutor tutor5 = new Tutor("Female", "Maizurah", "maizurah@gmail.com", "018-8890077", "History");

        HomeTuitionSystem homeTuitionSystem = new HomeTuitionSystem();

        try {
            File myObj = new File("tutor.txt");
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
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
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
