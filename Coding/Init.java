import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Init {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        
        // Perform login
        Person personLoggedIn = login.loginInterface(input);
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

        try {
            File myObj = new File("student.txt");
            Scanner myReader2 = new Scanner(myObj);
            while (myReader2.hasNextLine()) {
                String data = myReader2.nextLine();
                String[] studentData = data.split(" ");
                if (studentData.length < 6) {
                    System.out.println("Invalid student data: " + data);
                    continue; // Skip this line and move to the next one
                }
                String name = studentData[0];
                String phone = studentData[1];
                String username = studentData[2];
                String password = studentData[3];
                String email = studentData[4];
                String grade = studentData[5];
                
                Student student = new Student(name, username, password, email, phone, grade);
                homeTuitionSystem.addStudent(student);
            }
            myReader2.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Check if login was successful
        if (personLoggedIn != null) {

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
