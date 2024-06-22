import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HomeTuitionSystem {
    private ArrayList<Student> students;
    private ArrayList<Tutor> tutors;
    private Schedule schedule;

    public HomeTuitionSystem() {
        students = new ArrayList<>();
        tutors = new ArrayList<>();
        schedule = new Schedule();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTutor(Tutor tutor) {
        tutors.add(tutor);
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student.getDetails());
        }
    }

    public void displayTutors() {
        for (Tutor tutor : tutors) {
            System.out.println(tutor.getDetails());
        }
    }

    public void studentInterface(Student student, Scanner scanner) {
        int count = 0;
        boolean firstTime = true;
        while (true) {
            System.out.println("1. View Schedule");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Logout");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        if (firstTime) {
                            System.out.println("No registered subjects.");
                        } else {
                            // schedule.displayLessonsForStudent(student);
                            List<Lesson> studentLessons = Lesson.getLessons().stream()
                                    .filter(lesson -> lesson.getStudent().equals(student))
                                    .collect(Collectors.toList());
                            System.out.println("\n\nSubjects registered:");
                            System.out.println("*************************************************************");
                            System.out.println("*  Subject         | Tutor       | Day       | Time         *");
                            System.out.println("*************************************************************");
                            for (Lesson lesson : studentLessons) {
                                System.out.printf("*  %-16s| %-12s| %-10s| %-12s*%n", lesson.getSubject(),
                                        lesson.getTutor().getName(), lesson.getDay(),
                                        getClassTime(lesson.getSubject()));
                            }
                            System.out.println("*************************************************************");
                        }
                        break;
                    case 2:
                        boolean registerAnother;
                        do {
                            ArrayList<String> registeredSubjects = (ArrayList<String>) Lesson.getLessons().stream()
                                    .filter(lesson -> lesson.getStudent().equals(student))
                                    .map(Lesson::getSubject)
                                    .collect(Collectors.toList());

                            System.out.print("List of subjects offered:\n");
                            System.out.print("*************************************************************\n");
                            System.out.print("*  Subject         | Tutor       | Day       | Time         *\n");

                            List<String> availableSubjects = tutors.stream()
                                    .map(Tutor::getSubject)
                                    .distinct()
                                    .filter(subject -> !registeredSubjects.contains(subject))
                                    .collect(Collectors.toList());

                            for (Tutor tutor : tutors) {
                                if (availableSubjects.contains(tutor.getSubject())) {
                                    System.out.printf("*  %-16s| %-12s| %-10s| %-12s *\n", tutor.getSubject(),
                                            tutor.getName(), getDayBySubject(tutor.getSubject()), "8:00 PM - 10:00 PM");
                                }
                            }

                            System.out.print("*************************************************************\n");
                            System.out.print("Enter course details (subject):\n");
                            String subject = scanner.nextLine();

                            if (availableSubjects.contains(subject)) {
                                Day day = getDayBySubject(subject);
                                Tutor tutor = tutors.get(count++ % tutors.size()); // Assigning first tutor for
                                                                                   // simplicity
                                double price = 50.0; // Assuming a fixed price
                                int lessonId = Lesson.getLessons().size() + 1;
                                Lesson.addLesson(lessonId, subject, tutor, student, price, day);

                                while (true) {
                                    System.out.println("Do you want to register another subject? (yes/no)");
                                    String response = scanner.nextLine();
                                    if (response.equalsIgnoreCase("yes")) {
                                        registerAnother = true;
                                        break;
                                    } else if (response.equalsIgnoreCase("no")) {
                                        List<Lesson> studentLessons = Lesson.getLessons().stream()
                                                .filter(lesson -> lesson.getStudent().equals(student))
                                                .collect(Collectors.toList());

                                        System.out.println("\n\nSubjects registered:");
                                        System.out.println(
                                                "*************************************************************");
                                        System.out.println(
                                                "*  Subject         | Tutor       | Day       | Time         *");
                                        System.out.println(
                                                "*************************************************************");
                                        for (Lesson lesson : studentLessons) {
                                            System.out.printf("*  %-16s| %-12s| %-10s| %-12s*%n", lesson.getSubject(),
                                                    lesson.getTutor().getName(), lesson.getDay(),
                                                    getClassTime(lesson.getSubject()));
                                        }
                                        System.out.println(
                                                "*************************************************************");

                                        double totalPrice = Lesson.calculateTotalFeePerMonth(student);
                                        System.out.println("\nTotal price to pay: $" + totalPrice + "\n");
                                        registerAnother = false;
                                        break;
                                    } else {
                                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                                    }
                                }
                            } else {
                                System.out.println(
                                        "Invalid subject or already registered. Please enter a valid subject.");
                                registerAnother = true;
                            }
                        } while (registerAnother);
                        firstTime = false;
                        break;
                    case 3:
                        System.out.print("Enter course subject to delete: ");
                        String courseToDelete = scanner.nextLine();
                        schedule.removeLesson(student.getName(), courseToDelete);

                        // Display updated subjects after deletion
                        List<Lesson> updatedLessons = Lesson.getLessons().stream()
                                .filter(lesson -> lesson.getStudent().equals(student))
                                .collect(Collectors.toList());

                        System.out.println("\n\nUpdated subjects registered:");
                        System.out.println("*************************************************************");
                        System.out.println("*  Subject         | Tutor       | Day       | Time         *");
                        System.out.println("*************************************************************");
                        for (Lesson lesson : updatedLessons) {
                            System.out.printf("*  %-16s| %-12s| %-10s| %-12s*%n", lesson.getSubject(),
                                    lesson.getTutor().getName(), lesson.getDay(), getClassTime(lesson.getSubject()));
                        }
                        System.out.println("*************************************************************");
                        break;

                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                scanner.next();
            }
        }
    }

    public void tutorInterface(Tutor tutor, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Choose Lesson Time");
            System.out.println("2. View Schedule");
            System.out.println("3. View Students");
            System.out.println("4. Logout");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Available subjects to choose time:");
                        List<String> subjects = List.of("Malay", "English", "History", "Mathematics", "Science");
                        subjects.forEach(subject -> System.out.println("* " + subject));

                        System.out.println("Enter the subject:");
                        String subject = scanner.nextLine();

                        if (subjects.contains(subject)) {
                            Day day = getDayBySubject(subject);
                            double price = 50.0; // Assuming a fixed price
                            int lessonId = Lesson.getLessons().size() + 1;
                            Student student = getStudentDetails(scanner); // Method to fetch student details
                            Lesson.addLesson(lessonId, subject, tutor, student, price, day);
                            tutor.addLesson(new Lesson(lessonId, subject, tutor, student, price, day));
                        } else {
                            System.out.println("Invalid subject.");
                        }
                        break;
                    case 2:
                        System.out.println("Tutor's Schedule:");
                        tutor.displayInfo();
                        break;
                    case 3:
                        System.out.println("Students taught by " + tutor.getName() + ":");
                        tutor.getStudentList().forEach(student -> System.out.println(student.getDetails()));
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }
        }
    }

    private Student getStudentDetails(Scanner scanner) {
        System.out.println("Enter student name:");
        String name = scanner.nextLine();
        System.out.println("Enter student email:");
        String email = scanner.nextLine();
        System.out.println("Enter student phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter student grade:");
        String grade = scanner.nextLine();
        return new Student("Student", name, email, phoneNumber, grade);
    }

    private Day getDayBySubject(String subject) {
        switch (subject.toLowerCase()) {
            case "malay":
                return Day.FRIDAY;
            case "english":
                return Day.MONDAY;
            case "history":
                return Day.THURSDAY;
            case "mathematics":
                return Day.TUESDAY;
            case "science":
                return Day.WEDNESDAY;
            default:
                return null; // Or handle unknown subjects appropriately
        }
    }

    private String getClassTime(String subject) {
        return "8:00 PM - 10:00 PM"; // Assuming all classes have the same time
    }

}
