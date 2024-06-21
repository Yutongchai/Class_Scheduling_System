import java.util.Scanner;

public class Init {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        Person personLoggedIn = login.loginInterface(input);

        Tutor tutor1 = new Tutor("Male", "Hakim", "Hakim@gmail.com", "019-7882345", "Math");
        Tutor tutor2 = new Tutor("Female", "Rahimah", "rahimah@gmail.com", "014-8890067", "Malaysia Language");
        Tutor tutor3 = new Tutor("Female", "Azizah", "azizah@gmail.com", "014-8890089", "Science");
        Tutor tutor4 = new Tutor("Female", "Bharates", "bharates@gmail.com", "014-8890033", "English");
        Tutor tutor5 = new Tutor("Female", "Maizurah", "maizurah@gmail.com", "018-8890077", "History");

        HomeTuitionSystem homeTuitionSystem = new HomeTuitionSystem();
        homeTuitionSystem.addTutor(tutor1);
        homeTuitionSystem.addTutor(tutor2);
        homeTuitionSystem.addTutor(tutor3);
        homeTuitionSystem.addTutor(tutor4);
        homeTuitionSystem.addTutor(tutor5);

        homeTuitionSystem.studentInterface((Student) personLoggedIn, input);

        input.close();
    }
}
