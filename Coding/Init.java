import java.util.Scanner;

public class Init {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        Person personLoggedIn = login.loginInterface(input);

        Tutor tutor1 = new Tutor("Male", "Akim", "akim@gmail.com", "0119233", "Math");
        Tutor tutor2 = new Tutor("Female", "Rahimah", "akim@gmail.com", "0119233", "BM");

        HomeTuitionSystem homeTuitionSystem = new HomeTuitionSystem();
        homeTuitionSystem.addTutor(tutor1);
        homeTuitionSystem.addTutor(tutor2);
        homeTuitionSystem.studentInterface((Student) personLoggedIn, input);

        input.close();
    }
}
